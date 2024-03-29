package com.soc.backend.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.backend.account.dto.LoginReq;
import com.soc.backend.account.dto.SignupReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원가입 테스트")
    @Test
    public void signupTest() throws Exception{
        // given
        SignupReq signupReq = SignupReq.builder()
                .studentId("123456789")
                .nickname("vividswanTest")
                .password("12345678910")
                .email("vividswanTest@naver.com")
                .build();

        // when
        final ResultActions perform = mockMvc.perform(post("/api/sign-up")
                .content(objectMapper.writeValueAsString(signupReq))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result").exists());

    }

    @DisplayName("로그인 테스트")
    @Test
    public void loginTest() throws Exception{
        //given
        String studentId = "123456789";
        String rawPassword = "123456789";

        Account accountEntity = Account.builder()
                .studentId(studentId)
                .nickname("testUser")
                .email("test@test.com")
                .password(passwordEncoder.encode(rawPassword))
                .isConfirm(false)
                .role(RoleType.ROLE_USER)
                .emailToken(UUID.randomUUID().toString())
                .build();
        accountRepository.save(accountEntity);

        LoginReq loginReq = LoginReq.builder()
                .studentId(studentId)
                .password(rawPassword)
                .build();

        //when
        final ResultActions perform = mockMvc.perform(post("/api/sign-in")
                .content(objectMapper.writeValueAsString(loginReq))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result").exists());
    }
}