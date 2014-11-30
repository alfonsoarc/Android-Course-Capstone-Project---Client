package com.mobile.mutiboclient.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.listeners.LoginListener;

public class LoginActivity extends Activity {

	private Button loginButton;
	private EditText username;
	private EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		username = (EditText) findViewById(R.id.userNameTextInput);
		password = (EditText) findViewById(R.id.passwordInput);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new LoginListener(this));
	}
	
	public String getUsername() {
		return username.getText().toString();
	}
	
	public String getPassword() {
		return password.getText().toString();
	}
}
