package org.example.controller;

import org.example.pojo.Supplier;
import org.example.pojo.page.SupplierPage;
import org.example.service.SupplierService;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @模块文件夹：SupplierController
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.25.22:35
 */

@CrossOrigin
@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping ("getSuppliers")
    public ApiResult getSuppliers(@RequestBody SupplierPage supplierPage){
        return supplierService.getSuppliers(supplierPage);
    }

    @GetMapping("getSupplierAll")
    public ApiResult getSupplierAll(){
        return supplierService.getSupplierAll();
    }

    @PostMapping("getSupplier")
    public ApiResult getSupplier(@RequestBody Supplier supplier){
        return supplierService.getSupplier(supplier);
    }

    @PostMapping("addSupplier")
    public ApiResult addSuppliers(@RequestBody Supplier supplier){
        return supplierService.addSuppliers(supplier);
    }

    @PostMapping("updateSupplier")
    public ApiResult updateSupplier(@RequestBody Supplier supplier){
        return supplierService.updateSupplier(supplier);
    }

    @PostMapping("deleteSupplier")
    public ApiResult deleteSuppliers(@RequestBody Supplier supplier){
        return supplierService.deleteSuppliers(supplier);
    }
}
