package drawn.matches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Tree
 *
 */
public class Result {

	/**
	 * Makes connection to given URL then gets Json object using the URL and making a 
	 * GET request to the API. From that object we extract the total pages for the year
	 * we queried. Then using that we loop through all the pages comparing data and 
	 * increment the drawn matches we found.
	 * @param year
	 * @return numDraws
	 * @throws IOException
	 */
	public static int getNumDraws(int year) throws IOException {
		int numDraws = 0;
		// forming connection
		HttpURLConnection connection = null;
		String urlString = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year;
		connection = getConnection(urlString, connection);
		
		// creating json object using retrieved string version of json
		String jString = getJSONString(connection);
		JSONObject jObj = new JSONObject(jString);
		
		// looping through each page
		for (int page = 1; page <= jObj.getInt("total_pages"); page++) {
			// making connection to each page
			String urlStringWithPage = urlString + "&page=" + page;
			connection = getConnection(urlStringWithPage, connection);
			// creating json object to better work with
			jString = getJSONString(connection);
			jObj = new JSONObject(jString);
			JSONArray arr = jObj.getJSONArray("data");
			
			// comparison of data array
			for (int i = 0; i < arr.length(); i++) {
				int team1goals = Integer.parseInt(arr.getJSONObject(i).getString("team1goals"));
				int team2goals = Integer.parseInt(arr.getJSONObject(i).getString("team2goals"));

				if (team1goals == team2goals)
					numDraws++;
			}
		}
		return numDraws;
	}
	
	/**
	 * given a url string and passing connection object we return a 
	 * successfully created connection back or catch exception
	 * @param urlString
	 * @param connection
	 * @return HttpURLConnection
	 */
	private static HttpURLConnection getConnection(String urlString, HttpURLConnection connection) {
		try {
			// establishing connection
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
	
	/**
	 * using an established connection we check if its status is 200. If it is we
	 * get the input stream, convert to string and return
	 * @param connection
	 * @return String
	 */
	private static String getJSONString(HttpURLConnection connection) {
		BufferedReader reader;
		StringBuffer responseContent = new StringBuffer();
		String line;
		try {
			// if connection response code is 200 then we read the data and append 
			if (connection.getResponseCode() > 299) {
				System.out.println("Connection was not formed");
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return responseContent.toString();
	}
}
