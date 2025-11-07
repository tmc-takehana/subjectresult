package com.example.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

public class SubjectResultWriter {

	/**
	 * 教科情報を出力します
	 * 出力内容は下記のCSV形式になります
	 * name,scoreLabel,ScoreRank
	 * なお出力順は点数順にソートした内容になります。
	 * @param os
	 *        出力先
	 * @param srList
	 *        出力対象
	 * @throws IOException 
	 */
	public void write(OutputStream os, List<SubjectResult> srList) throws IOException {
//		List<SubjectResult> srTargetList = srList.stream().sorted((s1, s2) -> {
//			if (s1.getScore() == null) {
//				return -1;
//			}
//			if (s2.getScore() == null) {
//				return 1;
//			}
//			return s1.getScore().compareTo(s2.getScore());
//		}).toList();
		
		try (OutputStreamWriter osr = new OutputStreamWriter(os, Charset.forName("UTF-8"));
			BufferedWriter bw = new BufferedWriter(osr)) {
			for(SubjectResult s : srList) {
				String csv = s.getName() + "," + s.getScoreLabel() + "," + s.getScoreRank();
				bw.write(csv);
				bw.write("\n");
			}
		}
	}
}
