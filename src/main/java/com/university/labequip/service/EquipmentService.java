package com.university.labequip.service;

import com.university.labequip.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.university.labequip.entity.vo.EquipmentRequestVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
public interface EquipmentService extends IService<Equipment> {

    List<Equipment> selectEquipment(EquipmentRequestVo equipmentRequestVo);
}
