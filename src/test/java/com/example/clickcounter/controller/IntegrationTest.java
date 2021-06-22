package com.example.clickcounter.controller;

import com.example.clickcounter.service.CountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    CountService service;

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void integrationTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/count")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                service.add();
                try {
                    Thread.sleep((long) (Math.random() * 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        List<Thread> threads = new ArrayList<>();
        Stream.generate(() -> new Thread(task)).limit(15).peek(threads::add).forEach(Thread::start);
        for (Thread t : threads) {
            t.join();
        }
        mvc.perform(MockMvcRequestBuilders
                .get("/count")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("15000"));
    }
}