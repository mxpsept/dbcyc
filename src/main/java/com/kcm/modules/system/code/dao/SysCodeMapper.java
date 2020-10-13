package com.kcm.modules.system.code.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.system.code.entity.SysTCodeInfor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 编码详细信息表（SYS_T_CODE_INFOR）数据库访问接口
 *
 * @author zhaoqingwang
 * @date 2020/08/05
 * @version 1.0
 */
@Mapper
public interface SysCodeMapper extends BaseMapper<SysTCodeInfor> {


    /**
     * 通过主键更新编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息
     * @return 影响行数
     */
    Integer updateByPrimary(SysTCodeInfor sysTCodeInfor);

    /**
     * 通过主键查询编码详细信息
     *
     * @param codeId 编码Id
     * @return 查询结果
     */
    SysTCodeInfor selectByPrimary(String codeId);

    /**
     * 通过名称查询编码详细信息
     *
     * @param codeName 名称
     * @return 查询结果
     */
    SysTCodeInfor selectByCodeName(String codeName);

    /**
     * 通过编码类型表中编码名称查询编码详细信息
     *
     * @param codeTName 编码类型表中的编码类型名称
     * @return 查询结果集
     */
    List<SysTCodeInfor> selectBycodeTName(String codeTName);


    /**
     * 查询所有编码详细信息
     * @return 查询结果
     */
    List<SysTCodeInfor> selectAll();

    /**
     * 通过主键删除编码详细信息
     *
     * @param codeId 编码Id
     * @return 影响行数
     */
    Integer deleteByPrimary(String codeId);
}
