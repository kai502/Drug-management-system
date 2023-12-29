package org.example.pojo.page;

import lombok.Data;

/**
 * @模块文件夹：ReceiptPage
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.12.02.16:28
 */
@Data
public class ReceiptPage {

    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
}
