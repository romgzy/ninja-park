/**
 * 
 */
package io.ninja.park.service.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author romgzy
 *
 */

@Service
public class HelloMessagePrinter {
    
	final private MessageService service;
	
	@Autowired
    public HelloMessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }

}
