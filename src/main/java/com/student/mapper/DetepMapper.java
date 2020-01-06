package com.student.mapper;

import com.student.entity.Detep;
import com.student.entity.DetepExample;
import java.util.List;

public interface DetepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Detep record);

    int insertSelective(Detep record);

    List<Detep> selectByExample(DetepExample example);

    Detep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detep record);

    int updateByPrimaryKey(Detep record);
}