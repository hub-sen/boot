package com.shisen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.common.utils.NumberUtils;
import com.shisen.entity.User;
import com.shisen.mapper.UserMapper;
import com.shisen.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shishi
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "boot:user:verify:";

    public Boolean chckData(String data, Integer type) {
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                return null;
        }
        QueryWrapper<User> query = Wrappers.query(user);
        return userMapper.selectCount(query) == 0;
    }

    public void sendCode(String phone) {
        //生成验证码
        String code = NumberUtils.generateCode(6);
        //将code存入redis
        String key = KEY_PREFIX + phone;
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        //发送短信
    }

    public boolean register(User user, String code) {
        //验证code
        String key = KEY_PREFIX + user.getPhone();
        String cachecode = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(cachecode, code)) {
            return false;
        }
        //code正确加密密码，储存
        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //加密密码
        String password = CodecUtils.md5Hex(user.getPassword(), salt);
        user.setPassword(password);
        user.setCreatedTime(new Date());
        int i = userMapper.insert(user);
        return i == 1;
    }

    @Cacheable(cacheNames = "user", key = "#username+#password")
    public User findUserByUsernameAndPassword(String username, String password) {
        User u = new User();
        u.setUsername(username);
        QueryWrapper<User> query = Wrappers.query(u);
        User user = userMapper.selectOne(query);
        if (user == null) {
            return null;
        }
        String dbpass = CodecUtils.md5Hex(password, user.getSalt());
        if (!StringUtils.equals(dbpass, user.getPassword())) {
            return null;
        }
        return user;
    }
}
