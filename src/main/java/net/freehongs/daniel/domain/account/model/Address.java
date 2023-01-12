package net.freehongs.daniel.domain.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String zipCode;
    private String address;
    private String addressDetail;
}
