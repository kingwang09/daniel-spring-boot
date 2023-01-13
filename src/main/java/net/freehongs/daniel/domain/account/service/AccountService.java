package net.freehongs.daniel.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.domain.account.model.Account;
import net.freehongs.daniel.domain.account.model.AccountUser;
import net.freehongs.daniel.domain.account.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findOneByEmail(email).orElseThrow(()-> new NoSuchElementException(email+"의 이메일은 존재하지 않거나 올바르지 않습니다."));
        return new AccountUser(account);
    }


    public AccountDto add(AccountDto accountDto){
        Account account = accountDto.toEntity();
        //패스워드는 단방향 암호화로 인코딩
        account.changePassword(passwordEncoder.encode(accountDto.getPassword()));
        return accountRepository.save(account).toDto();
    }

    @Transactional
    public AccountDto modify(Long id, AccountDto accountDto){
        Account account = accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException(id+"의 회원번호는 존재하지 않거나 올바르지 않습니다."));
        account.change(accountDto);
        return account.toDto();
    }

    public AccountDto getOne(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException(id+"의 회원번호는 존재하지 않거나 올바르지 않습니다.")).toDto();
    }

}
