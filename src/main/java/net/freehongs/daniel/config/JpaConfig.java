package net.freehongs.daniel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "net.freehongs.daniel.domain.*.repository"
        }
)
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaConfig {
  @Primary
  @Bean("danielDataSource")
  @ConfigurationProperties(prefix = "spring.daniel.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  /**
   * JPA Setting
   */
  @Primary
  @Bean("entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
          EntityManagerFactoryBuilder builder, @Qualifier("danielDataSource") DataSource dataSource){
    return builder.dataSource(dataSource).packages("net.freehongs.daniel.domain.*.model").build();
  }

  @Primary
  @Bean(name="transactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
    return new JpaTransactionManager(entityManagerFactory);
  }
}
