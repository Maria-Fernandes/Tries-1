// Time Complexity : O(w*l)
// Space Complexity : O(w*l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
Trie
 */
class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word : words) {
            trie.insert(word, ++index);
        }
        trie.words = words;
        return trie.dfs();
    }
}

class TrieNode {
    int index;
    TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;
    String[] words;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int index) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.index = index;
    }

    public String dfs() {
        String ans = "";
        Stack<TrieNode> stack = new Stack();
        stack.push(root);
        while (!stack.empty()) {
            TrieNode node = stack.pop();
            if (node.index > 0 || node == root) {
                if (node != root) {
                    String word = words[node.index - 1];
                    if (word.length() > ans.length() ||
                            word.length() == ans.length() && word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null)
                        stack.push(node.children[i]);
                }
            }
        }
        return ans;
    }
}
