# ATB-API-Challenge

Author: `TalhaKhamoor`

![ATB-Result](https://user-images.githubusercontent.com/59896329/136430590-66844955-ad58-427c-b92e-fbb8dff3786e.PNG)

# Steps Complete Before Starting Project

- Forked repository
- Created a new branch and switched into it. 

# Starting Template

The following Java 8 code stub was provided to kick off the challenge

    class Result {

        /*
         * Complete the 'getNumDraws' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER year as a parameter.
         */

        public static int getNumDraws(int year) {
            // Your code here
        }

    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int year = Integer.parseInt(bufferedReader.readLine().trim());

            int result = Result.getNumDraws(year);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

# Description

API URL: `https://jsonmock.hackerrank.com/api/football_matches`

Developed an application that performs an HTTP GET request to a REST API. The API contains information about football matches and can be queried using a given year. 

More specifically this application takes a year and provides a sum of all the drawn matches from that year. It does so by forming a connection to the API, getting the response JSON and using it to get the total number of pages (we are only provided data in sets of 10, the rest is on different pages). From the total number of pages, we can loop through all the results from the given year and compare that match scores. 

The response given from the API is JSON  with the following fields

- page: The current page
- per_page: The maximum number of matches returned per page
- total: The total number of matches on all pages of the result
- total_pages: The total number of pages with results
- data: An array of objects containing matches information on the requested page. 
 
