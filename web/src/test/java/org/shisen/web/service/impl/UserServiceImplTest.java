package org.shisen.web.service.impl;

import org.shisen.web.BootApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.shisen.web.models.vo.UserVo;
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
		UserVo user = userService.getUser("施森");
		System.out.println("user = " + user);
	}

	@Test
	public void addUser() {

	}

	@Test
	public void delUser() {
	}
}