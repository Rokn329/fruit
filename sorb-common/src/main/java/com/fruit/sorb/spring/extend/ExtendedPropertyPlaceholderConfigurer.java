package com.fruit.sorb.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private Properties props;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.props = props;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
