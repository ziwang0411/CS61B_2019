import java.util.ArrayList;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{
    private char[] stringToCharArray;

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;
    private int hash;
    private ArrayList<Character> charHolder;
    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        charHolder = new ArrayList<>();
        hash = 0;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            charHolder.add(c);
            int cVal = (int) c;
            hash = ((hash * UNIQUECHARS) % PRIMEBASE + cVal) % PRIMEBASE;
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        char first = charHolder.remove(0);
        int firstValue = (int) first;
        charHolder.add(c);
        int cVal = (int) c;
        hashUpdate(firstValue,cVal);

    }

    private void hashUpdate(int firstValue, int cVal) {
        int tmp = (firstValue * (int)Math.pow(UNIQUECHARS, charHolder.size()-1)) % PRIMEBASE;
        hash = ((hash - tmp + PRIMEBASE) * UNIQUECHARS + cVal) % PRIMEBASE;
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        /* FIX ME */
        for (char c: charHolder) {
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return charHolder.size();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        RollingString oR = (RollingString) o;
        return (toString().equals(oR.toString()));
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        return hash;
    }
}
