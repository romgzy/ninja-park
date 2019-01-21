/**
 * 
 */
package io.ninja.park.service.demo.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.ninja.park.service.demo.spring.hello.MessagePrinter;
import io.ninja.park.service.demo.spring.transaction.ProjectUseScopeService;

/**
 * @author haochong.z
 *
 */
public class SpringMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] files = new String[] { "classpath:spring-config.xml" };
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(files);
		// MessagePrinter printer =
		// applicationContext.getBean(MessagePrinter.class);
		// printer.printMessage();
		ProjectUseScopeService projectUseScopeService = applicationContext.getBean(ProjectUseScopeService.class);
		// projectUseScopeService.save();
		projectUseScopeService.query(1L);

	}

}
