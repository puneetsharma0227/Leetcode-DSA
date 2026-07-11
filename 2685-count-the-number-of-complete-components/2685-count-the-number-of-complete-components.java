import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] res = dfs(i, graph, visited);
                int nodes = res[0];
                int degreeSum = res[1];

                int edgesCount = degreeSum / 2;
                int required = nodes * (nodes - 1) / 2;

                if (edgesCount == required) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private int[] dfs(int u, List<Integer>[] graph, boolean[] visited) {
        visited[u] = true;

        int nodes = 1;
        int degreeSum = graph[u].size();

        for (int v : graph[u]) {
            if (!visited[v]) {
                int[] next = dfs(v, graph, visited);
                nodes += next[0];
                degreeSum += next[1];
            }
        }

        return new int[]{nodes, degreeSum};
    }
}