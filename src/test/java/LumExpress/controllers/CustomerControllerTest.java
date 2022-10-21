package LumExpress.controllers;

import LumExpress.dtos.requests.CustomerRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    private CustomerRegistrationRequest registrationRequest;

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        registrationRequest = CustomerRegistrationRequest.builder()
                                        .email("blessingokougbenin879@gmail.com")
                                        .password("password")
                                        .country("Naija")
                                        .build();
    }

    @Test
    void registerControllerTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/")
                .contentType("application/json")
                .content(mapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andDo(print());
    }
}