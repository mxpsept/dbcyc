package com.kcm.modules.system.code.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.system.code.dao.SysCodeMapper;
import com.kcm.modules.system.code.dao.SysCodeTypeMapper;
import com.kcm.modules.system.code.entity.SysCodeType;
import com.kcm.modules.system.code.entity.SysTCodeInfor;
import com.kcm.modules.system.code.service.SysCodeService;
import com.kcm.modules.system.code.service.SysCodeTypeService;
import com.kcm.modules.system.code.vo.SysCodeTypeVo;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.vo.SysRoleVo;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.regex.Match;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 编码类型表（SYS_T_CODE_TYPE_INFOR)表服务实现类
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/04
 */
@Service
@RequiredArgsConstructor
public class SysCodeTypeServiceImpl extends ServiceImpl<SysCodeTypeMapper, SysCodeType> implements SysCodeTypeService {

    private final SysCodeTypeMapper sysCodeTypeMapper;
    private final SysCodeService sysCodeService;
    private final SysCodeMapper sysCodeMapper;


    /**
     * 新增编码类型
     *
     * @param sysCodeTypeVo 编码类型类
     * @return 新增结果
     */
    @Override
    public SysCodeTypeVo insertCodeType(SysCodeTypeVo sysCodeTypeVo) throws Exception {
        SysCodeType sysCodeTypeCope = new SysCodeType();
        sysCodeTypeVo.setCodeTypeId(StringUtils.uuid());
        sysCodeTypeVo.setActive("1");
        BeanUtils.copyProperties(sysCodeTypeVo, sysCodeTypeCope);
        boolean result = save(sysCodeTypeCope);
        for (SysTCodeInfor sysTCodeInfor : sysCodeTypeVo.getSysTCodeInforList()) {
            sysTCodeInfor.setCodeId(StringUtils.uuid());
            sysTCodeInfor.setActive("1");
            sysTCodeInfor.setCodeTId(sysCodeTypeVo.getCodeTypeId());
        }
        sysCodeService.insertBatch(sysCodeTypeVo.getSysTCodeInforList());
        if (result) {
            return selectBySysCodeTypeId(sysCodeTypeCope.getCodeTypeId());
        } else {
            throw new Exception("新增编码类型失败！");
        }
    }


