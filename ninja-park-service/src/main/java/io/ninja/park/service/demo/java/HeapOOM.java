/**
 * 
 */
package io.ninja.park.service.demo.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author romgzy
 *
 */
public class HeapOOM {
	static class OOMObject{
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}

	}

}
