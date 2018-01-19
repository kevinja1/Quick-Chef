package com.fbla.quickchef.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtilsExt {
	public static <S extends Object, T extends Object> T copy(T target, S source) {
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
