package fr.norsys.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    public static int Add(String numbers) {
        if (numbers == "" ) {
            return 0;
        }

        String del = ",";
        if(numbers.startsWith("//")){
            int firstDelIndex = numbers.indexOf("["+1);
            int lastDelIndex = numbers.indexOf("]");

            if(firstDelIndex>0 && lastDelIndex>0){
                del = numbers.substring(firstDelIndex,lastDelIndex);
            }
            else{
                del = numbers.substring(2,numbers.indexOf("\n"));
            }
            numbers = numbers.substring(numbers.indexOf("\n")+1);
        }


        numbers = numbers.trim();

        String[] delimiters = del.split("\\]\\[");

        StringBuilder regexBuilder = new StringBuilder("[,\n");
        for (String delim : delimiters) {
            String escapedDelimiter = delim.replaceAll("([\\[\\](){}+\\.^$|\\\\])", "\\\\$1");
            regexBuilder.append(escapedDelimiter).append("|");
        }
        regexBuilder.deleteCharAt(regexBuilder.length() - 1);
        regexBuilder.append("]");

        String[] A = numbers.split(regexBuilder.toString());

        int s= 0;
        List<Integer> neg = new ArrayList<>();

        for (String nA : A) {
            if (!nA.isEmpty()) {
                try {
                    int n = Integer.parseInt(nA.trim());
                    if (n < 0) {
                        neg.add(n);
                    } else if (n <= 1000) {
                        s += n;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number: " + nA);
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
