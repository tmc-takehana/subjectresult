package com.example.demo;

/**
 * 教科の情報を保持するクラスです。
 */
public class SubjectResult {
	private String name;
	
	private Integer score;
	

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score セットする score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * scoreに「点」を付けた内容を返却します。
	 * nullの場合は無効が返却されます。
	 * @return scoreLabel
	 */
	public String getScoreLabel() {
		return score == null ? "無効" : score + "点";
	}
	
	/**
	 * scoreに該当するランクを返します。
	 * @return
	 */
	public ScoreRank getScoreRank() {
		return ScoreRank.getInstance(score);
	}
}
