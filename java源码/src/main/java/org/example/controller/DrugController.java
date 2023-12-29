package org.example.controller;

import org.example.pojo.Drug;
import org.example.pojo.page.DrugPage;
import org.example.service.DrugService;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @模块文件夹：DrugController
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.25.20:12
 */

@CrossOrigin
@RestController
@RequestMapping("drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @PostMapping("getDrugs")
    public ApiResult getDrugs(@RequestBody DrugPage drugPage){
        return drugService.getDrugs(drugPage);
    }

    @PostMapping("addDrug")
    public ApiResult addDrug(@RequestBody Drug drug){
        return drugService.addDrug(drug);
    }

    @PostMapping("addDrugImage")
    public ApiResult addDrugImage(@RequestParam Object drugId ,@RequestParam("file") MultipartFile file){
        return drugService.addDrugImage(drugId,file);
    }

    @PostMapping("deleteDrug")
    public ApiResult deleteDrug(@RequestBody Drug drug){
        return drugService.deleteDrug(drug);
    }

    @PostMapping("getDrug")
    public ApiResult getDrug(@RequestBody Drug drug){
        return drugService.getDrug(drug);
    }

    @PostMapping("updateDrug")
    public ApiResult updateDrug(@RequestBody Drug drug){
        return drugService.updateDrug(drug);
    }

}
