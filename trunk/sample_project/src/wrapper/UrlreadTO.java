package com.rapidus.urlread.wrapper;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.rapidus.urlread.util.Service;

public class UrlreadTO {

	XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beanFactory.xml"));
	
	/*Method to evaluate URL*/
	public List<String> callEvaluateURL(String URL){		
		Service myBean = (Service) beanFactory.getBean("ServiceBean");
		List<String> ls = myBean.evaluateURL(URL);
		return ls; 
	}

}
