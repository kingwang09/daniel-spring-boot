package net.freehongs.daniel.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.freehongs.daniel.domain.account.constant.AccountRole;
import net.freehongs.daniel.domain.account.constant.AccountStatus;
import net.freehongs.daniel.domain.account.model.Account;
import net.freehongs.daniel.domain.account.model.Address;

import java.util.Set;

/**
 * TODO
 * - validation 추가 필요
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;

    private String email;

    private String password;

    private Address address;

    @JsonIgnore
    public Account toEntity(){
        return Account.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .address(this.address)
                .status(AccountStatus.NORMAL)
                .roles(Set.of(AccountRole.USER))
                .build();
    }
}
