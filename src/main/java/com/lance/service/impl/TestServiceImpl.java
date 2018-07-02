package com.lance.service.impl;

import com.lance.repository.TestRepository;
import com.lance.entity.TestEntity;
import com.lance.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by lance on 2018/7/1.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {
    private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    private TestRepository testRepository;

    @Override
    @CachePut(value = "testEntity",key = "#testEntity.name")
    public TestEntity save(TestEntity testEntity) {
        System.out.println("对name，key为"+testEntity.getName()+"的数据做了缓存");
        return  testRepository.save(testEntity);
    }



    @Override
    @CacheEvict(value = "testEntity")
    public int deleteByUsername(String username) {
        System.out.println("删除了testEntity的 key为"+username+"的缓存");
        System.out.println("同时在数据库中删除了");
        return testRepository.deleteByName(username);

    }

    //将缓存保存进testEntity，并使用参数username作为缓存的key
    @Cacheable(value="testEntity",key="#username")
    public TestEntity findByUsername(String username) {
        System.out.println("对name，key为"+username+"的数据做了缓存");
        return testRepository.getByName(username);
    }
}
