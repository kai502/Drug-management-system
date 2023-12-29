package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.Problem;
import org.example.pojo.page.ProblemPage;
import org.example.service.ProblemService;
import org.example.mapper.ProblemMapper;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【problem】的数据库操作Service实现
* @createDate 2023-11-30 21:03:26
*/
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem>
    implements ProblemService{

    @Autowired
    private ProblemMapper problemMapper ;

    @Override
    public ApiResult getProblems(ProblemPage problemPage) {

        IPage<Map> page = new Page<>(problemPage.getPageNum(),problemPage.getPageSize());

        problemMapper.selectPromblems(page);

        Map pageMap = new HashMap();
        pageMap.put("pageData",page.getRecords());
        pageMap.put("pageTotal",page.getTotal());
        pageMap.put("pageNum",page.getCurrent());

        return ApiResult.ok(pageMap);

    }

    @Override
    public ApiResult addProblem(Problem problem) {

        int row = problemMapper.insert(problem);

        if(row == 0){
            return ApiResult.error(null,900,"问题记录插入失败");
        }

        return ApiResult.ok(null);

    }

    @Override
    public ApiResult deleteProblem(Problem problem) {

        int row = problemMapper.deleteById(problem);

        if(row == 0){
            return ApiResult.error(null,901,"删除问题记录失败");
        }

        return ApiResult.ok(null);

    }

}




