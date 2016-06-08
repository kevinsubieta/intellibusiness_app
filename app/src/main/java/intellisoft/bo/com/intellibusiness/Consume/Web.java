package intellisoft.bo.com.intellibusiness.consume;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;
import intellisoft.bo.com.intellibusiness.utils.Functions;


public abstract class Web {
	private final String TAG = "ServiceConsume";
	private boolean hasConnection = false;
	private Context context;
//	public static String WEB_SERVER = "http://192.168.0.103:8680/ServicioWS/Services";
	public static String WEB_SERVER = "http://54.191.255.20:8680/ServicioWS/Services";
	private static String APP_USER = "";
	private static String APP_PASSWORD = "";
	private static final Integer TIMEOUT_CONNECTION = 30;
	private static final Integer TIMEOUT_RESPONSE = 30;
	public Gson gson;


	public Web(Context context) {
		this.context = context;
		this.hasConnection = (Functions.isConnected(context));
		this.initGson();
	}

	public Web(Context context, String web_server) {
		this.context = context;
		this.hasConnection = (Functions.isConnected(context));
		this.WEB_SERVER = web_server;
		this.initGson();
	}

	public boolean isConnected() {
		return this.hasConnection;
	}

	private void initGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		gson = builder.create();
	}



	private HttpURLConnection prepareConnection(HttpURLConnection connection) {
		connection.setConnectTimeout(TIMEOUT_CONNECTION * 1000);
		connection.setReadTimeout(TIMEOUT_RESPONSE * 1000);
		connection.setUseCaches(false);
		connection.setRequestProperty("Cache-Control", "no-cache");
		return connection;
	}

	private HttpURLConnection applySecurity(HttpURLConnection request) {
		request.setRequestProperty("Authorization", "Basic " + Base64.encodeToString((APP_USER + ":" + APP_PASSWORD).getBytes(), Base64.NO_WRAP));
		return request;
	}

	private Response response(HttpURLConnection connection) throws Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		Response obj = new Response();
		obj.setCode(connection.getResponseCode());
		obj.setUrl(connection.getURL().toString());
		obj.setMethod(connection.getRequestMethod());
		obj.setMessage(buffer.toString());
		obj.setLocation(connection.getHeaderField("Location"));
		if (obj.getLocation() != null) {
			try {
				String[] parts = obj.getLocation().split("/");
				String id = parts[parts.length - 1];
				obj.setLocationId(Long.parseLong(id));
			} catch (Exception e) {

			}
		}
		Log.i(TAG, obj.toString());
		return obj;
	}

	public Object getObject(String method, Type type) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				Object result = gson.fromJson(response.getMessage(), type);
				return result;
			}
			throw new Exception("Error GET : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}


	public <T extends Entity> T get(String method, Type type) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);
			connection.setRequestMethod("GET");			
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				T entity = gson.fromJson(response.getMessage(), type);
				return entity;
			}
			throw new Exception("Error GET : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	public <T extends Entity> List<T> getList(String method, Type type) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);			
			connection.setDoInput(true);			
			connection.setRequestMethod("GET");
			Response response = response(connection); //jmmmm
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				List<T> entities = gson.fromJson(response.getMessage(), type);
				return entities;
			}
			throw new Exception("Error GET : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	public <T extends Entity> T getByPost(String method, Type type, HashMap<String, Object> params) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);			
			connection.setDoInput(true);
			connection.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestMethod("POST");
			chargeFormParams(connection, params); 
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				T entity = gson.fromJson(response.getMessage(), type);
				return entity;
			}
			throw new Exception("Error POST : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}
	
	public <T extends Entity> List<T> getListByPost(String method, HashMap<String, Object> params, Type type) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);			
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			chargeFormParams(connection, params); 
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				List<T> entities = gson.fromJson(response.getMessage(), type);
				return entities;
			}
			throw new Exception("Error POST : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	public List<String> getListString(String method) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			prepareConnection(connection);			
			connection.setRequestMethod("GET");
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				List<String> entities = gson.fromJson(response.getMessage(), new TypeToken<ArrayList<String>>() {}.getType());
				return entities;
			}
			throw new Exception("Error GET : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	private void chargeFormParams(HttpURLConnection connection, HashMap<String, Object> params)throws Exception {
		Uri.Builder builder = new Uri.Builder();
		for (java.util.Map.Entry<String, Object> entry : params.entrySet()) {
			builder.appendQueryParameter(entry.getKey(), entry.getValue().toString());			
		}		
		String query = builder.build().getEncodedQuery();
		OutputStream os = connection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(query);
		writer.flush();
		writer.close();
		os.close();
	}

	public <T extends Entity> long post(String method, T obj) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			String postData = gson.toJson(obj);
			prepareConnection(connection);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestMethod("POST");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			writer.write(postData);
			writer.close();
			wr.close();

			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				return 1L;
			}
			throw new Exception("Error POST : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		} finally {
			connection.disconnect();
		}
	}

	public <T extends Entity> long put(String method, Type type, T obj) throws Exception {
		URL url = new URL(WEB_SERVER + method);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			String postData = gson.toJson(obj);
			prepareConnection(connection);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("PUT");
			DataOutputStream osw = new DataOutputStream(connection.getOutputStream());
			osw.write(postData.getBytes());
			osw.flush();
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_ACCEPTED) {
				return response.getLocationId();
			}
			throw new Exception("Error POST : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		} finally {
			connection.disconnect();
		}
	}

	public <T extends Entity> boolean delete(String method, Type type, T obj) throws Exception {
		URL url = new URL(WEB_SERVER + method + "/" + obj.getId());
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			applySecurity(connection);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setRequestMethod("DELETE");
			connection.disconnect();
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				return true;
			}
			throw new Exception("Error GET : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	public String post(File file) throws Exception {
		URL url = new URL(WEB_SERVER + "/api/files");
		Response response = this.upload(file, url, "POST");
		String location = response.getLocation().replace(response.getUrl(), "").replace("/", "");
		return location;
	}

	public String put(File file, String guid) throws Exception {
		URL url = new URL(WEB_SERVER + "/api/files/" + guid);
		Response response = this.upload(file, url, "PUT");
		String location = response.getLocation().replace(response.getUrl(), "").replace("/", "");
		return location;
	}

	public boolean delete(String guid) throws Exception {
		URL url = new URL(WEB_SERVER + "/api/files/" + guid);
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			applySecurity(connection);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setRequestMethod("DELETE");
			connection.disconnect();
			Response response = response(connection);
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				return true;
			}
			throw new Exception("Error DELETE : " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	private Response upload(File file, URL url, String method) throws Exception {
		String boundary = "*****";
		String fileName = file.getName();
		String crlf = "\r\n";
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			applySecurity(connection);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			OutputStream outputStream = connection.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
			writer.append("--" + boundary).append(crlf);
			writer.append("Content-Disposition: form-data; name=\"data\"; filename=\"" + fileName + "\"").append(crlf);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(crlf);
			writer.append("Content-Transfer-Encoding: binary").append(crlf);
			writer.append(crlf);
			writer.flush();
			FileInputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			inputStream.close();
			writer.append(crlf);
			writer.flush();
			writer.append(crlf).flush();
			writer.append("--" + boundary + "--").append(crlf);
			writer.close();
			connection.disconnect();
			Response response = response(connection);
			if ((response.getCode() == HttpURLConnection.HTTP_ACCEPTED) || (response.getCode() == HttpURLConnection.HTTP_CREATED)) {
				return response;
			}
			throw new Exception("Error POST/PUT : " + response.getCode());
		} catch (IOException e) {
			throw e;
		}
	}

	public Object postMultiparts(String method, Type type, HashMap<String, Object> formParams, HashMap<String, File> fileParams) throws Exception {
		try {
			MultipartUtility multipart = new MultipartUtility(WEB_SERVER + method, "UTF-8");
			if (formParams != null) {
				for (java.util.Map.Entry<String, Object> entryFormParam : formParams.entrySet()) {
					multipart.addFormField(entryFormParam.getKey(), String.valueOf(entryFormParam.getValue()));
				}
			}
			if (fileParams != null) {
				for (java.util.Map.Entry<String, File> entryFileParam : fileParams.entrySet()) {
					multipart.addFilePart(entryFileParam.getKey(), entryFileParam.getValue());
				}
			}
			multipart.prepareToSend();
			Response response = response(multipart.getHttpConn());
			if (response.getCode() == HttpURLConnection.HTTP_OK) {
				Object result = gson.fromJson(response.getMessage(), type);
				return result;
			}
			throw new Exception("Error POST MULTIPART: " + response.getCode());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}
		
}
