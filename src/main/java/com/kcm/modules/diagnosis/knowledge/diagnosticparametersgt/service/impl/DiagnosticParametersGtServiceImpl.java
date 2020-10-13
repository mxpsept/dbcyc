package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.dao.DiagnosticParametersGtDao;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.DiagnosticParametersGt;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.DiagnosticParametersGtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 液量异常条件服务层实现类
 * @author  zhaoqingwang
 * @date  2020/9/21 15:34
 * @version  1.0
 **/
@Service
@RequiredArgsConstructor
public class DiagnosticParametersGtServiceImpl extends ServiceImpl<DiagnosticParametersGtDao, DiagnosticParametersGt> implements DiagnosticParametersGtService {

    private final DiagnosticParametersGtDao diagnosticParametersGtDao;

    /**
     * 修改功图诊断参数
     * @param diagnosticParametersGt 功图诊断参数实体
     * @return 数据库影响行数
     */
    @Override
    public Integer updateByEntity(DiagnosticParametersGt diagnosticParametersGt) {
        String s= diagnosticParametersGt.getDiagnosticBasis();
        s=s.replaceAll(";|；","<br/>");
        s=s.replaceAll("(<br/>)+","<br/>");
        diagnosticParametersGt.setDiagnosticBasis(s);
        return diagnosticParametersGtDao.update(diagnosticParametersGt,new QueryWrapper<DiagnosticParametersGt>()
                .eq("DIAGNOSTIC_STEP",diagnosticParametersGt.getDiagnosticStep())
                .eq("ORDER_NUMBER",diagnosticParametersGt.getOrderNumber()));
    }

    /**
     * 查询所有功图诊断参数
     * @return 查询结果
     */
    @Override
    public List<DiagnosticParametersGt> selectAll() {
        return diagnosticParametersGtDao.selectAll();
    }
}
