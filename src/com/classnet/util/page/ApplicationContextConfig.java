package com.classnet.util.page;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ApplicationContextConfig {
	private static ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"});
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz, String beanName) {
		return (T) context.getBean(beanName);
	}
	public static void main(String args[]){
		System.out.println(getBean(HibernateTemplate.class, "hibernateTemplate")==null);
	}
}
