package hva.core;

import java.util.Comparator;
import java.io.Serializable;

public class CaseInsensitiveOrderComparator implements Comparator<String>, Serializable {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}

//TODO Should this implement Singleton pattern
/* 
 * package hva.core;

import java.util.Comparator;
import java.io.Serializable;

public class CaseInsensitiveOrderComparator implements Comparator<String>, Serializable {
    public static final CaseInsensitiveOrderComparator INSTANCE = new CaseInsensitiveOrderComparator();

    private CaseInsensitiveOrderComparator() {}

    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
*/