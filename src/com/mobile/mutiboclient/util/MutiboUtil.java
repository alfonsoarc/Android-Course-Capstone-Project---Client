package com.mobile.mutiboclient.util;

import com.mobile.mutiboclient.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ContextThemeWrapper;

public class MutiboUtil {

	public static boolean isInternetOn(Activity activity) {
		boolean isOn = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			isOn = networkInfo.getState() == NetworkInfo.State.CONNECTED
					|| networkInfo.getState() == NetworkInfo.State.CONNECTING;
		}
		return isOn;
	}
	
	public static void displayAlertDialog(Activity activity, int title, int message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(activity, R.style.AlertDialogCustom));

		builder.setTitle(title)
				.setMessage(message)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
