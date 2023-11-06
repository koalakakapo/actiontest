package org.example.actiontest.pagemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum StringComparator {
    /**
     * to compare s1 with s2.
     */
    EXACT("", ""),
    /**
     * to compare s1 for s2 as prefix.
     */
    PREFIX("", ".*"),
    /**
     * to compare s1 for s2 as suffix.
     */
    SUFFIX(".*", ""),
    /**
     * to check whether s1 contains s2?
     */
    IN(".*", ".*"),
    /**
     * compare s1 with regexp, s2 will be treated as regexp
     */
    REGEXP("", "");
    String p;
    String s;

    private StringComparator(String p, String s) {
        this.p = p;
        this.s = s;
    }

    public boolean compare(String s1, String s2) {
        if (REGEXP.equals(this)) {
            return s1.matches(s2);
        }
        return s1.matches(p + s2.replaceAll("([\\]\\[\\\\{\\}$\\(\\)\\|\\^\\+.])", "\\\\$1") + s);
    }

    public boolean compareIgnoreCase(String s1, String s2) {
        if (REGEXP.equals(this)) {
            ArrayList<HashMap<String, ArrayList<Integer>>> keepOrig = new ArrayList<>();
            HashMap<String, ArrayList<Integer>> hm;
            if (s2.contains("\\b")) {
                hm = new HashMap<>();
                hm.put("\\b", idxOfRegex(s2, "\\b"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\d")) {
                hm = new HashMap<>();
                hm.put("\\d", idxOfRegex(s2, "\\d"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\s")) {
                hm = new HashMap<>();
                hm.put("\\s", idxOfRegex(s2, "\\s"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\w")) {
                hm = new HashMap<>();
                hm.put("\\w", idxOfRegex(s2, "\\w"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\t")) {
                hm = new HashMap<>();
                hm.put("\\t", idxOfRegex(s2, "\\t"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\r")) {
                hm = new HashMap<>();
                hm.put("\\r", idxOfRegex(s2, "\\r"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\n")) {
                hm = new HashMap<>();
                hm.put("\\n", idxOfRegex(s2, "\\n"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\h")) {
                hm = new HashMap<>();
                hm.put("\\h", idxOfRegex(s2, "\\h"));
                keepOrig.add(hm);
            }
            if (s2.contains("\\v")) {
                hm = new HashMap<>();
                hm.put("\\v", idxOfRegex(s2, "\\v"));
                keepOrig.add(hm);
            }
            String newS2 = s2.toUpperCase();

            for (int i = 0; i < keepOrig.size(); i++) {
                for (Map.Entry<String, ArrayList<Integer>> entry : keepOrig.get(i).entrySet()) {
                    StringBuilder tmpStr = new StringBuilder(newS2);
                    for (int j = 0; j < entry.getValue().size(); j++) {
                        tmpStr.setCharAt((int) entry.getValue().get(j), entry.getKey().charAt(1));
                    }
                    newS2 = tmpStr.toString();
                }

            }

            return compare(s1.toUpperCase(), newS2);
        }
        return compare(s1.toUpperCase(), s2.toUpperCase());
    }

    private ArrayList<Integer> idxOfRegex(String regexStr, String matchStr) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int index = regexStr.indexOf(matchStr);
        while (index >= 0) {
            indexes.add(index + 1);
            index = regexStr.indexOf(matchStr, index + 1);
        }
        return indexes;
    }

}
