package com.kcm.modules.system.employee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.system.employee.entity.SysPositionEntity;


/**
 * <p>
 * 职务信息表 服务类
 * </p>
 *
 * @author xublu
 * @since 2020-08-12
 */
public interface SysPositionService extends IService<SysPositionEntity> {
    /**
     * 岗位管理分页查询
     * @param pageNum 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
     IPage<SysPositionEntity> findAllByPage(Integer pageNum, Integer size);

    /**
     * 分页 + 条件查询
     * @param positionName 职位实体类
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @return 查询结果
     */
     IPage<SysPositionEntity> findAllByPage(String positionName, Integer pageNum, Integer size);

    /**
     * 新增数据
     * @param entity 数据实体类
     */
     void insert(SysPositionEntity entity);

    /**
     * 批量逻辑删除
     * @param ids ID集合
     */
     void deleteById(String[] ids);

}
