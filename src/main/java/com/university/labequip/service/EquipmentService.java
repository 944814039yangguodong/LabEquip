package com.university.labequip.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.university.labequip.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.university.labequip.entity.vo.EquipmentRequestVo;

import java.util.List;
import java.util.Map;

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

    Page<Equipment> perPageByOrder(long current, long limit, EquipmentRequestVo equipmentRequestVo, String property);

    List<Map<String, Object>> countByGroup(String property);

    List<Map<String, Object>> countByGroupRetired(String property);

    Page<Equipment> perPageByOrderExceptMe(long current, long limit, EquipmentRequestVo equipmentRequestVo, String property, String myId);
}
