import java.security.Key;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /**
     * Returns the internal path length for an optimum binary search tree of
     * size N. Examples:
     * N = 1, OIPL: 0
     * N = 2, OIPL: 1
     * N = 3, OIPL: 2
     * N = 4, OIPL: 4
     * N = 5, OIPL: 6
     * N = 6, OIPL: 8
     * N = 7, OIPL: 10
     * N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int result = 0;
        int count = 0;
        int temp = N;
        while (temp != 1) {
            count++;
            temp = temp / 2;
        }
        for (int i = 0; i < count; i++) {
            result = result + (int) Math.pow(2, i) * i; //full BSTs, with # Nodes * length(level depth);
        }
        result = result + (N - (int) Math.pow(2, count) + 1) * count;  //the last layer. Previous layer has 2^N-1 nodes
        return result;
    }

    /**
     * Returns the average depth for nodes in an optimal BST of
     * size N.
     * Examples:
     * N = 1, OAD: 0
     * N = 5, OAD: 1.2
     * N = 8, OAD: 1.625
     *
     * @return
     */
    public static double optimalAverageDepth(int N) {

        return (double) optimalIPL(N) / N;
    }

    public static void insertint(BST<Integer> T) {
        int temp = RandomGenerator.getRandomInt(10000);
        while (true) {
            if (!T.contains(temp)) {
                T.add(temp);
                break;
            } else {
                temp = RandomGenerator.getRandomInt(10000);
            }
        }
    }


}
