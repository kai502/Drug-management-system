package org.example.controller;

import org.example.pojo.Problem;
import org.example.pojo.page.ProblemPage;
import org.example.service.ProblemService;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @模块文件夹：ProblemController
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.30.21:04
 */

@CrossOrigin
@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping("getProblems")
    public ApiResult getProblems(@RequestBody ProblemPage problemPage){
        return problemService.getProblems(problemPage);
    }

    @PostMapping("addProblem")
    public ApiResult addProblem(@RequestBody Problem problem){
        return problemService.addProblem(problem);
    }

    @PostMapping("deleteProblem")
    public ApiResult deleteProblem(@RequestBody Problem problem){
        return problemService.deleteProblem(problem);
    }



}
