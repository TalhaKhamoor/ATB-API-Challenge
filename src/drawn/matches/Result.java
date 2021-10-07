package drawn.matches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Result {

	public static int getNumDraws(int year) throws IOException {
		int numDraws = 0;
		HttpURLConnection connection = null;
		String urlString = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year;
		connection = getConnection(urlString, connection);
		
		String jString = getJSONString(connection);
		JSONObject jObj = new JSONObject(jString);
		
		for (int page = 1; page <= jObj.getInt("total_pages"); page++) {
			String urlStringWithPage = urlString + "&page=" + page;
			connection = getConnection(urlStringWithPage, connection);
			
			jString = getJSONString(connection);
			jObj = new JSONObject(jString);
			JSONArray arr = jObj.getJSONArray("data");

			for (int i = 0; i < arr.length(); i++) {
				int team1goals = Integer.parseInt(arr.getJSONObject(i).getString("team1goals"));
				int team2goals = Integer.parseInt(arr.getJSONObject(i).getString("team2goals"));

				if (team1goals == team2goals)
					numDraws++;

			}

		}
		return numDraws;
	}
	
	private static HttpURLConnection getConnection(String urlString, HttpURLConnection connection) {
		try {
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;

	}
	
	private static String getJSONString(HttpURLConnection connection) {
		BufferedReader reader;
		StringBuffer responseContent = new StringBuffer();
		String line;
		try {
			if (connection.getResponseCode() > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line + "\n");
				}
				reader.close();

			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return responseContent.toString();
	}
}
