package com.lance.service;

import com.lance.entity.TestEntity;

/**
 * Created by lance on 2018/7/1.
 */
public interface TestService {
    TestEntity findByUsername(String username);
    int deleteByUsername(String username);
    TestEntity save(TestEntity testEntity);
}
