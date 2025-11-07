package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SubjectResultEntryTest {

	@Test
	void testMain() throws IOException {
		
		// リーダー用のモック(スタブ)を生成
		SubjectResultReader readerMock = mock(SubjectResultReader.class);		
		
		List<SubjectResult> srList = new ArrayList<>();
		String inputPath = getClass().getResource("SubjectResultEntryPtn1.csv").getPath();
		// このメソッドが呼ばれたときにこのメソッドを返すという内容
		// Fileクラスが渡されたら、srListを返すという意味
		when(readerMock.read(new File(inputPath))).thenReturn(srList);

		// リーダーの差替え
		SubjectResultEntry.setSubjectResultReader(readerMock);
		
		// ライター用のスタブを生成		
		SubjectResultWriter writerMock = mock(SubjectResultWriter.class);
		String outputPath = getClass().getResource("SubjectResultEntryPtn1.csv").getPath();

		doNothing().when(writerMock).write(new FileOutputStream(new File(outputPath)), srList);
		// ライターの差替え
		SubjectResultEntry.setSubjectResultWriter(writerMock);

		SubjectResultEntry.main(new String[] {inputPath, outputPath});
	}
	
	@Test
	void testMain2() throws IOException {

        PrintStream originalErr = System.err;
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
        	SubjectResultEntry.main(new String[] {});
        	String erMsg = "引数が正しくありません。" + 
        			"\r\n第１引数：入力用のCSVファイルのパス" + 
        			"\r\n第２引数：出力用のCSVファイルのパス";
            assertTrue(errContent.toString().contains(erMsg));
        } finally {
            // メッセージは元に戻す必要がある
            System.setErr(originalErr);
        }
	}
}
