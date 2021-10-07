package drawn.matches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Result {

	/*
	 * Complete the 'getNumDraws' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts INTEGER
	 * year as a parameter.
	 */

	public static int getNumDraws(int year) throws IOException {
		HttpURLConnection connection = null;
		String urlString = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year;

		URL url = new URL(urlString);
		connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		BufferedReader reader;
		StringBuffer responseContent = new StringBuffer();
		String line;

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
		
		System.out.println(responseContent.toString());
		return 0;
	}
}
