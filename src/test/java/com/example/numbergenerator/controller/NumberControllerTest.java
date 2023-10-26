package com.example.numbergenerator.controller;

import com.example.numbergenerator.service.impl.NumberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberController.class)
public class NumberControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    NumberServiceImpl numberServiceImpl;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getRandomNumberTest() throws Exception {
        String response = "А116АА 116 RUS";
        when(numberServiceImpl.generateRandomNumber()).thenReturn(response);
        assertEquals(response, mockMvc.perform(get("/random"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    void getNextNumberTest() throws Exception {
        String response = "А117АА 116 RUS";
        when(numberServiceImpl.generateNextNumber()).thenReturn(response);
        assertEquals(response, mockMvc.perform(get("/next"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }
}
