package com.mobile.mutiboclient;

import java.util.Collection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobile.mutiboclient.activities.MainActivity;
import com.mobile.mutiboclient.client.Mset;
import com.mobile.mutiboclient.client.MutiboSvcApi;
import com.mobile.mutiboclient.client.SecuredRestBuilder;
import com.mobile.mutiboclient.util.MutiboUtil;

public class GameLoader implements OnClickListener {

	private MainActivity activity;
	private ProgressDialog progressDialog;

	private Collection<Mset> msets;
	private Collection<Mset> poorRatedMsets;
	private int highestScoreUser;

	public GameLoader(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		if (MutiboUtil.isInternetOn(activity)) {
			MsetsLoader msetLoader = new MsetsLoader();
			msetLoader.execute();
		} else {
			MutiboUtil.displayAlertDialog(activity, R.string.dialog_title_no_internet,
					R.string.dialog_message_no_internet);
		}
	}

	private class MsetsLoader extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(activity);
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			MutiboSvcApi msetSvcApi = SecuredRestBuilder.getMutiboService();
			msets = msetSvcApi.getMsetList();
			poorRatedMsets = msetSvcApi.findByNegativesGreaterThan(20);
			highestScoreUser = msetSvcApi.getMaximumScore();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();
			GameManager.startGame(activity, msets, poorRatedMsets, highestScoreUser);
		}

	}
}
