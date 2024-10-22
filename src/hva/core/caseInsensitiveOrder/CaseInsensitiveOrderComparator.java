package hva.core.caseInsensitiveOrder;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class that implements a custom comparator that ignores case, its case
 * insensitive. This class is a singleton.
 */
public class CaseInsensitiveOrderComparator implements Comparator<String>,
 Serializable {

    /**
     * The instance of the CaseInsensitiveOrderComparator
     */
    private static final CaseInsensitiveOrderComparator _caseInsensitiveOrderComparator = new CaseInsensitiveOrderComparator();

    /**
     * The private constructor of the CaseInsensitiveOrderComparator
     */
    private CaseInsensitiveOrderComparator() {}

    public static CaseInsensitiveOrderComparator getComparator() {
        return _caseInsensitiveOrderComparator;
    }

    /**
     * Override of the method compare to do it in a case insensitive matter
     * Used to compare ids to order hashmaps
     * 
     * @param s1 the first string
     * @param s2 the second string
     * 
     * @return the int that result from the compareToIgnoreCase between
     * the two strings
     */
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}