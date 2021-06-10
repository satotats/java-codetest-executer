package com.codetest.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.codetest.tools.fragment.MockIn;

public class Executor {

	public static void main(String[] args) {
		String mainClassName = args[0];
		
		System.out.println("Execute tests -> " + mainClassName);
		System.out.println();
		
		String simpleClassName = simplify(mainClassName);
		int seq = 0;
		
		while (true) {
			String[] values = loadFile(buildFilePath(simpleClassName, ++seq));
			if (values != null) {
				System.out.println("------ [START] Test " + seq + " ------");
				execute(mainClassName, values);
				System.out.println("------ [END] Test " + seq + " ------");
				System.out.println();
			} else {
				break;
			}
		}
	}

	private static String simplify(String fqcn) {
		String[] splited = fqcn.split("\\.");
		return splited[splited.length-1];
	}
	
	private static void execute(String className, String[] values) {
		try {
			Class<?> clazz = Class.forName(className);
			Method method = clazz.getMethod("main", String[].class);
			method.invoke(null, new Object[]{values}); // Object配列への格納は呪文: 消すとRuntimeError
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] loadFile(String fileName) {
		List<String> list = new ArrayList<>();
		try {
			File f = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(f, Charset.forName("utf-8")));
			System.out.println("File loaded: "+ fileName);

			MockIn in = new MockIn();
			System.setIn(in);
			
			String line;
			while ((line = br.readLine()) != null) {
				line = trim(line).replace(",", " ");
				
				in.type(line);
				list.add(line);
			}
			br.close();
			return list.toArray(new String[0]);
		} catch (IOException e) {
			System.out.println("[END OF TEST] No file found...");
			return null;
		}
	}
	
	private static String buildFilePath(String nameBody, int seq) {
		return "testcase\\" + nameBody + "\\case" + seq + ".csv";
	}
	
	private static final char COMMA = ',';
	private static String trim(String line) {
		StringBuilder sb = new StringBuilder(line);
		while (true) {
			char c = sb.charAt(sb.length()-1);
			if (c != COMMA) {
				break;
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
