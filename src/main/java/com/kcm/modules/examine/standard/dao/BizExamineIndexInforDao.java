package com.kcm.modules.examine.standard.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.vo.BizDetailInfoListPageVo;
import com.kcm.modules.examine.standard.vo.BizIndexInforVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 考核指标信息表 Mapper 接口
 * </p>
 *
 * @author xublu
 * @since 2020-08-24
 */
public interface BizExamineIndexInforDao extends BaseMapper<BizExamineIndexInfor> {

    int insertSelect(BizExamineIndexInfor record);

    Page<BizDetailInfoListPageVo> queryByPageAll(Page page);

    Page<BizIndexInforVo> queryByPageAndConAll(@Param("indexName") String indexName,Page page);
}