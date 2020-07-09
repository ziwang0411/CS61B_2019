import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;


    private class Node {
        public boolean isKey;
        private Map<Character, Node> map;
        Object val;

        public Node(char c, boolean b) {
            this.val = c;
            this.isKey = b;
            this.map = new HashMap<>();
        }

        public Node() {
            this.isKey = false;
            this.val = null;
            this.map = new HashMap<>();
        }
    }
    public MyTrieSet() { root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        if (key == null|| key.length() <1) throw new IllegalArgumentException();
        Node n = get(key);
        return n!= null && n.isKey;
    }

    private Node get(String key) {
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }


    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        if (prefix == null || prefix.length() < 1) {
            return null;}

        Node curr = get(prefix);
        collect(curr, new StringBuilder(prefix), results);
        return results;
    }
    private void collect (Node x, StringBuilder result, List<String> results) {
        if (x == null) return;
        if (x.isKey) {results.add(result.toString());}
        for (Character c: x.map.keySet()) {
            Node next = x.map.get(c);
            StringBuilder nextResult = new StringBuilder(result.toString());
            nextResult.append(next.val);
            collect(next, nextResult, results);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }

}
