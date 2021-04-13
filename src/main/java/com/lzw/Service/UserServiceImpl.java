package com.lzw.Service;

import com.lzw.Mapper.UserMapper;
import com.lzw.Pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-29-13:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int regist(user u) {
        return userMapper.insert(u);
    }

    public user login(user u) {
        return userMapper.login(u);
    }

    public user existuser(String username) {
        return userMapper.existuser(username);
    }

    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    public int update(user u) {
        return userMapper.update(u);
    }

    public user selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<user> selectAll() {
        return userMapper.selectAll();
    }

}
