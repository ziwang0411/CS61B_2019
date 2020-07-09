import java.util.*;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> chars = new ArrayDeque<Character>();
        for (char ch : word.toCharArray()) {
            chars.addLast(ch);
        }
        return chars;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> chars = wordToDeque(word);

        while (chars.size() > 1) {
            Character first = chars.removeFirst();
            Character last = chars.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> chars = wordToDeque(word);

        while (chars.size() > 1) {
            Character first = chars.removeFirst();
            Character last = chars.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
