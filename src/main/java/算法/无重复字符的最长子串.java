package 算法;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 轩轩
 * @Date: 2020/3/26 14:00
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class 无重复字符的最长子串 {
    @Test
    public void codeTest(){
        int ascdfe = lengthOfLongestSubstring("ascdfsef");
        System.out.println("结果："+ascdfe);
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }


    /**
     * 检测一个字符串是否有重复字符，我们可以使用set集合，我们遍历字符串中的所有字符，并将他们逐个放入 set 中。
     * 在放置一个字符之前，我们检查该集合是否已经包含它。如果包含，我们返回false，循环结束后，我们返回true
     * @param s
     * @return
     */
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}
