package com.tinkoff.edu.Helpers;

public class RandomStringHelper {
    public static String randomAlphabetString(int size) {
        String AlphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = (int) (AlphabetString.length() * Math.random());
            sb.append(AlphabetString.charAt(index));
        }
        return sb.toString();
    }
}
