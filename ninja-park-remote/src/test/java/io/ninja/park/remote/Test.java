/**
 * 
 */
package io.ninja.park.remote;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haochong.z
 *
 */
public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 128 + 256;
		int j = 256;
		System.out.println(i & 16);
		for (int k =0 ; k < 8 ;k++){
			System.out.println((int)Math.pow(2, k));
		}
		String str = "100|66,55:200|567,90:102|43,54";

		StringTokenizer strToke = new StringTokenizer(str, ":,|");// 默认不打印分隔符
		// StringTokenizer strToke=new StringTokenizer(str,":,|",true);//打印分隔符
		// StringTokenizer strToke=new StringTokenizer(str,":,|",false);//不打印分隔符
		while(strToke.hasMoreTokens()){
		    System.out.println(strToke.nextToken());
		}
		logger.error("测试北京我的");
	}

}
