package org.example.pojo.page;

import lombok.Data;

/**
 * @模块文件夹：ProblemPage
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.12.01.09:49
 */

@Data
public class ProblemPage {
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
}
