package com.mobile.mutiboclient.asynctasks;

import android.os.AsyncTask;

import com.mobile.mutiboclient.client.SecuredRestBuilder;

public class ScoreSubmitAsyncTask extends AsyncTask<Void, Void, Void> {
	private int score;

	public ScoreSubmitAsyncTask(int score) {
		this.score = score;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		SecuredRestBuilder.getMutiboService().saveScore(score);
		return null;
	}
}