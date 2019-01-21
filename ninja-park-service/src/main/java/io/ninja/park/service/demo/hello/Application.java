
package io.ninja.park.service.demo.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author romgzy
 *
 */
/*@Configuration
@ComponentScan*/
public class Application {

	/*@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}*/

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Application.class);
		HelloMessagePrinter printer = context.getBean(HelloMessagePrinter.class);
		printer.printMessage();

	}

}
