package com.student.service;

import com.student.entity.Detep;
import com.student.entity.PageBean;
import com.student.entity.Users;

import java.util.List;

public interface UserService {

    List<Users> selectPageAndLike(PageBean page);

    List<Detep> selectDtepAll();


    int selectAddUsers(Users users);

    int delUsers(Integer id);

    Users selectOneUsers(Integer id);

    int updateOne(Users users);

    int deleteBatch(Integer[] ids);
}
