package org.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.Drug;
import org.example.pojo.page.DrugPage;
import org.example.service.DrugService;
import org.example.mapper.DrugMapper;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【drug】的数据库操作Service实现
* @createDate 2023-11-25 20:11:22
*/
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug>
    implements DrugService{

    @Autowired
    private DrugMapper drugMapper;

    @Override
    public ApiResult getDrugs(DrugPage drugPage) {

        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(drugPage.getKeyword()),Drug::getDname,drugPage.getKeyword())
                .eq(drugPage.getSid()!=null,Drug::getSid,drugPage.getSid());

        IPage<Drug> page = new Page<>(drugPage.getPageNum(),drugPage.getPageSize());

        IPage<Drug> drugList = drugMapper.selectPage(page, lambdaQueryWrapper);
        if(drugList == null){
            return ApiResult.error(null,700,"药品查询有误");
        }

        Map pageMap = new HashMap();
        pageMap.put("pageData",drugList.getRecords());
        pageMap.put("pageNum",drugList.getCurrent());
        pageMap.put("pageSize",drugList.getSize());
        pageMap.put("pageTotal",drugList.getPages());
        pageMap.put("totalSize",drugList.getTotal());


        return ApiResult.ok(pageMap);
    }

    @Override
    public ApiResult addDrug(Drug drug) {

        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Drug::getDname,drug.getDname());

        Drug drugSelect = drugMapper.selectOne(lambdaQueryWrapper);

        if(drugSelect != null){
            return ApiResult.error(null,701,"该药品已存在");
        }

        int insert = drugMapper.insert(drug);

        if(insert == 0){
            return ApiResult.error(null,700,"添加药品错误");
        }

        return ApiResult.ok(drug.getDid());

    }

    @Override
    public ApiResult deleteDrug(Drug drug) {

        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Drug::getDname,drug.getDname());

        int row = drugMapper.delete(lambdaQueryWrapper);

        if(row == 0){
            return ApiResult.error(null,702,"删除药品失败");
        }

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult addDrugImage(Object drugId, MultipartFile file) {

        if(file.isEmpty()){
            return ApiResult.error(null,703,"传入的图片为空");
        }

        String fileName = "drug_" + drugId + ".png";

        String path ="C:\\Users\\陈鸿凯\\Desktop\\毕业设计\\毕业设计项目代码\\drug-administration\\d1\\src\\assets\\drugImage\\";

        File dest =new File(path+fileName);
        System.out.println("dest = " + dest);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            return ApiResult.ok(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.error(null,704,"图片保存失败");
        }
    }

    @Override
    public ApiResult getDrug(Drug drug) {

        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Drug::getDid,drug.getDid());

        Drug drugSelect = drugMapper.selectOne(lambdaQueryWrapper);

        if(drugSelect == null){
            return ApiResult.error(null,700,"查询不到该药品");
        }

        Map drugMap = new HashMap();
        drugMap.put("drugInfo",drugSelect);

        return ApiResult.ok(drugMap);

    }

    @Override
    public ApiResult updateDrug(Drug drug) {

        if(drug.getDid() == null){
            return ApiResult.error(null,705,"没有药品id无法修改");
        }

        int row = drugMapper.updateById(drug);

        if(row == 0){
            return ApiResult.error(null,705,"修改失败");
        }

        return ApiResult.ok(null);
    }
}




