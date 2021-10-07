package drawn.matches;

import java.io.IOException;
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

		System.out.println(connection.getResponseCode());
		return 0;
	}
}
