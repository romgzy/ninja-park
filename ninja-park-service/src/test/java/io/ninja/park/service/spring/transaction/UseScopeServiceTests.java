/**
 * 
 */
package io.ninja.park.service.spring.transaction;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import io.ninja.park.service.demo.spring.transaction.ProjectUseScopeService;

/**
 * @author rule
 *
 */
@PowerMockIgnore({ "javax.management.*" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/spring-config-test.xml" })
@DbUnitConfiguration(databaseConnection = { "h2DataSource" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UseScopeServiceTests {
	private static final Logger LOG = LoggerFactory.getLogger(UseScopeServiceTests.class);
	@Autowired
	private ProjectUseScopeService scopeServie;

	@Test
	public void testCreateRefundOrder() {
		scopeServie.save();
		long id = 0L;
		
	
	}

}
