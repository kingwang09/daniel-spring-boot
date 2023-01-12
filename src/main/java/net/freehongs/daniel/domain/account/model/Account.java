package net.freehongs.daniel.domain.account.model;

import lombok.*;
import net.freehongs.daniel.domain.account.constant.AccountRole;
import net.freehongs.daniel.domain.account.constant.AccountStatus;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.support.jpa.AbstractDateAuditingEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends AbstractDateAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(this.roles == null){
            return Collections.emptySet();
        }
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void change(AccountDto accountDto){
        this.address = accountDto.getAddress();
    }

    public AccountDto toDto(){
        return AccountDto.builder()
                .id(this.id)
                .email(this.email)
                .address(this.address)
                .build();
    }
}
