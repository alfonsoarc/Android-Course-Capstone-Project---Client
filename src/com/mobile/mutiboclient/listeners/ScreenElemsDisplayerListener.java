package com.mobile.mutiboclient.listeners;

import com.mobile.mutiboclient.R;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class ScreenElemsDisplayerListener implements AnimationListener {
	
	private Activity activity;
	
	public ScreenElemsDisplayerListener(Activity activity){
		this.activity = activity;
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		activity.findViewById(R.id.newGameButton).setVisibility(View.VISIBLE);
		activity.findViewById(R.id.highScoresButton).setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}
}