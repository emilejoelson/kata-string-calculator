package fr.norsys.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    public static int Add(String numbers) {
        if (numbers == null || numbers.length() ==0) {
            return 0;
        }
        String delimiter = ",";
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);
        }
        numbers = numbers.trim();

        String[] A = numbers.replaceAll(delimiter, ",").split("[,\n]");

        int s = 0;
        List<Integer> neg = new ArrayList<>();

        for (String n : A) {
            if (n.length()!=0) {
                try {
                    int n1 = Integer.parseInt(n.trim());
                    if (n1 < 0) {
                        neg.add(n1);
                    } else if(n1 <= 1000) {
                        s += n1;
                    }


                } catch (NumberFormatException e) {
                    System.err.println("Invalid number: " + n);
                }
            }
        }


        if (!neg.isEmpty()) {
            StringBuilder sb = new StringBuilder("Negatives not allowed: ");
            for (int i = 0; i < neg.size(); i++) {
                sb.append(neg.get(i));
                if (i < neg.size() - 1) {
                    sb.append(", ");
                }
            }
            throw new IllegalArgumentException(sb.toString());
        }

        return s;
    }
}
