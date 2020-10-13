package com.kcm.modules.system.code.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.system.code.entity.SysCodeType;
import com.kcm.modules.system.code.vo.SysCodeTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据类型表（SYS_T_CODE_TYPE_INFOR）数据库访问接口
 *
 * @author zhaoqingwang
 * @date 2020/08/04
 * @version 1.0
 */
@Mapper
public interface SysCodeTypeMapper extends BaseMapper<SysCodeType> {



    /**
     * 通过主键查询编码类型信息
     *
     * @param codeTypeId 主键数据类型ID
     * @return 查询结果
     */
    SysCodeType selectByPrimary(String codeTypeId);



    /**
     * 查询所有编码类型
     *
     * @return 查询结果
     */
    List<SysCodeType> selectAll();

    /**
     * 通过主键更新编码类型信息
     *
     * @param sysCodeType 需要传入的数据类型类
     * @return 影响行数
     */
    Integer updateByPrimary(SysCodeType sysCodeType);

    /**
     * 通过主键删除
     *
     * @param codeTypeId 主键数据类型ID
     * @return 影响行数
     */
    Integer deleteByPrimary(String codeTypeId);

    /**
     *查询编码类型总数
     *
     * @return 编码类型总数
     */
    Integer queryCount();

    /**
     * 分页查询
     *
     * @param page 分页
     * @return 查询结果
     */
    Page<SysCodeTypeVo> selectAllPage(Page page);

    /**
     * 条件分页模糊查询
     *
     * @param codeTName 编码类型名称
     * @param codeType 编码类型
     * @param page 分页参数
     * @return 查询结果
     */
    Page<SysCodeTypeVo> selectByTerm(String codeTName,String codeType,Page page);

//    IPage<SysCodeType> selectByTerm1(Page<?> page,String codeType,String codeTName);
}
