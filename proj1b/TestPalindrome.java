import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean d = palindrome.isPalindrome("ABBA");
        assertEquals(true, d);
        boolean c = palindrome.isPalindrome("cat");
        assertEquals(false, c);
    }

    @Test
    public void testIsPalindromeCC() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("zyzy", offByOne));
        assertTrue(palindrome.isPalindrome("yyxz", offByOne));
        assertTrue(palindrome.isPalindrome("yyyxz", offByOne));
        assertFalse(palindrome.isPalindrome("zxxz", offByOne));
        assertFalse(palindrome.isPalindrome("xyz", offByOne));
        assertFalse(palindrome.isPalindrome("aa", offByOne));
        assertFalse(palindrome.isPalindrome("zxzx", offByOne));
    }

    @Test
    public void TestIsPalindromeCCN() {
        CharacterComparator offBy3 = new OffByN(3);
        assertFalse(palindrome.isPalindrome("aeea", offBy3));
        assertTrue(palindrome.isPalindrome("aadd", offBy3));
    }
}