package io.ninja.park.service.demo.dubbo.client;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.io.Bytes;

import io.ninja.park.service.demo.dubbo.api.DemoService;

/**
 * @author romgzy
 *
 */
public class Consumer {
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	String str = "  ByteBufHolder是一个辅助类，是一个接口，其实现类是DefaultByteBufHolder，还有一些实现了ByteBufHolder接口的其他接口类。"
				+ "ByteBufHolder的作用就是帮助更方便的访问ByteBuf中的数据，当缓冲区没用了后，可以使用这个辅助类释放资源。"
				+ "ByteBufHolder很简单，提供的可供访问的方法也很少。如果你想实现一个“消息对象”有效负载存储在ByteBuf，使用ByteBufHolder是一个好主意。"
				+ "尽管Netty提供的各种缓冲区实现类已经很容易使用，但Netty依然提供了一些使用的工具类，使得创建和使用各种缓冲区更加方便。下面会介绍一些Netty中的缓冲区工具类。"
				+ "5.4.1ByteBufAllocator "
				+ " Netty支持各种ByteBuf的池实现，来使Netty提供一种称为ByteBufAllocator成为可能。ByteBufAllocator负责分配ByteBuf实例，"
				+ "ByteBufAllocator提供了各种分配不同ByteBuf的方法，如需要一个堆缓冲区可以使用ByteBufAllocator.heapBuffer()，"
				+ "需要一个直接缓冲区可以使用ByteBufAllocator.directBuffer()，需要一个复合缓冲区可以使用ByteBufAllocator.compositeBuffer()。"
				+ "其他方法的使用可以看ByteBufAllocator源码及注释。";
		StringBuilder hello = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			hello.append(str);
		}
        short    MAGIC              = (short) 0xdabb;
        System.out.println( "MAGIC"+MAGIC ); // 显示调用结果
        
        byte     MAGIC_HIGH         = Bytes.short2bytes(MAGIC)[0];
        System.out.println( "MAGIC_HIGH = "+MAGIC_HIGH ); // 显示调用结果
        byte    MAGIC_LOW          = Bytes.short2bytes(MAGIC)[1];
        System.out.println( "MAGIC_LOW = "+MAGIC_LOW ); // 显示调用结果
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-config-confumer.xml"});
        context.start();
        
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String result = demoService.sayHello(hello.toString()); // 执行远程方法
        System.out.println("======================start=============================" ); // 显示调用结果
        System.out.println("                                                        " );
        System.out.println("                                                        " );
        System.out.println("======================"+ result ); // 显示调用结果
        System.out.println("                                                        " );
        System.out.println("                                                        " );
        System.out.println("======================end=============================" ); 
      
    }

}
