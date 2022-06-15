package com.pavlov.testtask_urlhashtables.controller;

import com.pavlov.testtask_urlhashtables.service.URLService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebMvcTest(URLController.class)
public class URLControllerTest {

    @MockBean
    private URLService urlService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init () {
        mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getURLInfoTest () throws Exception{
        Mockito.when(urlService.getChangedURLs()).thenReturn(new ArrayList<String>());
        Mockito.when(urlService.getNewURLs()).thenReturn(new ArrayList<String>());
        Mockito.when(urlService.getDeletedURLs()).thenReturn(new ArrayList<String>());

        this.mockMvc.perform(get("/letter"))
                .andExpect(status().isOk())
                .andExpect(view().name("letter"))
                .andExpect(model().attribute("name", "и.о. секретаря"))
                .andExpect(model().attribute("deletedURLs", Collections.emptyList()))
                .andExpect(model().attribute("newURLs", Collections.emptyList()))
                .andExpect(model().attribute("changedURLs", Collections.emptyList()));
    }
}
