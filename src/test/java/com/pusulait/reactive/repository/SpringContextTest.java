package com.pusulait.reactive.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pusulait.reactive.ReactiveDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReactiveDemoApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
