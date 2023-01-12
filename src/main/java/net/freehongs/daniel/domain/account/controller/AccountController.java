package net.freehongs.daniel.domain.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.domain.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountDto add(@RequestBody AccountDto accountDto){
        log.debug("accounts add: {}", accountDto);
        return accountService.add(accountDto);
    }

    @PutMapping("/{id}")
    public AccountDto modify(@PathVariable Long id, @RequestBody AccountDto accountDto){
        log.debug("accounts modify: id={}, account={}", id, accountDto);
        return accountService.modify(id, accountDto);
    }
}
