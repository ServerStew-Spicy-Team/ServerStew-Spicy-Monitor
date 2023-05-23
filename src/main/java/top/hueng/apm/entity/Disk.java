package top.hueng.apm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: Disk
 * Package: top.hueng.apm.entity
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/14 - 23:44
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disk {
    private Integer id;
    //总容量
    private Integer total;
    //已使用
    private Integer used;
    //可获得
    private Integer available;
    //使用比例
    private Integer usedRate;
    //创建时间
    private Date createdTime;

    public Disk(Integer total, Integer used, Integer available, Integer usedRate, Date createdTime) {
        this.total = total;
        this.used = used;
        this.available = available;
        this.usedRate = usedRate;
        this.createdTime = createdTime;
    }
}
