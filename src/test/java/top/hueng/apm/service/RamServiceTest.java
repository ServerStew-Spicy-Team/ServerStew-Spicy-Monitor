package top.hueng.apm.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: RamServiceTest
 * Package: com.example.service
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/11 - 8:43
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class RamServiceTest {

    @Autowired
    RamService ramService;

    @Test
    public void getRamInfoTest(){
        log.info("Ram:{}",ramService.getRamInfo());
    }

}
