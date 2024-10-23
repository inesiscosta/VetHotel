package hva.core.caseInsensitiveOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class that implements a custom comparator that ignores case, its case
 * insensitive. This class is a singleton.
 */
public class CaseInsensitiveComparator implements Comparator<String>,
Serializable {

  @Serial
  private static final long serialVersionUID = 202410232302L;

  // Singleton instance
  private static final CaseInsensitiveComparator _caseInsensitiveComparator
  = new CaseInsensitiveComparator();

  private CaseInsensitiveComparator() {}

  /**
   * Method to get the instance of the singleton
   * 
   * @return the instance of the comparator
   */
  public static CaseInsensitiveComparator getComparator() {
    return _caseInsensitiveComparator;
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