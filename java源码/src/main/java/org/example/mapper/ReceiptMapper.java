package org.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Receipt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.page.ReceiptPage;

import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【receipt】的数据库操作Mapper
* @createDate 2023-12-02 14:51:25
* @Entity org.example.pojo.Receipt
*/
public interface ReceiptMapper extends BaseMapper<Receipt> {

    IPage<Map> selectReceipts(IPage<Receipt> page, @Param("ReceiptPage")ReceiptPage receiptPage);
}




