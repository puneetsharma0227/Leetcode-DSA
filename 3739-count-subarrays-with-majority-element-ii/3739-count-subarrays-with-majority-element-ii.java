class Solution {

    class BinaryIndexedTree {
        private final int[] tree;
        private final int n;

        BinaryIndexedTree(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void update(int idx, int delta) {
            while (idx <= n) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        boolean found = false;
        for (int x : nums) {
            if (x == target) {
                found = true;
                break;
            }
        }
        if (!found) return 0;

        BinaryIndexedTree bit = new BinaryIndexedTree(2 * n + 1);

        int prefix = n + 1;
        bit.update(prefix, 1);

        long ans = 0;

        for (int x : nums) {
            if (x == target) {
                prefix++;
            } else {
                prefix--;
            }

            ans += bit.query(prefix - 1);
            bit.update(prefix, 1);
        }

        return ans;
    }
}