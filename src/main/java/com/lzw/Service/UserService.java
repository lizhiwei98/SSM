package com.lzw.Service;

import com.lzw.Pojo.user;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-29-13:17
 */
public interface UserService {
    public int regist(user u);
    public user login(user u);
    public user existuser(String username);
    public int deleteById(Integer id);
    public int update(user u);
    public user selectById(Integer id);
    public List<user> selectAll();
}
