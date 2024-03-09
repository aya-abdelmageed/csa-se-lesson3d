package com.codedotorg;

public class AppLogic {

    /**
     * Returns the weather option based on the city and button text.
     * 
     * @param city the city for which the weather option is requested
     * @param buttonText the text on the button clicked by the user
     * @return the current weather or 7 day forecast based on the button text,
     *         an error message if the city is empty or null, or an empty string
     *         if the button text is invalid
     */
    public String getWeatherOption(String city, String buttonText) {
        if (buttonText.equals("Get Weather")) {
            return getCurrentWeather(city);
        }
        
        return "";
    }
    
    /**
     * Returns the current weather for the specified city.
     *
     * @param city the name of the city to get the weather for
     * @return a string containing the current weather information for the specified city
     */
    public String getCurrentWeather(String city) {
        String response = WeatherAPI.getWeather(city);
        return response;
    }

    /**
     * Returns the weekly forecast for a given city.
     *
     * @param city the name of the city to get the forecast for
     * @return a string containing the forecast information
     */
    public String getWeekForecast(String city) {
        
        String urlString = BASE_URL + city + "&cnt=7&appid=" + API_KEY;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStream in = conn.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");
                String data = scanner.hasNext() ? scanner.next() : "";
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns an error message if the given city is invalid (empty or null).
     * Otherwise, returns an empty string.
     *
     * @param city the city to validate
     * @return an error message if the city is invalid, otherwise an empty string
     */
    public String getInvalidCity(String city) {

        return "";
    }

}
