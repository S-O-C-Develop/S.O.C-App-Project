package com.soc.BackEnd.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.BackEnd.account.dto.SignupDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("회원가입 테스트")
    @Test
    public void signupTest() throws Exception{
        // given
        SignupDto signupDto = SignupDto.builder()
                .studentId("123456789")
                .nickname("vividswan")
                .password("12345678910")
                .email("vividswan@naver.com")
                .build();

        // when
        final ResultActions perform = mockMvc.perform(post("/api/sign-up")
                .content(objectMapper.writeValueAsString(signupDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Success"))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.success").value(true));

    }
}