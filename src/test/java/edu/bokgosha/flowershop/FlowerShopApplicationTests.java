package com.example.flowershop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class FlowerShopApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCatalog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/catalog"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog"));
    }

    @Test
    public void testGetLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("enter"));
    }
}
