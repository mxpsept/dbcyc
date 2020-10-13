package com.kcm.common.other;

import java.util.*;

/**
 * json对象构造工具类
 *
 * @author shawn
 * @version 1.0
 * @date 17/10/2018 11:35
 */
public class ImportUtil {
	/**
	 * 错误数据转换器
	 * @param errorList
	 * @return
	 */
	public static List<Map<String, Object>> conversionData(List<Map<String, Object>> errorList) {
		if(errorList.size()!=0){
			List<Map<String, Object>> list=new ArrayList<>();

			int length = (int)(errorList.size()/0.75+1);
			for(int i=0;i<errorList.size();i++){
				Map<String, Object> m=new HashMap<>(length);
				Map<String, Object> map = errorList.get(i);
				StringBuilder str=new StringBuilder();
				if(map.get("index")!=null && map.get("index")!=""){
					str.append("行数:"+map.get("index"));
				}else{
					str.append("行数:暂无");
				}
				map.remove("index");
				//迭代器遍历map
				Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<String, Object> entry = entries.next();
					str.append("、"+entry.getValue());
				}
				m.put("errorDate", str);
				m.put("index", map.get("index"));
				list.add(m);
			}
			return list;
		}else{
			return new ArrayList<>();
		}
	}
}