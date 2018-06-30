package com.lance.dao;

import com.lance.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by sang on 2017/1/10.
 */
//Spring data Rest风格
@RepositoryRestResource(path="sysuser")
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    //REST 查询
//    查询id为1的对象
//   访问 http://localhost:8080/sysuser/1
    //    暴露findByUsername方法为REST资源
    //定义
    @RestResource(path="findByName",rel="findByName")
    SysUser findByUsername(@Param("name") String username);
    //访问 http://localhost:8080/sysuser/search/findByName?name=lance

    SysUser findSysUserById(Long id);
}
