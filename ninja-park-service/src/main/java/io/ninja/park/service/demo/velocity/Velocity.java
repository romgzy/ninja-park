/**
 * 
 */
package io.ninja.park.service.demo.velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;



import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author haochong.z
 *
 */
public class Velocity {
	private static Properties props = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		alertCustomer();
	

	}

	private static void writeJavaFile(String name, String str) {
		try {
			FileWriter fw = new FileWriter(new File(name), true);
			BufferedWriter bw = new BufferedWriter(fw);

			// 将读入的字符串写入到文件中
			bw.write(str, 0, str.length());
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error-- :" + e.toString());
		}
	}

	private static void loadProperties() {
		props = new Properties();
		try {
			props.load(Velocity.class.getResourceAsStream("action.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void alertCustomer() {
		Properties properties = new Properties();
		// 指定生成静态文档需要的模板文件所在的目录
		/*properties
				.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "org/haochong");*/
		properties.setProperty("file.resource.loader.class", 
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		VelocityEngine engine = new VelocityEngine();

		// 初始化模板引擎
		engine.init(properties);
		/* next, get the Template */
		Template template = engine.getTemplate("T_CUSTOMER.vm", "UTF-8");
		VelocityContext context = new VelocityContext();

		String tableName = "T_CUSTOMER";
		StringWriter writer = new StringWriter();
		for (int i = 0; i < 64; i++) {
			context.put("T_CUSTOMER", tableName + i);

			/* now render the template into a StringWriter */

			template.merge(context, writer);
			/* show the World */
			// System.out.println( writer.toString() );
		}
		writeJavaFile("T_CUSTOMER.sql", writer.toString());
	}

}
