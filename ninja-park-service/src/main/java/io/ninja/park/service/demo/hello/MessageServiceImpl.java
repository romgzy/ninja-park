/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.hello;

import org.springframework.stereotype.Service;

/**
 * @author romgzy
 *
 */
@Service("helloMessageService")
public class MessageServiceImpl implements MessageService {

    /* (non-Javadoc)
     * @see org.netside.samples.petclinic.demo.hello.MessageService#getMessage()
     */
    @Override
    public String getMessage() {
        return "Hello World!";
    }

}
