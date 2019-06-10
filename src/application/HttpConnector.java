package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpConnector {
	String addr = "http://13.125.71.112:8081/";
	String method;
	String param;
	String sourceUrl;

	public HttpConnector(String method, Map<String, String> map) {
		this.method = method;

		StringBuffer sb = new StringBuffer("{");

		for (String key : map.keySet()) {
			sb.append("\"");
			sb.append(key);
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(map.get(key));
			sb.append("\"");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");

		param = sb.toString();
		sourceUrl = addr + method;
	}

	public Map<String, String> request(List<String> keys) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		URL url = new URL(sourceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setConnectTimeout(5000);
		conn.connect();

		// write
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(param.toString());
		osw.flush();
		// 응답
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String result = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line;
		}
		osw.close();
		br.close();
		conn.disconnect();

		JsonParser jp = new JsonParser();
		JsonObject jsonObject = (JsonObject) jp.parse(result);
		for(String key : keys) {
			map.put(key, jsonObject.get(key).getAsString());
		}
		return map;
	}
	
	public String request() throws Exception {
		URL url = new URL(sourceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setConnectTimeout(5000);
		conn.connect();

		// write
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(param.toString());
		osw.flush();
		// 응답
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String result = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line;
		}
		osw.close();
		br.close();
		conn.disconnect();

		JsonParser jp = new JsonParser();
		JsonObject jsonObject = (JsonObject) jp.parse(result);
		return jsonObject.get("message").getAsString();
	}

}
