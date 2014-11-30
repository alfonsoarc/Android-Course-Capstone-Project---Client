package com.mobile.mutiboclient.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.mutiboclient.GameManager;
import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.asynctasks.ScoreSubmitAsyncTask;
import com.mobile.mutiboclient.listeners.ChangeActivityFromToListener;

public class GameOverActivity extends Activity {

	private TextView gameOverText;
	private Button goBackToMainButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game_over);

		Bundle extras = getIntent().getExtras();
		
		boolean gameWasCompleted = extras.getBoolean("gameWasCompleted");
		gameOverText = (TextView) findViewById(R.id.gameOverText);
		
		if(gameWasCompleted) {
			gameOverText.setText(R.string.congratulations_message);	
		} else {
			gameOverText.setText(R.string.game_over_message);
		}
		
		ScoreSubmitAsyncTask scoreSubmitter = new ScoreSubmitAsyncTask(GameManager.getScore());
		scoreSubmitter.execute();

		goBackToMainButton = (Button) findViewById(R.id.goBackToMainButton);
		goBackToMainButton.setOnClickListener(new ChangeActivityFromToListener(this, MainActivity.class));
	}
}
