package com.logic.passinterview.auth.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 访问数据库用户表类
 */
@Repository("SysUserRepository")
public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    /**
     * JPA 会根据方法名自动生成 SQL 执行，完全不用自己写SQL
     * @param userId
     * @return
     */
    SysUser findByUserId(String userId);
}
