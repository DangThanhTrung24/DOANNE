package com.trungdt.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.trungdt.bean.Student;

public class StreamAPI {
	
	static List<Student> list = Arrays.asList(
			new Student("Đặng Thành Trung", true, 10.0),
			new Student("Nguyễn Văn A", true, 6.5), 
			new Student("Nguyễn Văn B", false, 7.0),
			new Student("Nguyễn Văn C", true, 8.7), 
			new Student("Nguyễn Văn D", false, 9.3)
			);

	public static void main(String[] args) {
		demo4();
	}

	private static void demo4() {
		double average = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.average().getAsDouble();
		System.out.println("Average: " + average);
		
		double sum = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.sum();
		System.out.println("Sum: " + sum);
		
		double min_marks = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.min().getAsDouble();
		System.out.println("Min marks: " + min_marks);
	
		boolean all_passed = list.stream()
				.allMatch(sv -> sv.getMarks() >= 8);
		System.out.println("All pass: " + all_passed);
		
		Student min_sv = list.stream()
				.reduce(list.get(0), (min, sv) -> sv.getMarks() < min.getMarks() ? sv : min);
		System.out.println("Min sv: " + min_sv);
	}

	private static void demo3() {
		List<Student> result = list.stream()
				.filter(sv -> sv.getMarks() >= 8)
				.peek(sv -> sv.setName(sv.getName().toUpperCase()))
				.collect(Collectors.toList());
		
		result.forEach(sv -> {
			System.out.println("Name: " +  sv.getName() + " - " + "Marks " + sv.getMarks());
		});
	}

	private static void demo2() {
		List<Integer> list = Arrays.asList(1, 9, 4, 7, 8, 3);
		List<Double> Stream2 = list.stream()
				.filter(n -> n % 2 ==0)
				.map(n -> Math.sqrt(n))
				.peek(d -> System.out.println(d))
				.collect(Collectors.toList());
	}

	private static void demo1() {
//		Cách 1
		Stream<Integer> stream1 = Stream.of(1, 9, 4, 7, 8, 3);
		stream1.forEach(n -> {
			System.out.print(n);
		});
		
//		Cách 2
		List<Integer> list = Arrays.asList(1, 9, 4, 7, 8, 3);
//		Stream<Integer> Stream2 = list.stream();
		list.forEach(n -> {
			System.out.print(n);
		});
	}
}
