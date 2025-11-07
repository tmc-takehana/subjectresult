package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SubjectResultWriterTest {

	@Test
	void testWrite() throws IOException {
		
		List<SubjectResult> srList = new ArrayList<>();
		SubjectResult sr = new SubjectResult();
		sr.setName("国語");
		sr.setScore(null);
		srList.add(sr);

		sr = new SubjectResult();
		sr.setName("算数");
		sr.setScore(100);
		srList.add(sr);
		
		sr = new SubjectResult();
		sr.setName("社会");
		sr.setScore(50);
		srList.add(sr);
		
		SubjectResultWriter srw = new SubjectResultWriter();
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		srw.write(bao, srList);
		
		System.out.println(new String(bao.toByteArray(), Charset.forName("UTF-8")));

		InputStream is = getClass().getResourceAsStream("SubjectResultWriterPtn1.csv");
		byte[] expectedBytes = is.readAllBytes();
		assertArrayEquals(expectedBytes, bao.toByteArray());
	}
}
