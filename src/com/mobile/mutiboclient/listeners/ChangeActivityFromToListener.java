package com.mobile.mutiboclient.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ChangeActivityFromToListener implements OnClickListener {
	private Activity currentActivity;
	private Class<?> nextActivity;
	public ChangeActivityFromToListener(Activity currentActivity, Class<?> nextActivity) {
		this.currentActivity = currentActivity;
		this.nextActivity = nextActivity;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(currentActivity, nextActivity);
		currentActivity.startActivity(intent);
	}
}
