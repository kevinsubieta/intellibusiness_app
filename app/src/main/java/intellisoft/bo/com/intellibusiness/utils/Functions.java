package intellisoft.bo.com.intellibusiness.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Functions {

	public static boolean isConnectedToWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo!=null){
			return networkInfo.getType() == ConnectivityManager.TYPE_WIFI ? networkInfo.isConnected() : false;
		}
		return false;

	}

	public static boolean isConnectedToMobile(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo != null){
			return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE ? networkInfo.isConnected() : false;
		}
		return false;
	}

	public static boolean isConnected(Context context) {
		if (isConnectedToWifi(context)) {
			return true;
		} else {
			return isConnectedToMobile(context);
		}
	}

	public static boolean isBluetoothEnabled() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter != null) {
			if (mBluetoothAdapter.isEnabled())
				return true;
		}
		return false;
	}

	public static boolean isEnabledGPS(Context context) {
		LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static String getImei(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	public static double getValue(String str) {
		double value = 0;
		if (str != null && !str.isEmpty()) {
			str = str.replace(",", ".");
			value = Double.parseDouble(str);
		}
		return value;
	}

	public static boolean isValidString(String content) {
		return content != null && !content.isEmpty() && !content.equals("null");
	}

	public static String getUrlThumbnailYoutube(String urlYoutube) {
		try {
			if (isValidString(urlYoutube) && (urlYoutube.contains("www.youtube.com/watch?v=") || urlYoutube.contains("youtu.be"))) {
				String[] val = urlYoutube.contains("youtu.be") ? urlYoutube.split("/") : urlYoutube.split("=");
				String codev = urlYoutube.contains("youtu.be") ? val[val.length - 1] : val[1];
				String videoThumbnail = "http://img.youtube.com/vi/" + codev + "/default.jpg";
				return videoThumbnail;
			}
		} catch (Exception e) {
			Log.e("TAG", "Error al obtener la miniatura de youtube " + urlYoutube + " " + e.getMessage());
		}
		return null;
	}

	/**
	 * Metodo que retorna si hay conexion a internet.
	 * 
	 * @param context
	 *            es el contexto de donde se hace la llamada al metodo.
	 * @return true o false si hubiera o no conectividad a internet.
	 */
	public static boolean isOnline(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public static String formatDouble(double dbl) {
		String doubleString = "";
		DecimalFormat format = new DecimalFormat("#.00");
		doubleString = format.format(Math.round(dbl * 100) / 100d);
		if (dbl < 1) {
			doubleString = "0" + doubleString;
		}
		return doubleString;
	}


	public static void open_url(Context context, String url) {
		try {
			if (!url.contains("http")) {
				url = "http://" + url;
			}
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			context.startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Get the current line number.
	 * 
	 * @return int - Current line number.
	 */
	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static String getClassName() {
		return Thread.currentThread().getStackTrace()[2].getFileName();
	}


	public static void backup(Activity context) {
		try {
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();

			if (sd.canWrite()) {
				String currentDBPath = "//data//" + context.getApplicationContext().getPackageName() + "//databases//history";
				String backupDBPath = "history" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date()) + ".db";
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				FileChannel src = new FileInputStream(currentDB).getChannel();
				FileChannel dst = new FileOutputStream(backupDB).getChannel();
				dst.transferFrom(src, 0, src.size());
				src.close();
				dst.close();
				Toast.makeText(context.getBaseContext(), "Backup realizado", Toast.LENGTH_LONG).show();

			} else {
				Toast.makeText(context.getBaseContext(), "Backup no realizado", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context.getBaseContext(), "Backup no realizado", Toast.LENGTH_LONG).show();

		}
	}

	public static boolean isSDCardPresent() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
}
