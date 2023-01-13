package net.freehongs.daniel.config;

import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.domain.account.model.Address;
import net.freehongs.daniel.domain.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Value("${spring.security.client.id}")
    private String clientId;
    @Value("${spring.security.client.secret}")
    private String clientSecret;

    @Test
    public void 인증토큰_발급() throws Exception {
        //given
        AccountDto accountDto = AccountDto.builder()
                .email("kingwang09@gmail.com")
                .password("password")
                .address(
                        Address.builder()
                            .zipCode("12345")
                            .address("우주 어딘가")
                            .addressDetail("나의 행성")
                        .build()
                )
                .build();
        accountService.add(accountDto);

        log.debug("clientId: {}, clientSecret:{}", clientId, clientSecret);

        //when & then
        mockMvc.perform(
                post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .param("username", accountDto.getEmail())
                .param("password", accountDto.getPassword())
                .param("grant_type", "password")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());

    }
}
