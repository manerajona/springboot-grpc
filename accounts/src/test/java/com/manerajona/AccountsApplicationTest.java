package com.manerajona;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
class AccountsApplicationTest {

    @MockBean
    AccountsApplicationRunner applicationRunner;

    @Test
    void contextLoads() {
    }
}