    /**
     * 通过主键查询编码类型信息
     *
     * @param codeTypeId 主键编码类型Id
     * @return 查询结果
     */
    @Override
    public AjaxResult selectByPrimary(String codeTypeId) {
        try {
            SysCodeType sysCodeType = sysCodeTypeMapper.selectByPrimary(codeTypeId);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysCodeType);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 查询所有编码类型信息
     *
     * @return 查询结果
     */
    @Override
    public AjaxResult selectAll() {
        try {
            List<SysCodeType> sysCodeType = sysCodeTypeMapper.selectAll();
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysCodeType);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键更新编码类型信息以及对应编码详细信息
     *
     * @param sysCodeTypeVo 编码类型结果集
     * @return 影响行数
     */
    @Override
    public SysCodeTypeVo updateByPrimary(SysCodeTypeVo sysCodeTypeVo) throws  Exception{
        SysCodeType sysCodeType = new SysCodeType();
        BeanUtils.copyProperties(sysCodeTypeVo,sysCodeType);
        boolean result= updateById(sysCodeType);
        sysCodeService.updateBatch(sysCodeTypeVo.getSysTCodeInforList());
        if (result){
            return selectBySysCodeTypeId(sysCodeTypeVo.getCodeTypeId());
        }    else {
            throw new Exception("批量修改失败！");
        }
    }


    /**
     * 通过主键更新编码类型信息
     *
     * @param sysCodeType 需要传入的数据类型类
     * @return 影响行数
     */
    public boolean update(SysCodeType sysCodeType) {
        Integer result =sysCodeTypeMapper.updateByPrimary(sysCodeType);
        if (result==1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 通过主键删除编码类型以及对应次编码类型下所有编码详细信息
     *
     * @param codeTypeId 编码类型Id
     * @return 影响行数
     */
    @Override
    public AjaxResult deleteByPrimary(String codeTypeId) {
        try {
            String codeTName = sysCodeTypeMapper.selectByPrimary(codeTypeId).getcodeTName();
            List<SysTCodeInfor> sysTCodeInfors = sysCodeMapper.selectBycodeTName(codeTName);
            for (SysTCodeInfor syscode : sysTCodeInfors
            ) {
                sysCodeMapper.deleteByPrimary(syscode.getCodeId());
            }
            sysCodeTypeMapper.deleteByPrimary(codeTypeId);
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 批量删除
     *
     * @param codeTypeIds 前端选择的复选框返回列表
     * @return 影响行数
     */
    @Override
    public AjaxResult deleteAllByPick(List<String> codeTypeIds) {
        try {
            for (String codeTypeId : codeTypeIds) {
                sysCodeTypeMapper.deleteByPrimary(codeTypeId);
            }
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询编码类型总数
     *
     * @return 编码类型总数
     */
    @Override
    public AjaxResult queryCount() {
        try {
            Integer count = sysCodeTypeMapper.queryCount();
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, count);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @return 查询结果
     */
    @Override
    public Page<SysCodeTypeVo> queryByPage(Integer current, Integer pageSize) {
        Page<SysCodeTypeVo> page = sysCodeTypeMapper.selectAllPage(new Page<>(current,pageSize));
        for (SysCodeTypeVo sysCodeTypeVo : page.getRecords()){
            if(!org.springframework.util.StringUtils.isEmpty(sysCodeTypeVo.getCodeIds())){
                sysCodeTypeVo.setSysTCodeInforList(sysCodeMapper.selectBatchIds(Arrays.asList(sysCodeTypeVo.getCodeIds().split(","))));
            }
        }
        return page;
    }

    /**
     * 条件查询（分页插件）
     *
     * @param current   当前页
     * @param pageSize  页面尺寸
     * @param codeType  编码类型
     * @param codeTName 编码类型名称
     * @return 查询结果
     */
    @Override
    public AjaxResult selectByTerm(String codeTName,String codeType,Integer current, Integer pageSize) {
        try {
            Page<SysCodeTypeVo> page = new Page<>(current, pageSize);
            List<SysCodeTypeVo> sysCodeTypeVoList = new ArrayList<>();
            Page<SysCodeType> page1 = sysCodeTypeMapper.selectPage(
                    new Page<SysCodeType>(current,pageSize),
                    new QueryWrapper<SysCodeType>()
                            .eq("ACTIVE",'1')
                            .like(!StringUtils.isEmpty(codeTName),"CODE_T_NAME",codeTName)
                            .like(!StringUtils.isEmpty(codeType),"CODE_TYPE",codeType)
            );
            for (SysCodeType sysCodeType : page1.getRecords()) {
                SysCodeTypeVo sysCodeTypeVo = new SysCodeTypeVo();
                BeanUtils.copyProperties(sysCodeType,sysCodeTypeVo);
                List<SysTCodeInfor> sysTCodeInforList = sysCodeMapper.selectList(new QueryWrapper<SysTCodeInfor>().eq("CODE_T_ID",sysCodeType.getCodeTypeId()));
                sysCodeTypeVo.setSysTCodeInforList(sysTCodeInforList);
                sysCodeTypeVoList.add(sysCodeTypeVo);
                page.setRecords(sysCodeTypeVoList);
            }
            BeanUtils.copyProperties(page1,page);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, page);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }



    /**
     * 通过主键查询编码类型和该类型下编码详细信息
     * @param sysCodeTypeId 编码类型ID
     * @return 编码类型VO
     */
    @Override
    public SysCodeTypeVo selectBySysCodeTypeId(String sysCodeTypeId) {
        SysCodeTypeVo resultVo = new SysCodeTypeVo();
        BeanUtils.copyProperties(sysCodeTypeMapper.selectByPrimary(sysCodeTypeId), resultVo);
        resultVo.setSysTCodeInforList(sysCodeMapper.selectList(new QueryWrapper<SysTCodeInfor>().eq("CODE_T_ID", sysCodeTypeId).eq("ACTIVE","1")));
        return resultVo;
    }
}
