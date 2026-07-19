class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];

        // Store last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (visited[ch - 'a']) {
                continue;
            }

            while (stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > ch &&
                   lastIndex[stack.charAt(stack.length() - 1) - 'a'] > i) {

                visited[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(ch);
            visited[ch - 'a'] = true;
        }

        return stack.toString();
    }
}