package com.mobile.mutiboclient.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobile.mutiboclient.GameManager;
import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.asynctasks.MsetRemoveAsyncTask;
import com.mobile.mutiboclient.asynctasks.RatingSubmitAsyncTask;

public class SetInfoActivity extends Activity {

	private Activity activity;

	private RatingBar ratingBar;
	private TextView explanationTextView;
	private Button nextSetButton;

	private Long msetId;

	private boolean isPoorlyRatedMset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set_info);

		activity = this;
		Bundle extras = getIntent().getExtras();

		boolean correctAnswer = extras.getBoolean("correctAnswer");
		isPoorlyRatedMset = extras.getBoolean("isPoorlyRated");
		String explanation = extras.getString("explanation");
		msetId = extras.getLong("msetId");

		explanationTextView = (TextView) findViewById(R.id.explanationText);
		if (correctAnswer) {
			explanationTextView.setText(R.string.correct_answer);
			GameManager.setScore(GameManager.getScore()
					+ GameManager.getBonusTimeScore() + 10);
		} else {
			explanationTextView.setText(explanation);
			GameManager.setNewIncorrectGuess();
		}

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		nextSetButton = (Button) findViewById(R.id.nextSetButton);
		nextSetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RatingSubmitAsyncTask ratingSubmitter = new RatingSubmitAsyncTask(msetId,
						ratingBar.getRating());
				ratingSubmitter.execute();

				if (ratingBar.getRating() == 1.0 && isPoorlyRatedMset) {
					showPoorlyRatedDialog();
				} else {
					goToNextScreen();
				}
			}
		});

		// Footer
		TextView footer = (TextView) findViewById(R.id.footerText);
		footer.setText(Integer.toString(3 - GameManager.getIncorrectGuesses()) + "   -   Score: " 
				+ GameManager.getScore() + "   -   Highest score: " + GameManager.getHighestScoreUser());
	}

	private void showPoorlyRatedDialog() {
		DialogInterface.OnClickListener onclickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == DialogInterface.BUTTON_POSITIVE) {
					MsetRemoveAsyncTask msetRemover = new MsetRemoveAsyncTask(msetId);
					msetRemover.execute();
				}
				goToNextScreen();
			}
		};

		new AlertDialog.Builder(this)
				.setMessage(
						"It seems that you did not like this Mset. This Mset has been poorly rated for some other users too."
								+ "Would you like to remove this mset?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton(android.R.string.yes, onclickListener)
				.setNegativeButton(android.R.string.no, onclickListener).show();
	}

	private void goToNextScreen() {
		if (GameManager.getIncorrectGuesses() < 3) {
			GameManager.loadNextSet(activity);
		} else {
			Bundle bundle = new Bundle();
			Intent intent = new Intent(activity, GameOverActivity.class);
			bundle.putBoolean("gameWasCompleted", false);
			intent.putExtras(bundle);
			activity.startActivity(intent);
		}
	}

}
