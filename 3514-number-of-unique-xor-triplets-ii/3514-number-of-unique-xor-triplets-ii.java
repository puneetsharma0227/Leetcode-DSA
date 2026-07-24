class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] present = new boolean[MAX];
        for (int x : nums) {
            present[x] = true;
        }

        boolean[] dp = new boolean[MAX];
        dp[0] = true;

        for (int step = 0; step < 3; step++) {
            boolean[] next = new boolean[MAX];

            for (int xor = 0; xor < MAX; xor++) {
                if (!dp[xor]) continue;

                for (int v = 0; v < MAX; v++) {
                    if (present[v]) {
                        next[xor ^ v] = true;
                    }
                }
            }

            dp = next;
        }

        int ans = 0;
        for (boolean ok : dp) {
            if (ok) ans++;
        }

        return ans;
    }
}