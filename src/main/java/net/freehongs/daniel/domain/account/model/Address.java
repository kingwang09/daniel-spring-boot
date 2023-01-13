package net.freehongs.daniel.domain.account.model;

import lombok.*;

import javax.persistence.Embeddable;

@ToString
@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String zipCode;
    private String address;
    private String addressDetail;
}
