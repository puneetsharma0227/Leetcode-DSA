import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                posList.add(i);
                digitList.add(d);
            }
        }

        int k = posList.size();
        int[] pos = new int[k];
        int[] digit = new int[k];

        for (int i = 0; i < k; i++) {
            pos[i] = posList.get(i);
            digit[i] = digitList.get(i);
        }

        long[] pow10 = new long[k + 1];
        pow10[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        long[] prefVal = new long[k + 1];
        long[] prefSum = new long[k + 1];

        for (int i = 0; i < k; i++) {
            prefVal[i + 1] = (prefVal[i] * 10 + digit[i]) % MOD;
            prefSum[i + 1] = prefSum[i] + digit[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long x = (prefVal[right + 1]
                    - (prefVal[left] * pow10[len]) % MOD
                    + MOD) % MOD;

            long sum = prefSum[right + 1] - prefSum[left];

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] a, int target) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private int upperBound(int[] a, int target) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}