package com.springboot.hyll;

import com.springboot.hyll.config.customrepository.CustomRepositoryFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 类描述：MapperScan自动扫描指定的文件夹底下的目录
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
@MapperScan("com.springboot.hyll.*.mybatis")
public class HyllApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HyllApplication.class, args);
	}

}
