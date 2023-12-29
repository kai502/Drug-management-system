package org.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.Supplier;
import org.example.pojo.page.SupplierPage;
import org.example.service.SupplierService;
import org.example.mapper.SupplierMapper;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 陈鸿凯
* @description 针对表【supplier】的数据库操作Service实现
* @createDate 2023-11-25 22:34:29
*/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
    implements SupplierService{

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public ApiResult getSuppliers(SupplierPage supplierPage) {

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(supplierPage.getKeyword()),Supplier::getSname,supplierPage.getKeyword());

        IPage<Supplier> Page = new Page<>(supplierPage.getPageNum(),supplierPage.getPageSize());

        IPage<Supplier> supplierIPage = supplierMapper.selectPage(Page, lambdaQueryWrapper);

        if(supplierMapper == null){
            return ApiResult.error(null,800,"获取供应商列表错误");
        }

        Map supplierMap = new HashMap();
        supplierMap.put("pageData",supplierIPage.getRecords());
        supplierMap.put("pageNum",supplierIPage.getCurrent());
        supplierMap.put("pageTotal",supplierIPage.getTotal());
        supplierMap.put("pageSize",supplierIPage.getSize());

        return ApiResult.ok(supplierMap);

    }

    @Override
    public ApiResult addSuppliers(Supplier supplier) {

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getSname,supplier.getSname());

        Supplier supplierSelect = supplierMapper.selectOne(lambdaQueryWrapper);

        if(supplierSelect != null){
            return ApiResult.error(null,801,"供应商已经存在");
        }

        supplierMapper.insert(supplier);

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult deleteSuppliers(Supplier supplier) {

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getSname,supplier.getSname());

        int row = supplierMapper.delete(lambdaQueryWrapper);

        if(row == 0 ){
            return ApiResult.error(null,802,"删除供应商失败");
        }

        return ApiResult.ok(null);
    }

    @Override
    public ApiResult getSupplierAll() {

        List<Supplier> suppliers = supplierMapper.selectList(null);

        return ApiResult.ok(suppliers);

    }

    @Override
    public ApiResult getSupplier(Supplier supplier) {

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getSid,supplier.getSid());

        Supplier supplierSelect = supplierMapper.selectOne(lambdaQueryWrapper);

        Map supplierMap = new HashMap();
        supplierMap.put("sid",supplierSelect.getSid());
        supplierMap.put("sname",supplierSelect.getSname());
        supplierMap.put("phoneNumber",supplierSelect.getPhoneNumber());

        return ApiResult.ok(supplierMap);

    }

    @Override
    public ApiResult updateSupplier(Supplier supplier) {

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getSname,supplier.getSname());

        Supplier supplierSelect = supplierMapper.selectOne(lambdaQueryWrapper);

        if(supplierSelect != null && !supplierSelect.getSid().equals(supplier.getSid()) ){
            return ApiResult.error(null,803,"该供应名称已经存在");
        }

        int row = supplierMapper.updateById(supplier);

        if(row == 0){
            return ApiResult.error(null,804,"修改供应商错误");
        }

        return ApiResult.ok(null);

    }
}




