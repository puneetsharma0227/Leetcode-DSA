import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        // Handle 1 separately
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, cnt % 2 == 0 ? cnt - 1 : cnt);
        }

        for (long start : freq.keySet()) {
            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (true) {
                int cnt = freq.getOrDefault(cur, 0);

                if (cnt >= 2) {
                    len += 2;
                    if (cur > 1000000000L) break;
                    long next = cur * cur;
                    if (next > (long) 1e18) break;
                    cur = next;
                } else if (cnt == 1) {
                    len += 1;
                    break;
                } else {
                    len -= 1; // remove unmatched pair
                    break;
                }
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}