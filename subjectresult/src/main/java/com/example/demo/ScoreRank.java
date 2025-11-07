package com.example.demo;

/**
 * 点数のランクを定義した列挙型
 */
public enum ScoreRank {
	A,
	B,
	C,
	D;
	
	/**
	 * 点数に該当するインスタンスを返します。
	 * @param score
	 *        点数
	 * @return
	 *        インスタンス
	 */
	public static ScoreRank getInstance(Integer score) {
		if (score == null || score < 30) {
			return D;
		} else if (score < 60) {
			return C;
		} else if (score < 80) {
			return B;
		} else {
			return A;
		}
	}
}
