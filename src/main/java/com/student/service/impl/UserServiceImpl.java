package com.student.service.impl;

import com.student.entity.Detep;
import com.student.entity.PageBean;
import com.student.entity.Users;
import com.student.entity.UsersExample;
import com.student.mapper.DetepMapper;
import com.student.mapper.UsersMapper;
import com.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
      @Autowired
      private UsersMapper usersMapper;

      @Autowired
      private DetepMapper detepMapper;

    @Override
    public List<Users> selectPageAndLike(PageBean page) {

        UsersExample u = new UsersExample();
        UsersExample.Criteria c = u.createCriteria();
        if (page.getDetepId()!=null&&page.getDetepId()!=0){
            c.andDetepEqualTo(page.getDetepId());
        }
        if (page.getStartBirth()!=null){
            c.andBirthGreaterThan(page.getStartBirth());
        }
        if (page.getEndBirth()!=null){
            c.andBirthLessThan(page.getEndBirth());
        }
        if (page.getName()!=null&&!page.getName().trim().equals("")){
            c.andNameLike("%"+page.getName()+"%");

        }
        return usersMapper.selectByExample(u);

    }

    @Override
    public List<Detep> selectDtepAll() {
        return detepMapper.selectByExample(null);
    }

    @Override
    public int selectAddUsers(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public int delUsers(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Users selectOneUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateOne(Users users) {
        return usersMapper.updateByPrimaryKey(users);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        int temps=0;
        for (Integer id : ids) {
         usersMapper.deleteByPrimaryKey(id);
         temps++;
        }
        return temps;
    }
}
