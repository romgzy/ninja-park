/**
 * 
 */
package io.ninja.park.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * @author haochong.z
 *
 */
public class RestTemplateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] files = new String[] { "classpath:spring-restTemplate.xml" };
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(files);
		RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
		String url = "http://www.google.com";
		String response = restTemplate.getForObject(url, String.class);

	}

}
