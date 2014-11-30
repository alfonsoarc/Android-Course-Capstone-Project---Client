package com.mobile.mutiboclient.listeners;

import retrofit.client.ApacheClient;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.mobile.mutiboclient.R;
import com.mobile.mutiboclient.activities.LoginActivity;
import com.mobile.mutiboclient.activities.MainActivity;
import com.mobile.mutiboclient.client.EasyHttpClient;
import com.mobile.mutiboclient.client.MutiboSvcApi;
import com.mobile.mutiboclient.client.SecuredRestBuilder;
import com.mobile.mutiboclient.util.MutiboUtil;

public class LoginListener implements OnClickListener {

	private LoginActivity activity;
	private String username;
	private String password;
	private boolean loggedIn = false;
	
	private MutiboSvcApi readWriteMutiboSvcUser1;
	private final String TEST_URL = "https://192.168.1.8:8443";
	private final String CLIENT_ID = "mobile";

	private ProgressDialog progressDialog;

	public LoginListener(LoginActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		username = activity.getUsername();
		password = activity.getPassword();
		if (MutiboUtil.isInternetOn(activity)) {
			LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
			loginAsyncTask.execute();
		} else {
			MutiboUtil.displayAlertDialog(activity, R.string.dialog_title_no_internet,
					R.string.dialog_message_no_internet);
		}
	}
	
	private class LoginAsyncTask extends AsyncTask<Void, Void, Void> {
		public LoginAsyncTask() {
			readWriteMutiboSvcUser1 = new SecuredRestBuilder()
					.setClient(new ApacheClient(new EasyHttpClient()))
					.setEndpoint(TEST_URL)
					.setLoginEndpoint(TEST_URL + MutiboSvcApi.TOKEN_PATH)
					// .setLogLevel(LogLevel.FULL)
					.setUsername(username).setPassword(password)
					.setClientId(CLIENT_ID).build().create(MutiboSvcApi.class);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(activity);
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				loggedIn = readWriteMutiboSvcUser1.login();
			} catch (Exception e) {
			
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();
			
			if(loggedIn) {
				SecuredRestBuilder.createMutiboService(username, password);
				Intent intent = new Intent(activity, MainActivity.class);
				activity.startActivity(intent);
			} else {
				Toast toast = Toast.makeText(activity, R.string.login_unsuccessful_text, Toast.LENGTH_SHORT);
				toast.show();
			};
		}
	}
}