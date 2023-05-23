package top.hueng.apm.service.impl;

import top.hueng.apm.converter.StorageConverter;
import top.hueng.apm.model.StorageVO;
import top.hueng.apm.service.DiskService;
import top.hueng.apm.service.RamService;
import top.hueng.apm.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: StorageImpl
 * Package: top.hueng.apm.service.impl
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/5/16 - 9:20
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private DiskService diskService;
    @Autowired
    private RamService ramService;
    private final StorageConverter storageConverter;

    @Override
    public StorageVO getStorageVO() {
        return storageConverter.toStorageVO(diskService.getDiskInfo(), ramService.getRamInfo());
    }
}

