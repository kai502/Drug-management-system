package org.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.pojo.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【problem】的数据库操作Mapper
* @createDate 2023-11-30 21:03:26
* @Entity org.example.pojo.Problem
*/
public interface ProblemMapper extends BaseMapper<Problem> {

    IPage<Map> selectPromblems(Object o);
}




