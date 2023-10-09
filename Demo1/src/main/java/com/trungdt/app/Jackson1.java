package com.trungdt.app;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson1 {

	public static void main(String[] args) throws Exception {
		demo2();
	}

	private static void demo2() throws Exception {
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\students.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode students = mapper.readTree(new File(path));
		
		students.iterator().forEachRemaining(student -> {
			System.out.println("Name: " + student.get("name").asText());
		});
	}

	private static void demo1() throws Exception {
		
//		String json = "{\r\n"
//				+ "				\"name\" : \"Đặng Thành Trung\",\r\n"
//				+ "				\"gender\" : true,\r\n"
//				+ "				\"marks\" : 7.5,\r\n"
//				+ "				\"contact\" : {\r\n"
//				+ "					\"email\" : \"dangthanhtrung57@gmail.com\",\r\n"
//				+ "					\"phone\" : \"0889641810\"\r\n"
//				+ "				},\r\n"
//				+ "				\"subjects\" : [\"WEB205\", \"COM108\"]\r\n"
//				+ "		}";
//		
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode student = mapper.readTree(json);
		
		
		
		String path = "D:\\Java6\\Demo\\Demo1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode student = mapper.readTree(new File(path));
		
		System.out.println("Name: " + student.get("name").asText());
		System.out.println("Marks: " + student.get("marks").asDouble());
		System.out.println("Gender: " + student.get("gender").asBoolean());
		System.out.println("Email: " + student.get("contact").get("email").asText());
		System.out.println("Phone: " + student.findValue("phone").asText());
		student.get("subjects").iterator().forEachRemaining(subject -> {
			System.out.println("Subject: " + subject.asText());
		});
	}
}
