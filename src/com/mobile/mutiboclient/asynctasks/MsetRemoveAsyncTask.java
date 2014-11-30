package com.mobile.mutiboclient.asynctasks;

import android.os.AsyncTask;

import com.mobile.mutiboclient.client.SecuredRestBuilder;

public class MsetRemoveAsyncTask extends AsyncTask<Void, Void, Void> {

	private Long msetId;

	public MsetRemoveAsyncTask(long msetId) {
		this.msetId = msetId;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		SecuredRestBuilder.getMutiboService().deleteMset(msetId);
		return null;
	}
}
