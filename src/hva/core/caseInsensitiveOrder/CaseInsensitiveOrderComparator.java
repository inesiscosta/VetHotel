package hva.core.caseInsensitiveOrder;

import java.util.Comparator;
import java.io.Serializable;

/**
 * Class that implements a custom comparator that ignores case, its case
 * insensitive
 */
public class CaseInsensitiveOrderComparator implements Comparator<String>,
 Serializable {

    /**
     * Override of the method compare to do it in a case insensitive matter
     * 
     * @param s1 the first id a string
     * @param s2 the second id a string
     * 
     * @return the int that result from the compareToIgnoreCase between
     * the two strings
     */
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