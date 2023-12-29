package org.example.pojo.page;

import lombok.Data;

/**
 * @模块文件夹：Page
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.25.20:19
 */

@Data
public class DrugPage {
    private String keyword;
    private Integer sid;
    private Integer pageNum = 1;
    private Integer pageSize = 5;
}
