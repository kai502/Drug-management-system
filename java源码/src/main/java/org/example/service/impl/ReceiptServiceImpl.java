package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.DrugMapper;
import org.example.pojo.Drug;
import org.example.pojo.Receipt;
import org.example.pojo.page.ReceiptPage;
import org.example.service.ReceiptService;
import org.example.mapper.ReceiptMapper;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【receipt】的数据库操作Service实现
* @createDate 2023-11-30 21:27:59
*/
@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt>
    implements ReceiptService{

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public ApiResult outInReceipt(Receipt receipt) {

        //receipt.setType(0);

        int row = receiptMapper.insert(receipt);

        if(row == 0){
            return ApiResult.error(null,1000,"添加出库记录失败");
        }

        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Drug::getDid,receipt.getDid());

        Drug drugSelect = drugMapper.selectOne(lambdaQueryWrapper);
        Integer drugNumber = drugSelect.getNumber();

        Drug drug = new Drug();
        drug.setDid(receipt.getDid());
        if(receipt.getType() == 0){
            drug.setNumber(drugNumber - receipt.getNumber());
        }else{
            drug.setNumber(drugNumber + receipt.getNumber());
        }

        drugMapper.updateById(drug);

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult deleteReceipt(Receipt receipt) {

        int row = receiptMapper.deleteById(receipt);

        if(row == 0 ){
            return ApiResult.error(null,1001,"删除出入库记录失败");
        }

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult getReceipt(Receipt receipt) {

        LambdaQueryWrapper<Receipt> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Receipt::getRid,receipt.getRid());

        Receipt receiptSelect = receiptMapper.selectOne(lambdaQueryWrapper);

        if(receiptSelect == null){
            return ApiResult.error(null, 1002,"找不到对应的单据");
        }

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult getReceipts(ReceiptPage receiptPage) {

        IPage<Receipt> page =new Page<>(receiptPage.getPageNum(),receiptPage.getPageSize());

        receiptMapper.selectReceipts(page,receiptPage);

        Map pageMap = new HashMap();
        pageMap.put("pageData",page.getRecords());
        pageMap.put("pageTotal",page.getTotal());

        return ApiResult.ok(pageMap);

    }


}




