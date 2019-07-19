package com.pengfeng;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装一个对象所有的PropertyValue。<br/>
 * 为什么封装而不是直接用List?因为可以封装一些操作。
 * @author pengfeng
 */
public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}

	public void addPropertyValue(PropertyValue pv) {
		this.propertyValueList.add(pv);
	}

	public List<PropertyValue> getPropertyValues() {
		return this.propertyValueList;
	}

}
