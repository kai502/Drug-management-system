package org.example.service;

import org.example.pojo.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.page.ProblemPage;
import org.example.utils.ApiResult;

/**
* @author 陈鸿凯
* @description 针对表【problem】的数据库操作Service
* @createDate 2023-11-30 21:03:26
*/
public interface ProblemService extends IService<Problem> {

    ApiResult getProblems(ProblemPage problemPage);

    ApiResult addProblem(Problem problem);

    ApiResult deleteProblem(Problem problem);
}
