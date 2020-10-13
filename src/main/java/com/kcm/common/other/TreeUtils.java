package com.kcm.common.other;

import com.kcm.common.other.EntityReflectionUtils;

import java.util.*;

/**
 * 生成树形结构的工具类
 * 
 * @author wujia<1438019595@qq.com>
 * 
 */

public final class TreeUtils<T, B> {

	private static final Random rnd = new Random();

	/**
	 * 添加关键字段的键值映射关系
	 * 
	 * @param targetPropName
	 *            目标类中的属性名称
	 * @param sourcePropName
	 *            源类中的属性名称
	 */
	public void addProperties(String targetPropName, String sourcePropName) {
		addProperties(null, targetPropName, sourcePropName);
	}

	/**
	 * 添加关键字段的键值映射关系
	 * 
	 * @param pubKey
	 *            id,pid，children等字段,null值则由系统随机生成
	 * @param targetPropName
	 *            目标类中的属性名称
	 * @param sourcePropName
	 *            源类中的属性名称
	 */
	public void addProperties(String pubKey, String targetPropName,
			String sourcePropName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(targetPropName, sourcePropName);
		if (pubKey == null) {
			int a = rnd.nextInt(10000) % 100000;
			pubKey = a + "";
		}
		codeMap.put(pubKey, map);
	}

	public static final String KEY_ID = "id";

	public static final String KEY_PID = "pid";

	public static final String KEY_CHILDREN = "children";

	public static final String KEY_SELECTED = "checked";

	public static final String KEY_SORT = "sortBy";

	public static final String KEY_ICON = "iconCls";

	private Map<String, Map<String, String>> codeMap = new HashMap<String, Map<String, String>>();

	public List<B> createTree(Class<B> clazz, List<T> entities) {
		return createTree(clazz, entities, null);
	}

	private String B_ID = null;

	private String B_PID = null;

	private String B_CHILDREN = null;

	private String B_SELECTED = null;

	private String B_ICON = null;

	public List<B> createTree(Class<B> clazz, List<T> entities,
			List<String> selectedCodes) {
		Map<String, B> maps = new HashMap<String, B>();
		if (entities != null) {
			for (T t : entities) {
				Map<String, Object> valueMaps = EntityReflectionUtils.fields(t);
				B entity = getInstanceOfB(clazz);
				Object code = null;
				for (String key : codeMap.keySet()) {
					Map<String, String> properties = codeMap.get(key);
					String b_propKey = null;
					String t_propKey = null;
					for (String key_bid : properties.keySet()) {
						b_propKey = key_bid;
						t_propKey = properties.get(key_bid);
						if (key.equals(KEY_ID)) {
							B_ID = key_bid;
							// T_ID = properties.get(key_bid);
						} else if (key.equals(KEY_PID)) {
							B_PID = key_bid;
							// T_PID = properties.get(key_bid);
						} else if (key.equals(KEY_CHILDREN)) {
							B_CHILDREN = key_bid;
						} else if (key.equals(KEY_SELECTED)) {
							B_SELECTED = key_bid;
						}else if(key.equals(KEY_ICON)){
							B_ICON = key_bid;
						}
						break;
					}
					if (key.equals(KEY_CHILDREN) || key.equals(KEY_SELECTED)) {
						continue;
					}
					Object t_propValue = getValue(valueMaps, t_propKey);
					EntityReflectionUtils.set(entity, b_propKey, t_propValue);
					if (B_ID != null && code == null) {
						if (key.equals(KEY_ID)) {
							code = t_propValue;
						}
					}
				}
				if (code != null) {
					maps.put(code.toString(), entity);
				} else {
					throw new IllegalStateException("待生成的树形数据中没有指定code属性");
				}
				if (B_CHILDREN == null) {
					// 默认
					B_CHILDREN = KEY_CHILDREN;
				}
				if (B_SELECTED == null) {
					B_SELECTED = KEY_SELECTED;
				}
				if (selectedCodes != null
						&& selectedCodes.contains(code.toString())) {
					EntityReflectionUtils.set(entity, B_SELECTED, true);
				}
				code = null;
			}
		}
		List<B> lst = dg(maps);
		return lst;
	}

	private List<B> sort(List<B> lst) {
		List<B> newLst = new ArrayList<B>();
		for (B entity : lst) {
			Object sort = getValue(entity, KEY_SORT);
			int sortValue = sort != null ? ((Integer) sort).intValue() : 0;
			int index = 0;
			int count = 0;
			for (B nb : newLst) {
				Object sort2 = getValue(nb, KEY_SORT);
				int sort2Value = sort2 != null ? ((Integer) sort2).intValue()
						: 0;
				if (sort2Value >= sortValue) {
					index = count;
					break;
				}
				count++;
			}
			if (count == newLst.size()) {
				index = count;
			}
			newLst.add(index, entity);
		}
		return newLst;
	}

	private List<B> dg(Map<String, B> values) {
		Set<String> codes = new HashSet<String>();
		for (String code : values.keySet()) {
			B child = values.get(code);
			Object parentCodeObject = getValue(child, B_PID);
			if (parentCodeObject == null) {
				continue;
			}
			String parentCode = parentCodeObject.toString();
			B parent = getBFromMap(parentCode, values);
			@SuppressWarnings("unchecked")
			List<B> childs = (List<B>) getValue(parent, B_CHILDREN);
			if (childs != null) {
				childs.add(child);
				codes.add(code);
				childs = sort(childs);
				EntityReflectionUtils.set(parent, B_CHILDREN, childs);
			}
		}

		List<B> lst = new ArrayList<B>();
		for (String code : values.keySet()) {
			if (codes.contains(code) == false) {
				lst.add(values.get(code));
			}
		}
		lst = sort(lst);
		return lst;
	}

	private B getBFromMap(String parentCode, Map<String, B> maps) {
		B result = null;
		for (String code : maps.keySet()) {
			if (getValue(maps.get(code), B_ID).toString().equals(parentCode)) {
				result = maps.get(code);
				break;
			}
		}
		return result;
	}

	private B getInstanceOfB(Class<B> clazz) {
		B entity = null;
		try {
			entity = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return entity;
	}

	private Object getValue(B b, String fieldName) {
		Map<String, Object> valueMaps = EntityReflectionUtils.fields(b);
		Object value = null;
		if (valueMaps != null) {
			value = valueMaps.get(fieldName);
		}
		return value;
	}

	private Object getValue(Map<String, Object> valueMaps, String fieldName) {
		Object value = null;
		if (valueMaps != null) {
			value = valueMaps.get(fieldName);
		}
		return value;
	}

}
