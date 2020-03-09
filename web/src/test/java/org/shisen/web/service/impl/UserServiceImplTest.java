package org.shisen.web.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.shisen.web.BootApplicationTests;
import org.shisen.web.entity.User;
import org.shisen.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
public class UserServiceImplTest extends BootApplicationTests {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getUser() {
		User user = userService.getById("123");
		System.out.println("user = " + user);
	}

}