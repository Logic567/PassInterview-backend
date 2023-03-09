package com.logic.passinterview.jwt.utils;

import com.logic.passinterview.jwt.config.PassInterviewJwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JWT 工具类
 */
@Component
public class PassInterviewJwtTokenUtil {
    private static final String JWT_CACHE_KEY = "jwt:userId:";
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String EXPIRE_IN = "expire_in";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private PassInterviewJwtProperties jwtProperties;

    /**
     * 生成 Token 令牌和刷新令牌
     * @param userId
     * @param username
     * @return
     */
    public Map<String, Object> generateTokenAndRefreshToken(String userId, String username) {
        Map<String, Object> tokenMap = buildToken(userId, username);
        cacheToken(userId, tokenMap);
        return tokenMap;
    }

    /**
     * 更新缓存中的 Token
     * @param userId
     * @param username
     * @return
     */
    public Map<String,Object> refreshTokenAndGenerateToken(String userId,String username){
        Map<String,Object> tokenMap = buildToken(userId,username);
        stringRedisTemplate.delete(JWT_CACHE_KEY + userId);
        cacheToken(userId,tokenMap);

        return tokenMap;
    }

    //把 token 放在 redis 缓存中
    private void cacheToken(String userId, Map<String, Object> tokenMap) {
        stringRedisTemplate.opsForHash().put(JWT_CACHE_KEY + userId, ACCESS_TOKEN, tokenMap.get(ACCESS_TOKEN));
        stringRedisTemplate.opsForHash().put(JWT_CACHE_KEY + userId, REFRESH_TOKEN, tokenMap.get(REFRESH_TOKEN));
        stringRedisTemplate.expire(userId, jwtProperties.getExpiration() * 2, TimeUnit.MILLISECONDS);
    }

    /**
     * 构建 Token
     *
     * @param userId
     * @param username
     * @return
     */
    private Map<String, Object> buildToken(String userId, String username) {
        String accessToken = generateToken(userId, username, null);
        String refreshToken = generateRefreshToken(userId, username, null);
        HashMap<String, Object> tokenMap = new HashMap<>(2);
        tokenMap.put(ACCESS_TOKEN, accessToken);
        tokenMap.put(REFRESH_TOKEN, refreshToken);
        tokenMap.put(EXPIRE_IN, jwtProperties.getExpiration());
        return tokenMap;
    }

    /**
     * 生成 Token 令牌
     *
     * @param userId
     * @param username
     * @param payloads
     * @return
     */
    public String generateToken(String userId, String username,
                                Map<String, String> payloads) {
        Map<String, Object> claims = buildClaims(userId, username, payloads);
        return generateToken(claims);
    }

    /**
     * 生成 Token 刷新令牌
     *
     * @param userId-用户id或用户名
     * @param username
     * @param payloads-令牌中携带的附加信息
     * @return
     */
    private String generateRefreshToken(String userId, String username, Map<String, String> payloads) {
        Map<String, Object> claims = buildClaims(userId, username, payloads);
        return generateRefreshToken(claims);
    }

    private Map<String, Object> buildClaims(String userId, String username, Map<String, String> payloads) {
        int payloadSizes = payloads == null ? 0 : payloads.size();

        Map<String, Object> claims = new HashMap<>(payloadSizes + 2);
        claims.put("sub", userId);
        claims.put("username", username);
        claims.put("created", new Date());

        if (payloadSizes > 0) {
            claims.putAll(payloads);
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param claims-数据声明
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        //过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration());
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 生成刷新令牌 refreshToken，有效期是令牌的 2 倍
     *
     * @param claims-数据声明
     * @return
     */
    private String generateRefreshToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 2);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 从请求中获取用户 id
     * @param request
     * @return
     */
    public String getUserIdFromRequest(HttpServletRequest request){
        return request.getHeader(USER_ID);
    }

    /**
     * 删除缓存中的 token
     * @param userId
     * @return
     */
    public boolean removeToken(String userId){
        return Boolean.TRUE.equals(stringRedisTemplate.delete(JWT_CACHE_KEY + userId));
    }

    /**
     * 从令牌中获取用户id
     * @param token
     * @return
     */
    public String getUserIdFromToken(String token){
        String userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        }catch (Exception e){
            userId = null;
        }
        return userId;
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(USER_NAME);
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取数据声明，验证 JWT 签名
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否不存在 redis 中
     * @param token
     * @return
     */
    public Boolean isRefreshTokenNotExistCache(String token){
        String userId = getUserIdFromToken(token);
        String refreshToken = (String) stringRedisTemplate.opsForHash().get(JWT_CACHE_KEY + userId,REFRESH_TOKEN);
        return refreshToken == null || !refreshToken.equals(token);
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return true=已过期，false=未过期
     */
    public Boolean isTokenExpired(String token){
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e){
            //验证 JWT 签名失败等同于令牌过期
            return true;
        }
    }
}
