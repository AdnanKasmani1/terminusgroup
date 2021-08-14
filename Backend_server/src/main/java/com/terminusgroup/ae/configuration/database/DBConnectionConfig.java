//package com.terminusgroup.ae.configuration.database;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.startup.Tomcat;
//import org.apache.tomcat.util.descriptor.web.ContextResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jmx.export.MBeanExporter;
//import org.springframework.jmx.support.RegistrationPolicy;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DBConnectionConfig {
//
//
//	@Autowired
//	MBeanExporter mBeanExporter ;
//
//
//@Bean
//	public TomcatServletWebServerFactory tomcatFactory() {
//		return new TomcatServletWebServerFactory() {
//
//			@Override
//			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
//				tomcat.enableNaming();
//				return new TomcatWebServer(tomcat, getPort() >= 0);
//			}
//
//			@Override
//			protected void postProcessContext(Context context) {
//
//				mBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
//				try {
//					context.getNamingResources().addResource(getResource("root", "zxcv+12345", "DEV"));
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//
//
//			}
//		};
//	}
//
//	private ContextResource getResource(String userName,String password , String jndiName)
//	{
//
//		ContextResource resource = new ContextResource();
//		resource.setType(DataSource.class.getName());
//		resource.setName(jndiName);
//		resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
//		resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
//		resource.setProperty("url", "jdbc:mysql://${MYSQL_HOST:localhost}:3306/terminus_group");
//		resource.setProperty("username", userName);
//		resource.setProperty("password", password);
//		return resource;
//	}
//
//
//
//
//
//}
