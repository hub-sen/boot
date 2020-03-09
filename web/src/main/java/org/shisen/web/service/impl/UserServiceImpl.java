package org.shisen.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.shisen.web.entity.User;
import org.shisen.web.mapper.UserMapper;
import org.shisen.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description  用户相关处理
 * </pre>
 *
 * @author shishi
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
