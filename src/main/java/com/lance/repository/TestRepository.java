package com.lance.repository;

import com.lance.entity.TestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lance on 2018/6/26.
 */
public interface TestRepository extends CrudRepository<TestEntity,String> {

    /*查询操作 */

    //    常规查询
    TestEntity getByName(String name);//查询单个
    List<TestEntity> findAll();//查询全部
    List<TestEntity> findAll(Sort sort);//查询全部，返回排序结果
    List<TestEntity> findTop5ByOrderByNickname(Sort sort);
    List<TestEntity> findFirst4By(Sort sort);
    List<TestEntity> findTop3ByOrderByNameAsc();
    long count();

    //@NamedQuery 查询,在Entity注解
    TestEntity getUserByNQ(String name);

    //@Query查询

    @Query("select t from TestEntity t where t.name=?1")
    TestEntity getUserByQuery(String name);

    @Query("select t from TestEntity t where t.name=:name")
    TestEntity getUserByQuery2(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("update TestEntity t set t.name=?1 where t.name=?2")
    int setName(String name,String oldname);


//    分页
    Page<TestEntity> findAll(Pageable pageable);


    //增加
     TestEntity save(TestEntity testEntity);


     //删除
     @Modifying
     @Transactional
     @Query("delete from TestEntity test where test.name = ?1")
     int deleteByName(String name);

     //批量删除
     @Modifying
     @Transactional
     @Query(value="delete from TestEntity test where test.name in (:names) ")
     int deleteByNames(@Param("names")List<String> names);


}
