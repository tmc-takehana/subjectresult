package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ScoreRankのテスト
 */
class ScoreRankTest {

	/**
	 * 正常ケース
	 */
	@Test
	void testScoreRank() {
		assertEquals(ScoreRank.D, ScoreRank.getInstance(null));
		assertEquals(ScoreRank.D, ScoreRank.getInstance(0));
		assertEquals(ScoreRank.D, ScoreRank.getInstance(29));
		assertEquals(ScoreRank.C, ScoreRank.getInstance(30));
		assertEquals(ScoreRank.C, ScoreRank.getInstance(31));
		assertEquals(ScoreRank.C, ScoreRank.getInstance(59));
		assertEquals(ScoreRank.B, ScoreRank.getInstance(60));
		assertEquals(ScoreRank.B, ScoreRank.getInstance(61));
		assertEquals(ScoreRank.B, ScoreRank.getInstance(79));
		assertEquals(ScoreRank.A, ScoreRank.getInstance(80));
		assertEquals(ScoreRank.A, ScoreRank.getInstance(81));
		assertEquals(ScoreRank.A, ScoreRank.getInstance(100));
	}
}
