package 算法.简单;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/21
 * @描述：
 */
public class 最长公共前缀优化 {

    public static void main(String[] args) {
//        String[]  strings =  {"abcd","abcdef","abcdefg"};
//        String[]  strings =  {"flower","flow","flight"};
//        String[]  strings =  {"aca","cba"};
//        String[]  strings =  {"a"};
        String[]  strings =  {"c","c"};
        最长公共前缀优化 最长公共前缀优化 = new 最长公共前缀优化();
        String s = 最长公共前缀优化.longestCommonPrefix(strings);
        System.out.println("info:"+s);


    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1)return strs[0];
        String result = "";
        String cache = "";//缓存相同前缀
        //遍历所有的字符串
        for(int i=0 ; i<strs.length ; i++){
            //判断是否为最后一次循环防止越界
            if (i+1 < strs.length){
                //判断是否为第一次循环
                String itemStr;
                //获取下一个元素
                String nextStr = strs[i+1];
                if (i==0){
                     itemStr = strs[i];//获取当前元素
                }else {
                     itemStr = cache;//todo 不是第一次循环
                }
                //获取最小的字符串长度
                int minLength = minLength(itemStr, nextStr);
                cache = alike(itemStr, nextStr, minLength);
            }else {
                //todo 最后一次循环
                result+=cache;
            }
        }

        return result;
    }

    //最小字符串长度
    public int minLength(String s1 , String s2){
        return s1.length() > s2.length() ? s2.length() : s1.length();
    }

    /**
     * 找相同前缀
     * @param minLength 最小循环长度
     */
    public String alike(String s1 ,String s2 , int minLength){
        String result = "";
        for (int i=0 ; i<minLength ; i++){
            boolean isAlike = s1.charAt(i) == s2.charAt(i);
            if (isAlike){
                result += s1.charAt(i);
            }else {
                //不相同直接return
                return result;
            }
        }
        return result;
    }

}
