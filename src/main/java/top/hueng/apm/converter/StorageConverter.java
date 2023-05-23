package top.hueng.apm.converter;

import top.hueng.apm.entity.Disk;
import top.hueng.apm.entity.Ram;
import top.hueng.apm.model.StorageVO;
import org.mapstruct.Mapper;

/**
 * ClassName: StorageConverter
 * Package: top.hueng.apm.converter
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/5/16 - 9:15
 * @Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface StorageConverter {

    StorageVO toStorageVO(Disk disk, Ram ram);

}
