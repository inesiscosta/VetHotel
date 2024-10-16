package hva.core;

import java.util.Comparator;
import java.io.Serializable;

public class CaseInsensitiveOrderComparator implements Comparator<String>, Serializable {
    @Override
    public int compare(String s1, String s2) {
        return (s1.compareToIgnoreCase(s2) != 0) ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
    }
}