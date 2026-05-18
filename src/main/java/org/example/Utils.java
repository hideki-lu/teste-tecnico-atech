package org.example;

import com.google.gson.JsonArray;

public class Utils {
    public static int[] jsonToIntArray(JsonArray jsonArray) {
        if (jsonArray == null || jsonArray.isEmpty()) {
            return new int[0];
        }

        int[] result = new int[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            // getAsInt() converte automaticamente números inteiros
            result[i] = jsonArray.get(i).getAsInt();
        }
        return result;
    }
}
