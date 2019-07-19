package boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shisen.web.WebBootApplication;
import org.shisen.web.models.vo.UserVo;
import org.shisen.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebBootApplication.class)
public class BootApplicationTests {


	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		UserVo user = userService.getUser("施森");
		System.out.println("user = " + user);
	}

}
