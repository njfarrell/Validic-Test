package com.njfarrell.validictest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    /**
     * Return percent as an integer.
     * @param number number used to calculate percent.
     * @param total total number.
     * @return percent as int.
     */
    public static int getPercentage(int number, int total) {
        if (total == 0) {
            return 0;
        }
        float proportion = ((float) number) / ((float) total);
        return (int) (proportion * 100);
    }

    /**
     * Read an input stream and return a string response.
     * @param inputStream input stream.
     * @return String response from an input stream.
     * @throws IOException caused by I/O error with BufferedReader.
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        bufferedReader.close();
        return builder.toString();
    }
}
