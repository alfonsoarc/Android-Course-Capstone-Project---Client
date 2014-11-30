package com.mobile.mutiboclient.services;

import com.mobile.mutiboclient.GameManager;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class ScoreBonusService extends IntentService {

	public ScoreBonusService() {
		super("ScoreBonusService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		GameManager.setBonusTimeScore(10);
		while (GameManager.getBonusTimeScore() > 0) {
			SystemClock.sleep(2000);
			Log.i("ScoreBonusService", "bonusTimeScore: " + Integer.toString(GameManager.getBonusTimeScore()));
			GameManager.setBonusTimeScore(GameManager.getBonusTimeScore() - 1);
		}
	}

}
