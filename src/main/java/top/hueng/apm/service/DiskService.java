package top.hueng.apm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hueng.apm.entity.Disk;

/**
 * ClassName: DiskService
 * Package: top.hueng.apm.service
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/15 - 0:29
 * @Version: 1.0
 */
public interface DiskService extends IService<Disk> {
    public Disk getDiskInfo();
    public boolean addDisk(Disk disk);

}
