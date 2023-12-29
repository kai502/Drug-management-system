package org.example.controller;

import org.example.pojo.Receipt;
import org.example.pojo.page.ReceiptPage;
import org.example.service.ReceiptService;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @模块文件夹：ReceiptController
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.12.02.14:04
 */

@CrossOrigin
@RestController
@RequestMapping("receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("outInReceipt")
    public ApiResult outInReceipt(@RequestBody Receipt receipt){
        return receiptService.outInReceipt(receipt);
    }

    @PostMapping("deleteReceipt")
    public ApiResult deleteReceipt(@RequestBody Receipt receipt){
        return receiptService.deleteReceipt(receipt);
    }

    @PostMapping("getReceipt")
    public ApiResult getReceipt(@RequestBody Receipt receipt){
        return receiptService.getReceipt(receipt);
    }

    @PostMapping("getReceipts")
    public ApiResult getReceipts(@RequestBody ReceiptPage receiptPage){
        return receiptService.getReceipts(receiptPage);
    }

}
