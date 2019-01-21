/**
 * 
 */
package io.ninja.park.service.demo.nio.demo;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author romgzy
 *
 */
public class BufferPut {

	static private final byte message[] = { 83, 111, 109, 101, 32,  
        98, 121, 116, 101, 115, 46 };  
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		   FileOutputStream fout = new FileOutputStream( "c:\\test.txt" );  
	          
	        FileChannel fc = fout.getChannel();  
	          
	        ByteBuffer buffer = ByteBuffer.allocate( 1024 );  
	          
	        for (int i=0; i<message.length; ++i) {  
	            buffer.put( message[i] );  
	        }  
	          
	        buffer.flip();  
	          
	        fc.write( buffer );  
	          
	        fout.close();  

	}

}
