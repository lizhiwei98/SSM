package com.lzw.Mapper;

import com.lzw.Pojo.user;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-29-13:05
 */
public interface UserMapper {
    public int insert(user u);
    public user login(user u);
    public int deleteById(Integer id);
    public int update(user u);
    public user existuser(String username);
    public user selectById(Integer id);
    public List<user> selectAll();
}
