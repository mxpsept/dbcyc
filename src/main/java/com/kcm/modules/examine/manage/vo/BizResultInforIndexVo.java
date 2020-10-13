package com.kcm.modules.examine.manage.vo;

import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 考核结果分页Vo中考核指标indexVo
 * @Author zhaoqingwang
 * @DATE 2020/9/15 10:34
 * @Version 1.0
 **/
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizResultInforIndexVo {

    private static final long serialVersionUID = 1L;

    private String indexId;

    private String indexName;

    private List<BizExamineIndexDetail> bizExamineIndexDetailList;
}
