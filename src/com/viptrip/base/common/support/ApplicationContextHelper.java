package com.viptrip.base.common.support;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
@SuppressWarnings("unchecked")
public class ApplicationContextHelper implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	private static ApplicationContextHelper instance = null;
	private ApplicationContextHelper(){
		
	}
	public static ApplicationContextHelper getInstance(){
		if(instance == null){
			synchronized (ApplicationContextHelper.class) {
				if(instance == null){
					instance = new ApplicationContextHelper();
				}
			}
		}
		return instance;
	}
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		instance = this;
		this.applicationContext = arg0;
	}
	/**
	 * 获取ApplicationContext
	 * @return
	 */
	public ApplicationContext getApplicationContext(){
		checkApplicationContext();
		return this.applicationContext;
	}
	/**
	 * 根据BeanName获取Bean
	 * @param <T>
	 * @param beanName
	 * @return
	 */
	public <T> T getBean(String beanName){
		return (T) this.applicationContext.getBean(beanName);
	}
	/**
	 * 获取Bean
	 * @param <T>
	 * @param beanName
	 * @return
	 */
	public <T> T getBean(Class<T> clazz){
		return (T) this.applicationContext.getBean(clazz);
	}
	/**
	 * 获取相同了类型的bean
	 * @param clazz
	 * @return
	 */
	public <T> Map<String,T>getBeansOfType(Class<T> clazz){
		return (Map<String,T>)this.applicationContext.getBeansOfType(clazz);
	}
	/**
	 * 创建bean
	 * @param clazz
	 * @return
	 */
	public <T> T createBean(Class<T> clazz){
		return this.getApplicationContext().getAutowireCapableBeanFactory().createBean(clazz);
	}
	/**
	 * 清除ApplicationContext
	 */
	public void clearApplicationContext(){
		this.applicationContext = null;
	}
	/**
	 * 检查
	 */
	private void checkApplicationContext() {
		if(this.applicationContext == null){
			System.out.println("applicationContext未注入，请在service-context.xml文件中配置!");
		}
	}
	
	/**
	 * 获取action
	 * 该方法获取基类的action会报错，比如BaseAction，因为有多个实现
	 * @param clz
	 * @return
	 */
	public <E> E getAction(Class<E> clz){
		WebApplicationContext rootWac=ContextLoader.getCurrentWebApplicationContext();
		//获取servletContext
		ServletContext servletContext = rootWac.getServletContext(); 
		WebApplicationContext wac=WebApplicationContextUtils.
				getWebApplicationContext(servletContext,
						"org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet" );
//					"org.springframework.web.servlet.FrameworkServlet.CONTEXT.springServlet" );
		return wac.getBean(clz);
	}
}
