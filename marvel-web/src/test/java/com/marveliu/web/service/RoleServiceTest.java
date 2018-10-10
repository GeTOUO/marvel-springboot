package com.marveliu.web.service;

import com.marveliu.web.dao.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.notNullValue;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void f0_test() {
        Role role = roleService.findById(1);
        log.info(role.toString());
        Assert.assertThat(role, notNullValue(null));
    }
}