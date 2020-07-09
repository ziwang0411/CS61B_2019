import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K k, V v) {
            this.key = k;
            this.val = v;
            this.size = 1;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;

    }

    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set keySet() {
        Set<K> temp = new HashSet<>();
        keySet(root, temp);
        return temp;
    }

    private void keySet(Node T, Set<K> temp) {
        if (T == null) {
            return;
        }
        temp.add(T.key);
        keySet(T.left, temp);
    }

    @Override
    /** Removes the mapping for the specified key from this map if present.*/
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V removed = get(key);
        root = remove(key, root);
        return removed;

    }

    @Override
    /** Removes the entry for the specified key only if it is currently mapped to
     *  the specified value.*/
    public V remove(K key, V value) {
        if (!containsKey(key)) {            // Return null if there is no such key.
            return null;
        }
        if (!get(key).equals(value)) {      // Return null if given value doesn't equal to get(key)
            return null;
        }
        root = remove(key, root);           // Remove (key,value) pair.
        return value;
    }

    public Node remove(K key, Node n) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) n.left = remove(key, n.left);
        else if (cmp > 0) n.right = remove(key, n.right);
        else {
            if (n.right == null) return n.left;
            else if (n.left == null) return n.right;
            else {
                Node temp = n;
                n = min(n.right);
                n.right = deleteMin(temp.right);
                n.left = temp.left;
            }

        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node deleteMin(Node n) {
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node n) {
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();

            if(curr.right !=null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }

    private void printInOrder(Node x) {
//        if (x.left == null && x.right == null) {
//            printNode(x);
//        } else if (x.right == null) {
//            printInOrder(x);
//        }
        if (x == null) return;
        printInOrder(x.left);
        printNode(x);
        printInOrder(x.right);
    }

    private void printNode(Node x) {
        System.out.print(x.key);
        System.out.println(" " + x.val);
    }
}
