package com.kcm.modules.system.code.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.code.dao.SysCodeMapper;
import com.kcm.modules.system.code.entity.SysTCodeInfor;
import com.kcm.modules.system.code.service.SysCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 编码详细信息表(SYS_T_CODE_INFOR)表服务实现类
 *
 * @author zhaoqingwang
 * @date 2020/08/04
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class SysCodeServiceImpl extends ServiceImpl<SysCodeMapper, SysTCodeInfor> implements SysCodeService {


    private final  SysCodeMapper sysCodeMapper;

    /**
     * 新增一条编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息集
     * @return 新增结果
     */
    @Override
    public AjaxResult insert(SysTCodeInfor sysTCodeInfor){
        try{
            //默认为1，有效
                sysTCodeInfor.setActive("1");
            sysCodeMapper.insert(sysTCodeInfor);
            return AjaxResult.success(ResultCode.SUCCESS_ADD);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键更新编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息集
     * @return 修改结果
     */
    @Override
    public AjaxResult updateByPrimary(SysTCodeInfor sysTCodeInfor){
        try{
            sysCodeMapper.updateByPrimary(sysTCodeInfor);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键查询编码详细信息
     *
     * @param codeId 编码Id
     * @return 查询结果
     */
    @Override
    public AjaxResult selectByPrimary(String codeId){
        try {
            SysTCodeInfor sysTCodeInfor =sysCodeMapper.selectByPrimary(codeId);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysTCodeInfor);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过名称查询编码详细信息
     *
     * @param codeName 名称
     * @return 查询结果
     */
    @Override
    public AjaxResult selectByCodeName(String codeName){
        try {
            SysTCodeInfor sysTCodeInfor =sysCodeMapper.selectByCodeName(codeName);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysTCodeInfor);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过编码类型表中编码名称查询编码详细信息
     *
     * @param codeTName 编码类型表中编码类型名称
     * @return 查询结果
     */
    @Override
    public AjaxResult selectBycodeTName(String codeTName){
        try {
            List<SysTCodeInfor> sysTCodeInfor =sysCodeMapper.selectBycodeTName(codeTName);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysTCodeInfor);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有编码详细信息
     *
     * @return 查询结果
     */
    @Override
    public AjaxResult selectAll(){
        try {
            List<SysTCodeInfor> sysTCodeInfors =sysCodeMapper.selectAll();
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysTCodeInfors);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键删除编码详细信息
     *
     * @param codeId 编码Id
     * @return 删除结果
     */
    @Override
    public AjaxResult deleteByPrimary(String codeId){
        try {
            sysCodeMapper.deleteByPrimary(codeId);
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     *
     * @param codeIds 前端传回的编码Id集
     * @return 删除结果
     */
    @Override
    public AjaxResult deleteAllByPick(List<String> codeIds) {
        try {
            for (String codeId:codeIds) {
                sysCodeMapper.deleteByPrimary(codeId);
            }
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 批量新增
     * @param sysTCodeInforList 编码信息列表
     * @return 新增结果
     * @throws Exception 批量插入失败！
     */
    @Override
    public List<SysTCodeInfor> insertBatch(List<SysTCodeInfor> sysTCodeInforList) throws Exception {
        boolean result = saveBatch(sysTCodeInforList);
        if (result){
            return sysTCodeInforList;
        }else {
            throw new Exception("批量插入失败！");
        }
    }

    /**
     * 批量修改编码详细信息
     * @param sysTCodeInforlist 编码详细信息列表
     * @return 修改结果
     * @throws Exception 批量修改失败！
     */
    @Override
    public List<SysTCodeInfor> updateBatch(List<SysTCodeInfor> sysTCodeInforlist) throws Exception {
        boolean result = updateBatchById(sysTCodeInforlist);
        if (result){
            return sysTCodeInforlist;
        }else {
            throw  new Exception("批量修改失败！");
        }
    }
}
