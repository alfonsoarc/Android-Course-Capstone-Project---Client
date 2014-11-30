package com.mobile.mutiboclient.client;

public class Score implements Comparable<Score> {
	private long id;

	private String user;
	private int score;

	public Score() {
	}

	public Score(String user, int score) {
		super();
		this.user = user;
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int compareTo(Score another) {
		if(this.score > another.score) {
			return -1;
		} else if (this.score < another.score) {
			return 1;
		} else {
			return 0;
		}
	}
}
