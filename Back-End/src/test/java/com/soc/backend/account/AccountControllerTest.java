package com.soc.backend.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.backend.account.dto.LoginDto;
import com.soc.backend.account.dto.SignupDto;
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


import java.util.Collections;
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
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.success").value(true));

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
                .roles(Collections.singletonList(RoleType.ROLE_USER))
                .emailToken(UUID.randomUUID().toString())
                .build();
        accountRepository.save(accountEntity);

        LoginDto loginDto = LoginDto.builder()
                .studentId(studentId)
                .password(rawPassword)
                .build();

        //when
        final ResultActions perform = mockMvc.perform(post("/api/sign-in")
                .content(objectMapper.writeValueAsString(loginDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }
}