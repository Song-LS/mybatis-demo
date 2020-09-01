package com.sls.base.config;

import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SLS
 * @data 2019/3/4 13:38
 **/
@Configuration
@MapperScan(basePackages = "com.sls.base.dao")
public class MybatisPlusConfig {

    /**
     * 注册逻辑删除bean ，使用了逻辑删除需要在实体类的删除字段上加上@TableLogic
     *
     * @return ISqlInjector
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }
}
