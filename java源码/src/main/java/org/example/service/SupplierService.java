package org.example.service;

import org.example.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.page.SupplierPage;
import org.example.utils.ApiResult;

/**
* @author 陈鸿凯
* @description 针对表【supplier】的数据库操作Service
* @createDate 2023-11-25 22:34:29
*/
public interface SupplierService extends IService<Supplier> {

    ApiResult getSuppliers(SupplierPage supplierPage);

    ApiResult addSuppliers(Supplier supplier);

    ApiResult deleteSuppliers(Supplier supplier);

    ApiResult getSupplierAll();

    ApiResult getSupplier(Supplier supplier);

    ApiResult updateSupplier(Supplier supplier);
}
