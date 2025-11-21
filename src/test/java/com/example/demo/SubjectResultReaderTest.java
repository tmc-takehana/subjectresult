package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubjectResultReaderTest {

	/**
	 * CSVファイルを読み込むテスト
	 * 正常系
	 * @throws IOException
	 */
	@Test
	@DisplayName("CSVファイルを読み込むテスト testRead()")
	void testRead() throws IOException {
		InputStream is = getClass().getResourceAsStream("SubjectResultReaderPtn1.csv");
		SubjectResultReader srr = new SubjectResultReader();
		List<SubjectResult> srList = srr.read(is);

		assertEquals(4, srList.size());
		SubjectResult sr = srList.get(0);
		assertEquals("国語", sr.getName());
		assertEquals(91, sr.getScore());
		assertEquals("91点", sr.getScoreLabel());

		sr = srList.get(1);
		assertEquals("算数", sr.getName());
		assertEquals(68, sr.getScore());
		assertEquals("68点", sr.getScoreLabel());

		sr = srList.get(2);
		assertEquals("理科", sr.getName());
		assertEquals(74, sr.getScore());
		assertEquals("74点", sr.getScoreLabel());

		sr = srList.get(3);
		assertEquals("社会", sr.getName());
		assertEquals(82, sr.getScore());
		assertEquals("82点", sr.getScoreLabel());
	}
	
	/**
	 * 0件のCSVファイルを読み込むテスト
	 * 準正常系
	 * @throws IOException
	 */
	@Test
	@DisplayName("0件のCSVファイルを読み込むテスト testRead2()")
	void testRead2() throws IOException {
		InputStream is = getClass().getResourceAsStream("SubjectResultReaderPtn2.csv");
		SubjectResultReader srr = new SubjectResultReader();
		List<SubjectResult> srList = srr.read(is);
		assertTrue(srList.isEmpty());

	}
	
	/**
	 * CSVファイルを読み込むテスト
	 * 空行が存在する
	 * 準正常系
	 * @throws IOException
	 */
	@Test
	void testRead3() throws IOException {
		InputStream is = getClass().getResourceAsStream("SubjectResultReaderPtn3.csv");
		SubjectResultReader srr = new SubjectResultReader();
		List<SubjectResult> srList = srr.read(is);
		assertEquals(4, srList.size());
		SubjectResult sr = srList.get(0);
		assertEquals("国語", sr.getName());
		assertEquals(91, sr.getScore());
		assertEquals("91点", sr.getScoreLabel());

		sr = srList.get(1);
		assertEquals("算数", sr.getName());
		assertEquals(68, sr.getScore());
		assertEquals("68点", sr.getScoreLabel());

		sr = srList.get(2);
		assertEquals("理科", sr.getName());
		assertEquals(74, sr.getScore());
		assertEquals("74点", sr.getScoreLabel());

		sr = srList.get(3);
		assertEquals("社会", sr.getName());
		assertEquals(82, sr.getScore());
		assertEquals("82点", sr.getScoreLabel());
	}

	/**
	 * CSVファイルを読み込むテスト
	 * 空行が存在する
	 * 準正常系
	 * @throws IOException
	 */
	@Test
	void testRead4() throws IOException {
		InputStream is = getClass().getResourceAsStream("SubjectResultReaderPtn4.csv");
		SubjectResultReader srr = new SubjectResultReader();
		List<SubjectResult> srList = srr.read(is);
		assertEquals(3, srList.size());
		SubjectResult sr = srList.get(0);
		assertEquals("国語", sr.getName());
		assertNull(sr.getScore());
		assertEquals("無効", sr.getScoreLabel());

		sr = srList.get(1);
		assertEquals("算数", sr.getName());
		assertNull(sr.getScore());
		assertEquals("無効", sr.getScoreLabel());

		sr = srList.get(2);
		assertEquals("理科", sr.getName());
		assertEquals(100, sr.getScore());
		assertEquals("100点", sr.getScoreLabel());
	}
	
	@Test
	void testReadRank() throws IOException {
		InputStream is = getClass().getResourceAsStream("SubjectResultReaderPtn1.csv");
		SubjectResultReader srr = new SubjectResultReader();
		List<SubjectResult> srList = srr.read(is);

		SubjectResult sr = srList.get(0);
		assertEquals(ScoreRank.A, sr.getScoreRank());
	}

	/**
	 * CSVファイルを読み込むテスト
	 * Fileクラス指定パターン
	 * 正常系
	 * @throws IOException
	 */
	@Test
	void testReadFile() throws IOException {
		URL url = getClass().getResource("SubjectResultReaderPtn1.csv");
		File f = new File(url.getPath());
		SubjectResultReader srr = new SubjectResultReader();
		srr.read(f);
	}
	
	/**
	 * CSVファイルを読み込むテスト
	 * Fileクラス指定パターン
	 * ファイルが存在しないケース
	 * @throws IOException
	 */
	@Test
	void testReadFileNG() throws IOException {
		assertThrows(IOException.class, () -> {
			SubjectResultReader srr = new SubjectResultReader();
			srr.read(new File("存在しないファイル"));
	    });
	}
}
