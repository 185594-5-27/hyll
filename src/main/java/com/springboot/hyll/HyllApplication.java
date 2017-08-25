package com.springboot.hyll;

import com.springboot.hyll.config.customrepository.CustomRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class HyllApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HyllApplication.class, args);
	}

}
