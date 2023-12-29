package org.example.service;

import org.example.pojo.Drug;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.page.DrugPage;
import org.example.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 陈鸿凯
* @description 针对表【drug】的数据库操作Service
* @createDate 2023-11-25 20:11:22
*/
public interface DrugService extends IService<Drug> {

    ApiResult getDrugs(DrugPage drugPage);

    ApiResult addDrug(Drug drug);

    ApiResult deleteDrug(Drug drug);

    ApiResult addDrugImage(Object drugId, MultipartFile file);

    ApiResult getDrug(Drug drug);

    ApiResult updateDrug(Drug drug);
}
