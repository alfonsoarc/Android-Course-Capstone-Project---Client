package com.mobile.mutiboclient.asynctasks;

import android.os.AsyncTask;

import com.mobile.mutiboclient.client.SecuredRestBuilder;

public class RatingSubmitAsyncTask extends AsyncTask<Void, Void, Void> {

	private Long msetId;
	private int rating;

	public RatingSubmitAsyncTask(long msetId, float rating) {
		this.msetId = msetId;
		this.rating = (int) rating;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		SecuredRestBuilder.getMutiboService().rateMset(msetId, rating);
		return null;
	}
}