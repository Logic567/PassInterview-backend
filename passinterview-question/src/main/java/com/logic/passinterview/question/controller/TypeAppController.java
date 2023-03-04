package com.logic.passinterview.question.controller;

import com.logic.passinterview.common.utils.R;
import com.logic.passinterview.question.entity.TypeEntity;
import com.logic.passinterview.question.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("question/app/type")
public class TypeAppController {

    private Map<String,Object> cache = new HashMap<>();

    @Autowired
    private TypeService typeService;

    /**
     * 查询题目类型列表 by hashmap
     * @return
     */
    @RequestMapping("/list")
    public R list(){
       List<TypeEntity> typeEntityListCache = (List<TypeEntity>) cache.get("typeEntityList");
       //如果缓存中没有数据
        if (typeEntityListCache == null){
            System.out.println("The cache is empty");
            List<TypeEntity> list = typeService.list();
            typeEntityListCache = list;
            cache.put("typeEntityList",typeEntityListCache);
        }
        return R.ok().put("typeEntityList",typeEntityListCache);
    }

    /**
     * 查询题目类型列表 by redis
     * @return
     */
    @RequestMapping("/list-by-redis")
    public R listByRedis(){
        List<TypeEntity> typeEntityList = typeService.getTypeEntityList();
        return R.ok().put("typeEntityList",typeEntityList);
    }

    /**
     * 查询题目类型列表 by redis 加锁
     * @return
     */
    @RequestMapping("/list-by-redis-lock")
    public R listByRedisLock(){
        List<TypeEntity> typeEntityList = typeService.getTypeEntityListByLock();
        return R.ok().put("typeEntityList",typeEntityList);
    }

    /**
     * 查询题目类型列表 by redis 分布式锁 Lua 脚本
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/list-by-redis-distributed-lock")
    public R listByRedisDistributedLock() throws InterruptedException {
        List<TypeEntity> typeEntityList = typeService.getTypeEntityListByRedisDistributedLock();
        return R.ok().put("typeEntityList",typeEntityList);
    }

    /**
     * 查询题目类型列表 by redisson 分布式锁
     * @return
     */
    @RequestMapping("/list-by-redisson-distributed-lock")
    public R listByRedissonDistributedLock() throws InterruptedException{
        List<TypeEntity> typeEntityList = typeService.getTypeEntityListByRedissonDistributedLock();
        return R.ok().put("typeEntityList",typeEntityList);
    }
}
