package com.mobile.mutiboclient.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.mutiboclient.GameLoader;
import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.listeners.LoadHighScoresListener;
import com.mobile.mutiboclient.listeners.ScreenElemsDisplayerListener;

public class MainActivity extends Activity {

	private Button newGameButton;
	private Button highScoresButton;
	private Animation translateAnimation;
	private TextView animatedText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		newGameButton = (Button) findViewById(R.id.newGameButton);
		newGameButton.setOnClickListener(new GameLoader(this));
		
		highScoresButton = (Button) findViewById(R.id.highScoresButton);
		highScoresButton.setOnClickListener(new LoadHighScoresListener(this));

		translateAnimation = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.translate);
		translateAnimation.setAnimationListener(new ScreenElemsDisplayerListener(this));

		animatedText = (TextView) findViewById(R.id.welcomingText);
	}

	@Override
	protected void onResume() {
		newGameButton.setVisibility(View.INVISIBLE);
		super.onResume();
		animatedText.startAnimation(translateAnimation);
	}
}
