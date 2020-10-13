package com.kcm.modules.system.department.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.system.department.dao.SysDepartmentMapper;
import com.kcm.modules.system.department.entity.SysDepartment;
import com.kcm.modules.system.department.service.SysDepartmentService;
import com.kcm.modules.system.user.dao.SysUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *部门信息表（SYS_DEPARTMENT）服务实现类
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/03
 */
@Service
@RequiredArgsConstructor
public class SysDepartmentServiceImpl implements SysDepartmentService {

    private final SysDepartmentMapper sysDepartmentMapper;
    private final SysUserDao sysUserDao;

    /**
     * 新增全部部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    @Override
    public AjaxResult insertDepartment(SysDepartment sysDepartment){
        try{
            sysDepartment.setDepartmentId(StringUtils.uuid());
            if("string".equals(sysDepartment.getParentDepartmentId())||sysDepartment.getParentDepartmentId()==null||"".equals(sysDepartment.getParentDepartmentId())){
                sysDepartment.setParentDepartmentId("0");
            }
            if(sysDepartment.getActive()==null){
                sysDepartment.setActive("1");
            }
            sysDepartmentMapper.insertDepartment(sysDepartment);
            return AjaxResult.success(ResultCode.SUCCESS_ADD);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 新增部分或全部部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    @Override
    public AjaxResult insertSelective(SysDepartment sysDepartment){
        try{
            sysDepartmentMapper.insertSelective(sysDepartment);
            return AjaxResult.success(ResultCode.SUCCESS_ADD);
        } catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 修改部门全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    @Override
    public AjaxResult updateDepartmentByPrimaryKey(SysDepartment sysDepartment){
        try{
            sysDepartmentMapper.updateDepartmentByPrimaryKey(sysDepartment);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 修改部门全部或部分信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    @Override
    public AjaxResult updateSelective(SysDepartment sysDepartment){
        try{
            sysDepartmentMapper.updateSelective(sysDepartment);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE);
        } catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键查询部门信息
     *
     * @param departmentId 主键部门Id
     * @return 查询结果
     */
    @Override
    public AjaxResult selectByPrimary(String departmentId){
        try{
            SysDepartment sysDepartment=sysDepartmentMapper.selectByPrimary(departmentId);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,sysDepartment);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有部门信息
     * @return 查询结果
     */
    @Override
    public AjaxResult selectAll(){
        try {
            List<SysDepartment> sysDepartments=sysDepartmentMapper.selectAll();
            Collections.sort(sysDepartments,sequence());
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysDepartments);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 通过主键删除部门信息(批量删除，假删除)
     *
     * @param departmentId 主键部门Id
     * @return 删除结果
     */
    @Override
    public AjaxResult deleteByPrimary(String departmentId){
        try{
            sysDepartmentMapper.deleteByPrimary(departmentId);
            List<String> idlist = getAllChildDepartmentId(departmentId);
            for (String Id : idlist) {
                sysDepartmentMapper.deleteByPrimary(Id);
            }
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR);
        }
    }

    /**
     * 批量删除部门信息
     *
     * @param departmentIds 前端返回的部门Id集
     * @return 删除结果
     */
    @Override
    public AjaxResult deleteAllByPick(List<String> departmentIds) {
        try {
            for (String departmentId : departmentIds){
                deleteByPrimary(departmentId);
            }
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 通过父部门Id查询子部门
     *
     * @param parentDepartmentId 父部门Id
     * @return 子部门
     */
    @Override
     public AjaxResult  selectByParentId(String parentDepartmentId){
        try {
            SysDepartment sysDepartment=sysDepartmentMapper.selectByPrimary(parentDepartmentId);
            List<SysDepartment> sysDepartmentList =sysDepartmentMapper.selectByParentId(parentDepartmentId);
            sysDepartmentList.add(sysDepartment);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,sysDepartmentList);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }
    /**
     * 获取部门树
     *
     * @return 部门树
     */
    @Override
    public AjaxResult getDepartmentTree() {
        try {

            long start = System.currentTimeMillis();
            //查询所有部门信息，放入到sysDepartments
            List<SysDepartment> sysDepartments = sysDepartmentMapper.selectAll();
            //新建一个列表rootDepartments存放菜单树
            List<SysDepartment> rootDepartments = new ArrayList<>();
            //用增强for循环遍历sysDepartments，其中部门的父部门ID为0的即为根部门，将所有根部门添加到rootDepartments中
            for(SysDepartment sysDepartment:sysDepartments){
                if ("0".equals(sysDepartment.getParentDepartmentId())){
                    rootDepartments.add(sysDepartment);
                }
            }
            //按照显示顺序排序
            Collections.sort(rootDepartments, sequence());
            //获取根部门下子部门。
            //遍历所有部门，将每个部门ID和所有部门列表作为参数传入getChild方法中，将得到的子部门添加到部门的children中。
            for (SysDepartment sysDepartment:sysDepartments){
                List<SysDepartment> childList = getChild(sysDepartment.getDepartmentId(),sysDepartments);
                sysDepartment.setChildren(childList);
            }
            long end = System.currentTimeMillis();
            System.out.printf("运行时间为：%d\n",end-start);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,rootDepartments);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

//    @Override
//    public List<SysDepartmentVo> getDepartmentAndUser() {
//        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectAll();
//        List<SysDepartmentVo> sysDepartmentVoList = new ArrayList<>();
//        for (SysDepartment sysDepartment : sysDepartmentList) {
//            if ("0".equals(sysDepartment.getParentDepartmentId())){
//                SysDepartmentVo sysDepartmentVo = new SysDepartmentVo();
//                sysDepartmentVo.setDepartmentName(sysDepartment.getDepartmentName());
//                sysDepartmentVo.setUserList(sysUserDao.selectList(new QueryWrapper<SysUser>().eq("DEPARTMENT_ID",sysDepartment.getDepartmentId())));
//                sysDepartmentVo.setChildren(getChildVo(sysDepartment.getDepartmentId(),sysDepartmentList));
//                sysDepartmentVoList.add(sysDepartmentVo);
//            }
//        }
//        return sysDepartmentVoList;
//    }

    /**
     * 排序
     *
     * @return 显示排序
     */
    private Comparator<SysDepartment> sequence(){
        Comparator<SysDepartment> comparator = new Comparator<SysDepartment>() {

            @Override
            public int compare(SysDepartment o1,SysDepartment o2){
                if (!o1.getSequence().equals(o2.getSequence())){
                    return o1.getSequence().intValue() - o2.getSequence().intValue();
                }
                return 0;
            }
        };
        return comparator;
    }


    /**
     * 获取子部门列表
     *
     * @param departmentId 父部门ID
     * @param sysDepartments 需要在这个列表中查询
     * @return 子部门列表
     */
    private List<SysDepartment> getChild(String departmentId,List<SysDepartment> sysDepartments){
        //新建一个childList列表存放获得的子部门列表。
        List<SysDepartment> childList = new ArrayList<>();
        //遍历传入的部门列表，将所有父部门ID和传入的参数部门ID相同的部门添加到子部门列表中
        for(SysDepartment sysDepartment:sysDepartments){
            if(departmentId.equals(sysDepartment.getParentDepartmentId())){
                childList.add(sysDepartment);
            }
        }
        //递归。遍历子部门列表，将子部门列表中所有部门的部门ID和所有部门ID比较，获得对应部门下的子部门。
        for (SysDepartment sysDepartment : childList){
            sysDepartment.setChildren(getChild(sysDepartment.getDepartmentId(),sysDepartments));
        }
        //排序
        Collections.sort(childList,sequence());

        //退出递归
        if(childList.size() == 0){
            return new ArrayList<>();
        }
        //返回子部门列表
        return childList;
    }

//    private List<SysDepartmentVo> getChildVo(String departmentId,List<SysDepartment> sysDepartmentList){
//        List<SysDepartmentVo> sysDepartmentVos = new ArrayList<>();
//        for (SysDepartment sysDepartment : sysDepartmentList) {
//            if (departmentId.equals(sysDepartment.getParentDepartmentId())){
//                SysDepartmentVo sysDepartmentVo = new SysDepartmentVo();
//                sysDepartmentVo.setDepartmentName(sysDepartment.getDepartmentName());
//                sysDepartmentVo.setUserList(sysUserDao.selectList(new QueryWrapper<SysUser>().eq("DEPARTMENT_ID",sysDepartment.getDepartmentId())));
//                sysDepartmentVos.add(sysDepartmentVo);
//            }
//        }
//        for (SysDepartmentVo sysDepartmentVo : sysDepartmentVos) {
//            String name = sysDepartmentVo.getDepartmentName();
//            String Id =sysDepartmentMapper.selectByDepartmentName(name).getDepartmentId();
//            sysDepartmentVo.setChildren(getChildVo(Id,sysDepartmentList));
//        }
//        if (sysDepartmentVos.size()==0){
//            return new ArrayList<>();
//        }
//        return sysDepartmentVos;
//    }

    /**
     * 通过部门ID查询叶子单位部门
     *
     * @param parentId 部门ID
     * @return 查询结果
     */
    @Override
    public AjaxResult getDepartmentTreeTip(String parentId) {
        try {
            long start=System.currentTimeMillis();
            //将所有部门信息放入新建的sysDepartments
            List<SysDepartment> sysDepartments=sysDepartmentMapper.selectAll();
            SysDepartment sysDepartment = sysDepartmentMapper.selectByPrimary(parentId);
            sysDepartment.setChildren(getLeast(parentId,sysDepartments));
            long end = System.currentTimeMillis();
            long runtime = end-start;
            System.out.printf("查询部门Id为%s的叶子部门共耗时%d\n",parentId,runtime);
           return AjaxResult.success(ResultCode.SUCCESS_QUERY,sysDepartment);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 查询最底层部门
     *
     * @param departmentId 部门ID
     * @param sysDepartments 所有部门列表
     * @return 查询得到的最底层部门
     */
    private List<SysDepartment> getLeast(String departmentId,List<SysDepartment> sysDepartments) {
        List<SysDepartment> sysDepartmentList = new ArrayList<>();
        for (SysDepartment sysDepartment : sysDepartments){
            if (departmentId.equals(sysDepartment.getParentDepartmentId())){
                sysDepartmentList.add(sysDepartment);
                int before = sysDepartmentList.size();
                sysDepartmentList.addAll(getLeast(sysDepartment.getDepartmentId(),sysDepartments));
                int current = sysDepartmentList.size();
                if(current>before){
                    sysDepartmentList.remove(sysDepartment);
                }
            }
        }
        Collections.sort(sysDepartmentList, sequence());
        return sysDepartmentList;
    }

    /**
     * 通过部门Id获得所有子部门Id列表
     *
     * @param departmentId 部门Id
     * @return 子部门Id列表
     */
    @Override
    public List<String> getAllChildDepartmentId(String departmentId){
            List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectAll();
            List<String> idList = new ArrayList<>();
            idList.add(departmentId);
            idList.addAll(getChildIds(departmentId,sysDepartmentList));
            Collections.sort(idList, id());
            return idList;
    }

    /**
     * 通过部门Id获得子部门Id列表
     * @param departmentId 部门Id
     * @param sysDepartments 所有部门列表
     * @return 子部门Id列表
     */
    public List<String> getChildIds(String departmentId,List<SysDepartment> sysDepartments){
        List<String> childIdList = new ArrayList<>();
        for (SysDepartment sysDepartment : sysDepartments){
            if(departmentId.equals(sysDepartment.getParentDepartmentId())){
                childIdList.add(sysDepartment.getDepartmentId());
                childIdList.addAll(getChildIds(sysDepartment.getDepartmentId(),sysDepartments));
            }
        }
        return childIdList;
    }

    /**
     * 按照Id排序
     * @return 显示顺序
     */
    public Comparator<String> id(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (!o1.equals(o2)){
                    return Integer.parseInt(o1)-Integer.parseInt(o2);
                }
                return 0;
            }

        };
        return comparator;
    }

}
