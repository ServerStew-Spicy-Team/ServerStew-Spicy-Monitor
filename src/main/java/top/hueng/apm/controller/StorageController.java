package top.hueng.apm.controller;

import top.hueng.apm.common.CommonResult;
import top.hueng.apm.model.StorageVO;
import top.hueng.apm.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: StorageController
 * Package: top.hueng.apm.controller
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/5/16 - 9:06
 * @Version: 1.0
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping("/")
    public CommonResult<StorageVO> getStorageInfo(){
        return CommonResult.success(storageService.getStorageVO(),"请求成功");
    }
}
