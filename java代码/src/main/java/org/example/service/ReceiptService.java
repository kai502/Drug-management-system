package org.example.service;

import org.example.pojo.Receipt;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.page.ReceiptPage;
import org.example.utils.ApiResult;

/**
* @author 陈鸿凯
* @description 针对表【receipt】的数据库操作Service
* @createDate 2023-11-30 21:27:59
*/
public interface ReceiptService extends IService<Receipt> {

    ApiResult outInReceipt(Receipt receipt);

    ApiResult deleteReceipt(Receipt receipt);

    ApiResult getReceipt(Receipt receipt);

    ApiResult getReceipts(ReceiptPage receiptPage);
}
