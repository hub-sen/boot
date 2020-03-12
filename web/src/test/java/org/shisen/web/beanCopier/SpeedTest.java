package org.shisen.web.beanCopier;

import com.boot.common.beans.WrapperBeanCopier;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/11 18:22
 * </pre>
 */
public class SpeedTest {
    @Test
    public void normalCopyTest() {


        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setName("施森" + i);
            user.setAge(i);
            users.add(user);
        }

        long start = System.currentTimeMillis();
        for (User user : users) {
            UserDto userDto = new UserDto();
            WrapperBeanCopier.copyProperties(user, userDto);
        }
        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));

        long start2 = System.currentTimeMillis();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("(end2 - start2) = " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        for (User user : users) {
            UserDto userDto = new UserDto();
            WrapperBeanCopier.copyProperties(user, userDto);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("(end3 - start3) = " + (end3 - start3));

    }
}
