package com.university.labequip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.university.labequip.entity.Equipment;
import com.university.labequip.entity.vo.EquipmentRequestVo;
import com.university.labequip.mapper.EquipmentMapper;
import com.university.labequip.service.EquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

    @Resource
    EquipmentMapper equipmentMapper;

    //不分页版
    @Override
    public List<Equipment> selectEquipment(EquipmentRequestVo equipmentRequestVo) {
        QueryWrapper<Equipment> equipmentQueryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getEquipmentType()))
            equipmentQueryWrapper.eq("equipment_type",equipmentRequestVo.getEquipmentType());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getBrand()))
            equipmentQueryWrapper.like("brand",equipmentRequestVo.getBrand());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getModel()))
            equipmentQueryWrapper.like("model",equipmentRequestVo.getModel());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getSpecification()))
            equipmentQueryWrapper.like("specification",equipmentRequestVo.getSpecification());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getUnitPrice()))
            equipmentQueryWrapper.eq("unit_price",equipmentRequestVo.getUnitPrice());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPurchaseDate()))
            equipmentQueryWrapper.eq("purchase_date",equipmentRequestVo.getPurchaseDate());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getRetirementDate()))
            equipmentQueryWrapper.eq("retirement_date",equipmentRequestVo.getRetirementDate());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsRetired()))
            equipmentQueryWrapper.eq("is_retired",equipmentRequestVo.getIsRetired());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getUserId()))
            equipmentQueryWrapper.eq("user_id",equipmentRequestVo.getUserId());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPurchaserId()))
            equipmentQueryWrapper.eq("purchaser_id",equipmentRequestVo.getPurchaserId());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPlace()))
            equipmentQueryWrapper.eq("place",equipmentRequestVo.getPlace());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsRecorded()))
            equipmentQueryWrapper.eq("is_recorded",equipmentRequestVo.getIsRecorded());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsAssigned()))
            equipmentQueryWrapper.eq("is_assigned",equipmentRequestVo.getIsAssigned());
        return equipmentMapper.selectList(equipmentQueryWrapper);
    }

    //分页且按property排序的设备查询
    @Override
    public Page<Equipment> perPageByOrder(long current, long limit, EquipmentRequestVo equipmentRequestVo, String property) {
        QueryWrapper<Equipment> equipmentQueryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getEquipmentType()))
            equipmentQueryWrapper.eq("equipment_type",equipmentRequestVo.getEquipmentType());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getBrand()))
            equipmentQueryWrapper.like("brand",equipmentRequestVo.getBrand());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getModel()))
            equipmentQueryWrapper.like("model",equipmentRequestVo.getModel());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getSpecification()))
            equipmentQueryWrapper.like("specification",equipmentRequestVo.getSpecification());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getUnitPrice()))
            equipmentQueryWrapper.eq("unit_price",equipmentRequestVo.getUnitPrice());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPurchaseDate()))
            equipmentQueryWrapper.eq("purchase_date",equipmentRequestVo.getPurchaseDate());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getRetirementDate()))
            equipmentQueryWrapper.eq("retirement_date",equipmentRequestVo.getRetirementDate());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsRetired()))
            equipmentQueryWrapper.eq("is_retired",equipmentRequestVo.getIsRetired());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getUserId()))
            equipmentQueryWrapper.eq("user_id",equipmentRequestVo.getUserId());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPurchaserId()))
            equipmentQueryWrapper.eq("purchaser_id",equipmentRequestVo.getPurchaserId());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getPlace()))
            equipmentQueryWrapper.eq("place",equipmentRequestVo.getPlace());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsRecorded()))
            equipmentQueryWrapper.eq("is_recorded",equipmentRequestVo.getIsRecorded());
        if (!ObjectUtils.isEmpty(equipmentRequestVo.getIsAssigned()))
            equipmentQueryWrapper.eq("is_assigned",equipmentRequestVo.getIsAssigned());
        equipmentQueryWrapper.orderByDesc(property);
        Page< Equipment > equipmentPage = new Page<>(current,limit);
        equipmentMapper.selectPage(equipmentPage,equipmentQueryWrapper);
        return equipmentPage;
    }

    @Override
    public List<Map<String, Object>> countByGroup(String property) {
        QueryWrapper<Equipment> equipmentQueryWrapper = new QueryWrapper<>();
        equipmentQueryWrapper.select(property+" property", "count(*) count")
                             .groupBy(property);
        return equipmentMapper.selectMaps(equipmentQueryWrapper);
    }
}
