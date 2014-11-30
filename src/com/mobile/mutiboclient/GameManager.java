package com.mobile.mutiboclient;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mobile.mutiboclient.activities.GameOverActivity;
import com.mobile.mutiboclient.activities.SetActivity;
import com.mobile.mutiboclient.client.Mset;

public class GameManager {
	private static Collection<Mset> msets;
	private static Collection<Mset> poorRatedMsets;
	private static Mset currentMset;
	private static Iterator<Mset> msetsIterator;
	
	private static int incorrectGuesses;
	private static volatile int bonusTimeScore;
	private static volatile int score;
	private static int highestScoreUser;

	private GameManager() {
	}

	public static void startGame(Activity activity, Collection<Mset> sets, Collection<Mset> poorRatedSets, int highestScore) {
		msets = sets;
		poorRatedMsets = poorRatedSets;
		msetsIterator = msets.iterator();
		incorrectGuesses = 0;
		score = 0;
		bonusTimeScore = 0;
		highestScoreUser = highestScore;
		loadNextSet(activity);
	}
	
	public static void loadNextSet(Activity activity) {
		Bundle bundle = new Bundle();
		if (msetsIterator.hasNext()) {
			currentMset = (Mset) msetsIterator.next();
			List<String> currentMsetMovies = currentMset.getMovies();
			
			bundle.putString("movie1", currentMsetMovies.get(0));
			bundle.putString("movie2", currentMsetMovies.get(1));
			bundle.putString("movie3", currentMsetMovies.get(2));
			bundle.putString("movie4", currentMsetMovies.get(3));

			bundle.putInt("intruder", currentMset.getUnrelatedMovieIndex());
			bundle.putString("explanation",
					currentMset.getRelationshipExplanation());
			bundle.putLong("msetId", currentMset.getId());
			bundle.putBoolean("isPoorlyRated", isPoorRatedMset(currentMset));

			Intent intent = new Intent(activity, SetActivity.class);
			intent.putExtras(bundle);
			activity.startActivity(intent);
		} else {
			// Congratulations!!! No more sets. The game is completed
			Intent intent = new Intent(activity, GameOverActivity.class);
			bundle.putBoolean("gameWasCompleted", true);
			intent.putExtras(bundle);
			activity.startActivity(intent);
		}
	}
	
	public static void setNewIncorrectGuess() {
		incorrectGuesses++; 
	}
	
	public static int getIncorrectGuesses() {
		return incorrectGuesses;
	}
	
	public static void setScore(int newscore) {
		score = newscore;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void setBonusTimeScore(int newsBonusTimeScore) {
		bonusTimeScore = newsBonusTimeScore;
	}
	
	public static int getBonusTimeScore() {
		return bonusTimeScore;
	}
	
	public static boolean isPoorRatedMset(Mset mset) {
		return poorRatedMsets.contains(mset);
	}
	
	public static int getHighestScoreUser() {
		return highestScoreUser;
	}
}
