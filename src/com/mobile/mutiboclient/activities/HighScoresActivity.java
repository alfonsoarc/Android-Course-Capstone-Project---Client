package com.mobile.mutiboclient.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.listeners.ChangeActivityFromToListener;

public class HighScoresActivity extends Activity {

	  private ListView highScoresListView ;
	  private ArrayAdapter<String> listAdapter;
	  private Button goBackToMainButton;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_high_scores);
	    highScoresListView = (ListView) findViewById( R.id.highScoresListView );
	    
	    Bundle extras = getIntent().getExtras();
	    ArrayList<String> scores = extras.getStringArrayList("scores");
	    
	    listAdapter = new ArrayAdapter<String>(this, R.layout.high_scores_row, scores);
	    highScoresListView.setAdapter( listAdapter );
	    
	    goBackToMainButton = (Button) findViewById(R.id.goBackToMainButtonFromHighScores);
		goBackToMainButton.setOnClickListener(new ChangeActivityFromToListener(this, MainActivity.class));
	  }
}
