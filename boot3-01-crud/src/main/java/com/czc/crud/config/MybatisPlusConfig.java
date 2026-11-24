package com.czc.crud.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.czc.crud.mapper")
public class MybatisPlusConfig {
}
