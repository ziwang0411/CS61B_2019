public class BubbleGrid {
    private int[][] A;
    private int[][] grid;
    private int[] dr = {1, 0, -1, 0};
    private int[] dc = {0, 1, 0, -1};
    private int R;
    private int C;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        A = new int[R][C];
        for (int r = 0; r < R; ++r)
            A[r] = grid[r].clone();
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        for (int[] hit : darts)
            A[hit[0]][hit[1]] = 0;
        UnionFind UF = new UnionFind(R * C + 1);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (A[r][c] == 1) {
                    int i = r * C + c;
                    if (r == 0) UF.union(i, R * C);
                    if (r > 0 && A[r - 1][c] == 1) UF.union(i, (r - 1) * C + c);
                    if (c > 0 && A[r][c - 1] == 1) UF.union(i, r * C + c - 1);
                }
            }
        }
        int t = darts.length;
        int[] ans = new int[t--];//Why t-- here?
        while (t >= 0) {
            int r = darts[t][0];
            int c = darts[t][1];
            int preRoof = UF.sizeOf(R * C) - 1;
            if (grid[r][c] == 0) {
                t--;
            } else {
                int i = r * C + c;
                for (int k = 0; k < 4; ++k) { //search for up, down, left, right from point[r][c]
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                        UF.union(i, nr * C + nc);
                }
                if (r == 0) UF.union(i, R * C);
                A[r][c] = 1;
                ans[t--] = Math.max(0, UF.sizeOf(R * C) -1 - preRoof - 1);
            }
        }
        return ans;
    }
}
