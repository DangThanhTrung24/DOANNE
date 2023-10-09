package com.trungdt.app;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trungdt.bean.Contact;
import com.trungdt.bean.Student2;

public class Jackson2 {

	public static void main(String[] args) throws Exception {
//		demo7();
	}

	private static void demo7() throws Exception{
		Contact contact = new Contact("teonv@gmail.com", "0123456789", null);
		List<String> subjects = Arrays.asList("WEB205", "COM108");
		Student2 student = new Student2("NguyenVanTeo", true, 5.7, contact, subjects);
		
		ObjectMapper mapper = new ObjectMapper();
		// write to string
				String json = mapper.writeValueAsString(student);
				System.out.println(json);
				// write to output
				mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, student);
				// write to file
				mapper.writeValue(new File("D:\\Java6\\student.json"), student);
	}

	private static void demo6() throws Exception {
		Map<String, Object> contact = new HashMap<String, Object>();
		contact.put("email", "teo@gmai.com");
		contact.put("phone", "0975318642");

		List<String> subjects = Arrays.asList("WEB205", "COM108");

		Map<String, Object> student = new HashMap<String, Object>();
		student.put("name", "NguyenVanTeo");
		student.put("marks", 9.5);
		student.put("gender", true);
		student.put("contact", contact);
		student.put("subjects", subjects);

		ObjectMapper mapper = new ObjectMapper();
		// write to string
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		// write to output
		mapper.writeValue(System.out, student);
		// write to file
		mapper.writeValue(new File("D:\\Java6\\student.json"), student);
	}

	private static void demo5() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode student = mapper.createObjectNode();
		student.put("name", "Nguyễn Văn Tèo");
		student.put("marks", 5.5);
		student.put("gender", false);
		ObjectNode contact = student.putObject("contact");
		contact.put("email", "teonv@gmail.com");
		contact.put("phone", "0123456789");
		ArrayNode subjects = student.putArray("subjects");
		subjects.add("WEB205");
		subjects.add("COM108");

		// write to string
		String json = mapper.writeValueAsString(student);
		// write to output
		mapper.writeValue(System.out, student);
		// write to file
		mapper.writeValue(new File("D:\\Java6\\student.json"), student);
	}

	private static void demo4() throws Exception {
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\students.json";

		TypeReference<List<Student2>> type = new TypeReference<List<Student2>>() {
		};
		ObjectMapper mapper = new ObjectMapper();

		List<Student2> students = mapper.readValue(new File(path), type);
		students.forEach(student -> {
			System.out.println("Name: " + student.getName());
		});
	}

	private static void demo3() throws Exception {
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		Student2 student = mapper.readValue(new File(path), Student2.class);
		System.out.println("Name: " + student.getName());
		System.out.println("Marks: " + student.getMarks());
		System.out.println("Gender: " + student.getGender());

		Contact contact = student.getContact();
		System.out.println("Email: " + contact.getEmail());
		System.out.println("Phone: " + contact.getPhone());

		List<String> subjects = student.getSubjects();
		subjects.forEach(subject -> {
			System.out.println("Subject: " + subject);
		});
	}

	private static void demo2() throws Exception {
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\students.json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> students = mapper.readValue(new File(path), List.class);
		students.forEach(student -> {
			System.out.println("Name: " + student.get("name"));
		});
	}

	private static void demo1() throws Exception {
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> student = mapper.readValue(new File(path), Map.class);

		System.out.println("Name: " + student.get("name"));
		System.out.println("Marks: " + student.get("marks"));
		System.out.println("Gender: " + student.get("gender"));

		Map<String, Object> contact = (Map<String, Object>) student.get("contact");
		System.out.println("Email: " + contact.get("email"));
		System.out.println("Phone: " + contact.get("phone"));

		List<String> subjects = (List<String>) student.get("subjects");
		subjects.forEach(subject -> {
			System.out.println("Subject: " + subject);
		});

	}
}
