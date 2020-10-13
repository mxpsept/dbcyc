package com.kcm.modules.system.code.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.code.entity.SysCodeType;
import com.kcm.modules.system.code.vo.SysCodeTypeVo;

import java.util.List;


/**
 * 编码类型表（SYS_T_CODE_TYPE_INFOR）表服务层接口
 *
 * @author zhaoqingwang
 * @date 2020/08/04
 * @version 1.0
 */
public interface SysCodeTypeService extends IService<SysCodeType> {

    /**
     *新增一个编码类型
     *
     * @param sysCodeTypeVo 编码类型类
     * @return 新增结果
     */
    SysCodeTypeVo insertCodeType(SysCodeTypeVo sysCodeTypeVo) throws Exception;

    /**
     * 通过主键查询编码类型信息
     *
     * @param codeTypeId 主键编码类型Id
     * @return 查询结果
     */
    AjaxResult selectByPrimary(String codeTypeId);

    /**
     * 查询所有编码类型信息
     *
     * @return 查询结果
     */
    AjaxResult selectAll();

    /**
     * 通过主键修改编码类型信息以及对应的编码详细信息
     *
     * @param sysCodeTypeVo 编码类型集
     * @return 修改结果
     */
    SysCodeTypeVo updateByPrimary(SysCodeTypeVo sysCodeTypeVo) throws Exception;

    /**
     * 通过主键更新编码类型信息
     *
     * @param sysCodeType 需要传入的数据类型类
     * @return 影响行数
     */
    boolean update(SysCodeType sysCodeType);
    /**
     * 通过主键删除
     *
     * @param codeTypeId 主键编码类型Id
     * @return 删除结果
     */
    AjaxResult deleteByPrimary(String codeTypeId);


    /**
     * 批量删除
     *
     * @param codeTypeIds 前端选择的复选框返回列表
     * @return 删除结果
     */
    AjaxResult deleteAllByPick(List<String> codeTypeIds);

    /**
     *查询编码类型总数
     *
     * @return 编码类型总数
     */
    AjaxResult queryCount();

    /**
     * 分页查询
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 查询结果
     */
    Page<SysCodeTypeVo> queryByPage(Integer current, Integer pageSize);


    /**
     * 条件查询（分页插件）
     *
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @param codeType 编码类型
     * @param codeTName 编码类型名称
     * @return 查询结果
     */
    AjaxResult selectByTerm(String codeTName, String codeType,Integer current,Integer pageSize);

    /**
     * 通过主键查询编码类型和该类型下编码详细信息
     * @param sysCodeTypeId 编码类型ID
     * @return 编码类型VO
     */
    SysCodeTypeVo selectBySysCodeTypeId(String sysCodeTypeId);
}
