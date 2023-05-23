package top.hueng.apm.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: DiskServiceTest
 * Package: com.example.service
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/5/16 - 10:12
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class DiskServiceTest {
    @Autowired
    DiskService diskService;

    @Test
    public void getDiskInfoTest(){
        log.info("DiskInfo:{}",diskService.getDiskInfo());
    }

}
