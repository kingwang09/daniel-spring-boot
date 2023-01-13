package net.freehongs.daniel.domain.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.domain.account.model.AccountUser;
import net.freehongs.daniel.domain.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> add(@RequestBody AccountDto accountDto){
        log.debug("accounts add: {}", accountDto);
        return ResponseEntity.ok(accountService.add(accountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable Long id, @AuthenticationPrincipal AccountUser currentAccount){
        log.debug("accounts get: id={}, currentAccount={}", id, currentAccount);
        if(!Objects.equals(currentAccount.getAccount().getId(), id)){//TODO AOP로 처리할 수 있을 것으로 보임.
            log.warn("인증되지 않음.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(accountService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> modify(@PathVariable Long id, @RequestBody AccountDto accountDto, @AuthenticationPrincipal AccountUser currentAccount){
        log.debug("accounts modify: id={}, currentAccount={}, account={}", id, currentAccount, accountDto);
        if(!Objects.equals(currentAccount.getAccount().getId(), id)){//TODO AOP로 처리할 수 있을 것으로 보임.
            log.warn("인증되지 않음.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(accountService.modify(id, accountDto));
    }
}
