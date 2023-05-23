package top.hueng.apm.model;

import top.hueng.apm.entity.Disk;
import top.hueng.apm.entity.Ram;
import lombok.Data;

/**
 * ClassName: StorageVO
 * Package: top.hueng.apm.model
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/5/16 - 9:07
 * @Version: 1.0
 */
@Data
public class StorageVO {
    private Disk disk;
    private Ram ram;
}
