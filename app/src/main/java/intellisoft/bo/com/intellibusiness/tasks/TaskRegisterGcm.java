package intellisoft.bo.com.intellibusiness.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.security.MessageDigest;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

public class TaskRegisterGcm extends AsyncTask<String, Void, String> {

	private GoogleCloudMessaging gcm;
	private Activity activity;
	private PreferencesManager prefs;
	private final String TAG= "android.util.Log.TaskRegisterGcm";

	public TaskRegisterGcm(Activity activity) {
		this.activity = activity;
		prefs = new PreferencesManager(activity);
	}

	@Override
	protected void onPreExecute() {
		Log.i(TAG, "Iniciando la verificación del registro del ID de GCM....");
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... arg0) {
	//	Client client = prefs.getClient();
		try {
		//	String gcmRegID = prefs.getRegistration_GCM_Id();
			gcm = GoogleCloudMessaging.getInstance(activity);
			String gcmRegID = gcm.register(activity.getResources().getString(R.string.senderID));
			//if (gcmRegID != null && !gcmRegID.isEmpty()) {
				Log.d(TAG, "Device registered, registration GCM ID = " + gcmRegID);
			//	prefs.setRegistration_GCM_Id(activity, gcmRegID);
			//	client = updateRegistrationGCMID(client, gcmRegID);
			} catch (IOException e) {
			e.printStackTrace();
		}
//		else {
//				Log.i(App.TAG, "Empty Registration GCM ID");
//				client = null;
//				}
			catch (Exception e1) {
			e1.printStackTrace();
		}
//		else {
//				if (!client.getApn_gcm().equals(gcmRegID)) {
//					client = updateRegistrationGCMID(client, gcmRegID);
//				}
//			}
//		} catch (Exception e) {
//			Log.e(App.TAG, "Ocurrio un error en el registro de GCM. : " + e.getMessage());
//			e.printStackTrace();
//		}
	//	Log.i(TAG,client != null? "Registro GCM correcto.":"Registro GCM incorrecto.");

		return null;
	}

//	private Client updateRegistrationGCMID(Client client, String gcmId) throws Exception {
//		Services services = new Services(activity);
//		client.setApn_gcm(gcmId);
//		return services.registry(client);
//	}

	private void getHashKey() {
		try {
			PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.e(TAG, Base64.encodeToString(md.digest(), Base64.DEFAULT));
				System.out.println(Base64.encodeToString(md.digest(), Base64.DEFAULT));
				String s = Base64.encodeToString(md.digest(), Base64.DEFAULT);
				s = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, activity, AppStatics.PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "Este Dispositivo tiene una versión de Google Play Services que no e soportada.");
				activity.finish();
			}
			return false;
		}
		return true;
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			throw new RuntimeException("Could not get package name: " + e);
		}
	}
}
