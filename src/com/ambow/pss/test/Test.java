package com.ambow.pss.test;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		Set<String> list = new HashSet<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		System.out.println(list);

		String str = null;
		if("".equals(str)){
			System.out.println(true);
		}
	}
}
