/**
 * 
 */
package io.ninja.park.service.demo.java.sort;

import java.util.Arrays;

/**
 * 简单选择排序
 * 
 * @author romgzy
 *
 */
public class SelectSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[] { 5, 4, 3, 2, 1 };
		sort(a);
		System.out.println(Arrays.toString(a));

	}

	public static void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				// 查找
				if (a[j] < a[min]) {
					min = j;
				}
			}
			int temp = a[i];
			// 交换位置
			a[i] = a[min];

			a[min] = temp;
		}
	}

}
