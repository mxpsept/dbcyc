package com.kcm.common.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形菜单
 * 
 * @author wujia<1438019595@qq.com>
 * 
 */
public class TreeJson {

	/**
	 * 编号
	 */
	private String id;

	/**
	 * 父级编号
	 */
	private String parentId;

	/**
	 * 显示文字
	 */
	private String text;

	/**
	 * 点击的url
	 */
	private String url;

	/**
	 * 菜单状态
	 */
	private String state;

	/**
	 * 排序字段
	 */
	private Integer sortBy;

	/**
	 * 标识是否被选中
	 */
	private boolean checked;
	/**
	 * 地图数据
	 */
	private String gisPipeLid;
	/**
	 * 区分部门和用户
	 */
	private String stateType;
	/**
	 * 部门和用户图标
	 */
	private String iconCls;

	/**
	 * 子集列表
	 */
	private List<TreeJson> children = new ArrayList<TreeJson>();

	public String getGisPipeLid() {
		return gisPipeLid;
	}

	public void setGisPipeLid(String gisPipeLid) {
		this.gisPipeLid = gisPipeLid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeJson> getChildren() {
		return children;
	}

	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "TreeJson [id=" + id + ", parentId=" + parentId + ", text="
				+ text + ", url=" + url + ", state=" + state + ", children="
				+ children + "]";
	}

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
