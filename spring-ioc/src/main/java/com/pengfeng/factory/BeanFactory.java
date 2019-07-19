package com.pengfeng.factory;

import com.pengfeng.BeanDefinition;
/**
 * bean的容器
 * @author pengfeng
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
