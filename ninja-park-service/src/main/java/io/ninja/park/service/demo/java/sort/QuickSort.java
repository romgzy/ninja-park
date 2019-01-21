/**
 * 
 */
package io.ninja.park.service.demo.java.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @author romgzy
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[] { 8, 6, 7, 9, 4, 11, 10 };
		sort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}

	public static void sort(int[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(int[] a, int lo, int hi) {
		// 左指针
		int i = lo;
		// 右指针
		int j = hi + 1;
		// 哨兵
		int v = a[lo];
		while (true) {
			// find item on lo to swap
			while (less(a[++i], v)) {
				if (i == hi)
					break;
			}

			// find item on hi to swap
			while (less(v, a[--j])) {
				// redundant since a[lo] acts as sentinel
			}

			// check if pointers cross
			if (i >= j)
				break;

			exch(a, i, j);
		}

		// put partitioning item v at a[j]
		exch(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(int v, int w) {
		if (v == w)
			return false; // optimization when reference equals
		return v - w < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

}
