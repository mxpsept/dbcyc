package com.kcm.modules.system.code.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.code.entity.SysTCodeInfor;

import java.util.List;


/**
 * 编码详细信息表（SYS_T_CODE_INFOR）表服务层接口
 *
 * @author zhaoqingwang
 * @date 2020/08/04
 * @version 1.0
 */
public interface SysCodeService extends IService<SysTCodeInfor> {

    /**
     * 新增一条编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息
     * @return 新增结果
     */
    AjaxResult insert(SysTCodeInfor sysTCodeInfor);

    /**
     * 通过主键修改编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息
     * @return 修改结果
     */
    AjaxResult updateByPrimary(SysTCodeInfor sysTCodeInfor);

    /**
     * 通过主键查询编码详细信息
     *
     * @param codeId 编码Id
     * @return 查询结果
     */
    AjaxResult selectByPrimary(String codeId);

    /**
     * 通过名称查询编码详细信息
     *
     * @param codeName 名称
     * @return 查询结果
     */
    AjaxResult selectByCodeName(String codeName);

    /**
     * 通过编码类型表中编码名称查询编码详细信息
     *
     * @param codeTName 编码类型名称
     * @return 查询结果
     */
    AjaxResult selectBycodeTName(String codeTName);

    /**
     * 查询所有编码详细信息
     * @return 查询结果
     */
    AjaxResult selectAll();

    /**
     * 通过主键删除编码详细信息
     *
     * @param codeId 编码Id
     * @return 删除结果
     */
    AjaxResult deleteByPrimary(String codeId);


    /**
     * 批量删除
     * @param codeIds 前端传回的编码Id集
     * @return 删除结果
     */
    AjaxResult deleteAllByPick(List<String> codeIds);


    /**
     * 批量新增
     * @param sysTCodeInforList 编码信息列表
     * @return 新增结果
     * @throws Exception
     */
    List<SysTCodeInfor> insertBatch(List<SysTCodeInfor> sysTCodeInforList) throws Exception;


    /**
     * 批量修改编码详细信息
     * @param sysTCodeInforlist 编码详细信息列表
     * @return 修改结果
     * @throws Exception 批量修改失败！
     */
    List<SysTCodeInfor> updateBatch(List<SysTCodeInfor> sysTCodeInforlist) throws Exception;

}
