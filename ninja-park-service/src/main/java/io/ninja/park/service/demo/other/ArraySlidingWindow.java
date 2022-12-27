package io.ninja.park.service.demo.other;

import java.util.HashMap;
import java.util.Map;

public class ArraySlidingWindow {
    public static void main(String[] args) {
        int[] a = new int[]{-1,-1};
        System.out.println(minWindow("1ABddfdCBBAODEDANC","ABCB"));
    }

    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public static String minWindow(String source , String target) {
        // 初始化counter_s和counter_t
        Map<Character, Integer> counter_t = new HashMap<Character, Integer>();
        Map<Character, Integer> counter_s = new HashMap<Character, Integer>();
        for (int i = 0; i < target.length(); i++) {
            int count = counter_t.getOrDefault(target.charAt(i), 0);
            counter_t.put(target.charAt(i), count + 1);
        }

        int left = 0, valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = -1, minlen = Integer.MAX_VALUE;
        for (int right = 0; right < source.length(); right ++){
            // 移动右边界, ch 是将移入窗口的字符
            char ch = source.charAt(right);
            if (counter_t.containsKey(ch)) {
                counter_s.put(ch, counter_s.getOrDefault(ch, 0) + 1);
                if (counter_s.get(ch).compareTo(counter_t.get(ch)) == 0) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == counter_t.size()) {
                // 更新最小覆盖子串
                if (right - left < minlen) {
                    start = left;
                    minlen = right - left;
                }
                // left_ch 是将移出窗口的字符
                char left_ch = source.charAt(left);
                // 左移窗口
                left ++;
                // 进行窗口内数据的一系列更新
                if (counter_t.containsKey(left_ch)) {
                    if (counter_t.get(left_ch).equals(counter_s.get(left_ch))) {
                        valid--;
                    }
                    counter_s.put(left_ch, counter_s.getOrDefault(left_ch, 0) - 1);
                }
            }
        }
        // 返回最小覆盖子串
        return start == -1 ? "" : source.substring(start, start + minlen  + 1);
    }
}
