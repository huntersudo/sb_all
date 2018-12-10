/*
package com.project.seed.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

*/
/**
 * Created by SML on 2018/5/14.
 *
 * 静态多数据源情况
 * 示例
 *//*

@Configuration
@MapperScan(basePackages = {MybatisConfigOfEs.MAPPER_SCAN}, sqlSessionFactoryRef = MybatisConfigOfEs.SQL_SESSION_FACTORY, sqlSessionTemplateRef = MybatisConfigOfEs.SQL_SESSION_TEMPLATE_REF)
public class MybatisConfigOfEs {


    public static final String FLAG = "es";
    public static final String SPRING_DATA_SOURCE = "spring.dataSource." + FLAG;
    public static final String MAPPER_SCAN = "com.chinamobile.cmss.persistence." + FLAG;
    public static final String ALIAS_PACKAGE_SCAN = "com.chinamobile.cmss.model." + FLAG;
    public static final String XML_SCAN = "classpath*:com/chinamobile/cmss/persistence/" + FLAG + "/*.xml";
    public static final String DATA_SOURCE = "DataSource_" + FLAG;
    public static final String TRANSACTION_MANAGER = "TransactionManager_" + FLAG;
    public static final String SQL_SESSION_FACTORY = "SqlSessionFactory_" + FLAG;
    public static final String SQL_SESSION_TEMPLATE_REF = "SqlSessionTemplateRef_" + FLAG;


    @Bean(name = DATA_SOURCE)
    @Primary
    @ConfigurationProperties(prefix = SPRING_DATA_SOURCE)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = SQL_SESSION_FACTORY)
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setVfs(SpringBootVFS.class);

        sessionFactoryBean.setTypeAliasesPackage(ALIAS_PACKAGE_SCAN);

        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(XML_SCAN));
        return sessionFactoryBean.getObject();
    }


    @Bean(TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager TransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean(name = SQL_SESSION_TEMPLATE_REF)
    @Primary
    public SqlSessionTemplate SqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


}
*/
