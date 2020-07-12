package net.freehongs.daniel.domain.hello.repository;

import net.freehongs.daniel.domain.hello.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {
}
