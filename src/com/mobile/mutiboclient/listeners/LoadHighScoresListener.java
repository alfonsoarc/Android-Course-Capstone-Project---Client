package com.mobile.mutiboclient.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.activities.HighScoresActivity;
import com.mobile.mutiboclient.activities.MainActivity;
import com.mobile.mutiboclient.client.Score;
import com.mobile.mutiboclient.client.SecuredRestBuilder;
import com.mobile.mutiboclient.util.MutiboUtil;

public class LoadHighScoresListener implements OnClickListener {

	private MainActivity activity;
	private ProgressDialog progressDialog;

	private Collection<Score> scores;

	public LoadHighScoresListener(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		if (MutiboUtil.isInternetOn(activity)) {
			LoadHighScoresAsyncTask highScoreLoadingAsyncTask = new LoadHighScoresAsyncTask();
			highScoreLoadingAsyncTask.execute();
		} else {
			MutiboUtil.displayAlertDialog(activity, R.string.dialog_title_no_internet,
					R.string.dialog_message_no_internet);
		}
	}

	private class LoadHighScoresAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(activity);
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			scores = SecuredRestBuilder.getMutiboService().getScoreList();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();
			
			ArrayList<Score> scoresList = new ArrayList<Score>(scores);
			Collections.sort(scoresList);
			
			Bundle bundle = new Bundle();
			Intent intent = new Intent(activity, HighScoresActivity.class);
			bundle.putStringArrayList("scores", convertToListViewFormat(scoresList));
			intent.putExtras(bundle);
			activity.startActivity(intent);
		}
		
		private ArrayList<String> convertToListViewFormat(ArrayList<Score> scoresList) {
			ArrayList<String> arrayListScores = new ArrayList<String>(scoresList.size());
			for(Object o : scoresList) {
				Score score = (Score) o;
				arrayListScores.add(score.getUser() + "  " + score.getScore());
			}
			return arrayListScores;
		}
	}
}
