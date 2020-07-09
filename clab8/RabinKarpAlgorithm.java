public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int k = pattern.length();
        RollingString rInput = new RollingString(input.substring(0,k), k);
        RollingString rPattern = new RollingString(pattern, k);

        int patternHash = rPattern.hashCode();
        while( k < input.length()+1) {
            if (rInput.hashCode() == patternHash && rInput.equals(rPattern)) return k - pattern.length();
            k++;
            if (k < input.length()+1) {
                rInput.addChar(input.charAt(k-1));
            } else {break;}

        }
        return -1;
    }

}
