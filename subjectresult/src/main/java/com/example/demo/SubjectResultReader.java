package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 教科データを読み込む為のクラスです
 */
public class SubjectResultReader {

	/**
	 * 教科データを読み込んでSubjectResultクラスへ展開します
	 * @param is
	 *         入力ストリーム
	 * @return
	 *         教科データのリスト
	 * @throws IOException
	 *         読込みＮＧ等が発生した場合
	 */
	public List<SubjectResult> read(InputStream is) throws IOException {
		List<SubjectResult> result = new ArrayList<>();
		
		try(InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr)) {
			String str;
			while ((str = br.readLine()) != null) {
				String[] array = str.split(",");
				if (array.length < 2) {
					continue;
				}
				SubjectResult sr = new SubjectResult();
				sr.setName(array[0]);
				try {
					sr.setScore(Integer.parseInt(array[1].trim()));
				} catch (NumberFormatException e) {
					sr.setScore(null);
				}
				result.add(sr);
			}
		}
		
		return result;
	}
	
	/**
	 * 教科データを読み込んでSubjectResultクラスへ展開します
	 * @param file
	 *        教科データのファイル
	 * @return
	 *         教科データのリスト
	 * @throws IOException
	 *         読込みＮＧ等が発生した場合
	 */
	public List<SubjectResult> read(File file) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return read(fis);
		}
	}
}
