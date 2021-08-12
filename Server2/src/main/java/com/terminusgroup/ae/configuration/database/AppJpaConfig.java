//package com.terminusgroup.ae.configuration.database;
//
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan("com.terminusgroup.ae")
//@EnableJpaRepositories(
//        basePackages = "com.terminusgroup.ae.repository",
//        entityManagerFactoryRef = "pfsRepEntityManager",
//        transactionManagerRef = "pfsRepTransactionManager")
//public class AppJpaConfig {
//
//
//    @Autowired
//    Environment env;
//    private static final Logger logger = Logger.getLogger("APPLI.AppJPAConfig");
//    @Bean(name = "mysqldb", destroyMethod = "")
//    public DataSource pfsDataSource() {
//        DataSource ds = null;
//        try {
//            String dataSource = "DEV";
//            final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//            dsLookup.setResourceRef(true);
//            logger.info("Looking for datasource :- "+dataSource);
//            ds=dsLookup.getDataSource(dataSource);
//            logger.info("Datasource Found :- "+dataSource);
//
//
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage(),e);
//        }
//        return ds;
//    }
//
//
//
//
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean pfsRepEntityManager() {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(pfsDataSource());
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.setPackagesToScan("rta.ae.healthcheck.model.pfsRep");
//        factoryBean.setJpaProperties(HibernateProperties.getProperties());
//        return factoryBean;
//    }
//
//
//
//
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(false);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
//
//        return hibernateJpaVendorAdapter;
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager pfsRepTransactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
//
//}
