// Time Complexity : O(d*l+s*l) d: dictioonary words s: sentence words l: average length of a word
// Space Complexity : O(d*l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
Trie
 */

class Solution {

    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        TrieNode(){
            children=new TrieNode[26];
        }
    }

    TrieNode root;

    Solution(){
        root=new TrieNode();
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd=true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String word : dictionary) {
            insert(word);
        }

        String[] sentenceArray = sentence.split(" ");
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < sentenceArray.length; i++) {
            if(i!=0){
                sb.append(" ");
            }
            String sentenceWord = sentenceArray[i];
            TrieNode curr = root;
            StringBuilder replacement=new StringBuilder();
            for (int j = 0; j < sentenceWord.length(); j++) {
                char c = sentenceWord.charAt(j);
                if (curr.children[c - 'a'] == null || curr.isEnd) {
                    break;
                }
                replacement.append(c);
                curr = curr.children[c - 'a'];
            }
            if(curr.isEnd){
                sb.append(replacement.toString());
            }
            else {
                sb.append(sentenceWord);
            }
        }
        return sb.toString();
    }
}
