package com.mobile.mutiboclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobile.mutiboclient.GameManager;
import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.services.ScoreBonusService;

public class SetActivity extends Activity {

	private RadioGroup movieRadioGroup;
	private Button submitSetButton;
	private int intruder;
	private String explanation;
	private long msetId;
	private Activity activity;
	private RadioButton[] radioButtons;
	private boolean isPoorlyRatedMset;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set);

		//Footer
		TextView footer = (TextView) findViewById(R.id.footerText);
		footer.setText(Integer.toString(3 - GameManager.getIncorrectGuesses()) + "   -   Score: " 
				+ GameManager.getScore() + "   -   Highest score: " + GameManager.getHighestScoreUser());
		
		intent = new Intent(this, ScoreBonusService.class);
		startService(intent);
		
		activity = this;

		Bundle extras = getIntent().getExtras();

		radioButtons = new RadioButton[4];
		movieRadioGroup = (RadioGroup) findViewById(R.id.movieRadioGroup);
		radioButtons[0] = ((RadioButton) findViewById(R.id.movie1));
		radioButtons[1] = ((RadioButton) findViewById(R.id.movie2));
		radioButtons[2] = ((RadioButton) findViewById(R.id.movie3));
		radioButtons[3] = ((RadioButton) findViewById(R.id.movie4));
		radioButtons[0].setText(extras.getString("movie1"));
		radioButtons[1].setText(extras.getString("movie2"));
		radioButtons[2].setText(extras.getString("movie3"));
		radioButtons[3].setText(extras.getString("movie4"));
		
		intruder = extras.getInt("intruder");
		explanation = extras.getString("explanation");
		msetId = extras.getLong("msetId");
		isPoorlyRatedMset = extras.getBoolean("isPoorlyRated");

		submitSetButton = (Button) findViewById(R.id.submitSetButton);
		submitSetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(intent);
				
				// get selected radio button from radioGroup
				int selectedMovieId = movieRadioGroup.getCheckedRadioButtonId();
				RadioButton selectedRadioButton = (RadioButton) findViewById(selectedMovieId);
				
				Bundle bundle = new Bundle();

				bundle.putBoolean("correctAnswer", isCorrectAnswer(selectedRadioButton));
				bundle.putBoolean("isPoorlyRated", isPoorlyRatedMset);
				bundle.putString("explanation", explanation);
				bundle.putLong("msetId", msetId);
				

				Intent intent = new Intent(activity, SetInfoActivity.class);
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});
	}
	
	private boolean isCorrectAnswer(RadioButton selectedRadioButton) {
		return selectedRadioButton == radioButtons[intruder]; 
	}

}
