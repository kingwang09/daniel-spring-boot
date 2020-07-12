package net.freehongs.daniel.domain.hello.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Class Hello
 * @Description
 * @Author hyungeun.jin
 * @Since 2020. 7. 12.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 7. 12. || 진형은 || 최초생성
 */
@ToString
@NoArgsConstructor
@Getter
@Entity
@Table
public class Hello {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Builder
  public Hello(String name) {
    this.name = name;
  }
}
