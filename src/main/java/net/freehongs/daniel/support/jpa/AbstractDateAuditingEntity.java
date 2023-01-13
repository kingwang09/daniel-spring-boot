package net.freehongs.daniel.support.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractDateAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedDate
  @Column(name="Registered", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date registered = Date.from(Instant.now());

  @LastModifiedDate
  @Column(name="Updated")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated = Date.from(Instant.now());

}
