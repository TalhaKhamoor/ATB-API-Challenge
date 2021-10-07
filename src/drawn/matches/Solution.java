package drawn.matches;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws IOException {
		System.out.print("Please enter the year: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.getNumDraws(year);

        bufferedWriter.write("The amount of draws in the year " + year + " were: " +String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
