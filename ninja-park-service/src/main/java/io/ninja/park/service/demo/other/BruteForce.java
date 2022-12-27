package io.ninja.park.service.demo.other;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BruteForce {
    static int[][] dfa;

    public static void main(String[] args)throws Exception {
        String M = "abac";
        String N = "dfsdfasfasqlcdgdasfabasdf abac000";
        System.out.println(search(M, N));
        extracted(M, N);
        System.out.println(search1(N, M));
        System.out.println(search2(N, M));
        kmp("ababc");
        for (int[] i : dfa) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println(new String("陈运新".getBytes("UTF-8"),"GBK"));

    }

    private static void extracted(String txt, String pattern) {
        int point = 0;
        int M = pattern.length();
        int N = txt.length();
        int i = 0;
        int j = 0;
        while (i < M && j < N) {
            if (txt.charAt(j) == pattern.charAt(i)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            point++;
        }
        if (j >= M) {
            System.out.println(String.format("==={%s}", (i - M)));
        } else {
            System.out.println(String.format("***{%s}", j));
        }
        System.out.println(String.format("point***{%s}", point));
    }

    private static int search1(String txt, String pattern) {
        int M = pattern.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return -1;
    }

    private static int search2(String txt, String pattern) {
        int j, M = pattern.length();
        int i, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++) {

            if (txt.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) {
            return i;
        }
        return -1;
    }

    public static int search(String pattern, String txt) {
        int point = 0;
        int M = pattern.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                point++;
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            if (j == M) {
                System.out.println(String.format("point***{%s}", point));
                return i;

            }
        }
        System.out.println(String.format("point***{%s}", point));
        return N;

    }

    public static void kmp(String pattern) {
        int R = 128;
        int M = pattern.length();
        // dfa[字符][状态] = 下个状态
        dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];
            }
            dfa[pattern.charAt(j)][j] = j + 1;
            // 更新影子状态
            x = dfa[pattern.charAt(j)][x];
            System.out.println(String.format("{%s},{%s}", x, j));
        }
    }
}
