package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 成績ファイルを変換するプログラムのエントリーポイントです。
 */
public class SubjectResultEntry {
	
	private static SubjectResultReader srr = new SubjectResultReader();
	
	public static void setSubjectResultReader(SubjectResultReader param) {
		srr = param;
	}

	private static SubjectResultWriter srw = new SubjectResultWriter();
	
	public static void setSubjectResultWriter(SubjectResultWriter param) {
		srw = param;
	}
	
	/**
	 * mainメソッド
	 * @param args
	 *		第１引数：入力用のCSVファイルのパス
	 *		第２引数：出力用のCSVファイルのパス
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("引数が正しくありません。");
			System.err.println("第１引数：入力用のCSVファイルのパス");
			System.err.println("第２引数：出力用のCSVファイルのパス");
			return;
		}
		
		File inputFile = new File(args[0]);
		if (!inputFile.exists()) {
			System.err.println("第１引数のパスが正しくありません[" + args[0] + "]");
			return;
		}
		
		File outputFile = new File(args[1]);
		File outputFolder = outputFile.getAbsoluteFile().getParentFile().getParentFile();
		outputFolder.mkdirs();
		
		System.out.println("CSVファイルを読込みます。");
		List <SubjectResult> srList;
		try {
			srList = srr.read(new File(args[0]));
		} catch (IOException e) {
			System.err.println("ファイル読込中にエラーが発生しました。");
			e.printStackTrace();
			return;
		}
		
		System.out.println("CSVファイルを書込みます。");
		
		try (FileOutputStream fos = new FileOutputStream(args[1])) {
			srw.write(fos, srList);
		} catch (IOException e) {
			System.err.println("ファイル書込中にエラーが発生しました。");
			e.printStackTrace();
			return;
		}
		System.out.println("プログラムを終了します。");
	}
}
