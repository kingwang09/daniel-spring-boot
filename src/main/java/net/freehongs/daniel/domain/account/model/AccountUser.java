package net.freehongs.daniel.domain.account.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class AccountUser extends User {
    private Account account;

    public AccountUser(Account account) {
        super(account.getEmail(), account.getPassword(), account.getAuthorities());
        this.account = account;
    }
}
