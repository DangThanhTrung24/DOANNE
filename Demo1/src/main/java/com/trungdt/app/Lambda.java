package com.trungdt.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.trungdt.bean.Student;

public class Lambda {
	
	static List<Student> list = Arrays.asList(
			new Student("Đặng Thành Trung", true, 10.0),
			new Student("Nguyễn Văn A", true, 6.5), 
			new Student("Nguyễn Văn B", false, 7.0),
			new Student("Nguyễn Văn C", true, 8.7), 
			new Student("Nguyễn Văn D", false, 9.3)
			);
	
	
	public static void main(String[] args) {
		demo1();
	}

	@FunctionalInterface
	interface Demo4Inter{
		void m1(int x);
		default void m2() {};
		public static void m3() {};
	}
	
	private static void demo4() {
//		Truyen Thong
//		Demo4Inter o = new Demo4Inter() {
//			
//			@Override
//			public void m1(int x) {
//				System.out.println(x);
//			}
//		};
//		o.m1(2409);
		
		Demo4Inter o = x -> {
			System.out.println(x);
		};
		o.m1(2409);
	}

	private static void demo3() {
		Collections.sort(list, (sv1, sv2) -> sv1.getMarks().compareTo(sv2.getMarks()));
		list.forEach(sv -> {
			System.out.println("Name: " +  sv.getName() + " - " + "Marks " + sv.getMarks());
		});
	}

	private static void demo2() {
		List<Student> list = Arrays.asList(
				new Student("Đặng Thành Trung", true, 10.0),
				new Student("Nguyễn Văn A", true, 6.5), 
				new Student("Nguyễn Văn B", false, 7.0),
				new Student("Nguyễn Văn C", true, 8.7), 
				new Student("Nguyễn Văn D", false, 9.3)
				);
		list.forEach(sv -> {
			System.out.println("Name: " + sv.getName());
		});
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
		list.forEach(n -> System.out.println(n));
	}
}
