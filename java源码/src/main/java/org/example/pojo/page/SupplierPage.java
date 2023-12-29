package org.example.pojo.page;

import lombok.Data;

/**
 * @模块文件夹：SupplierPage
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.25.22:36
 */
@Data
public class SupplierPage {
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
}
