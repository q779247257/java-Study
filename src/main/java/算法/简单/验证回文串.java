package 算法.简单;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 验证回文串 {

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        isPalindrome(str);
    }


    public static boolean isPalindrome(String s) {
        StringBuilder antitoneBuilder = new StringBuilder();

        char[] chars = s.toCharArray();

        //筛选出是字母或者数字的
        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.isLetterOrDigit(chars[i])) {
                antitoneBuilder.append(chars[i]);
            }
        }
        String antitone = antitoneBuilder.toString();//反序

        StringBuilder positiveBuilder = new StringBuilder();
        //获取正的字符串
        char[] chars1 = antitone.toCharArray();
        for (int i = chars1.length - 1; i >= 0; i--) {
            if (Character.isLetterOrDigit(chars1[i])) {
                positiveBuilder.append(chars1[i]);
            }
        }
        String positive = positiveBuilder.toString();//正序

        return positive.equalsIgnoreCase(antitone);
    }
}
