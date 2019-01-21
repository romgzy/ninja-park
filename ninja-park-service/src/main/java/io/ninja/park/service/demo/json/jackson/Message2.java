/**
 * 
 */
package io.ninja.park.service.demo.json.jackson;

/**
 * @author romgzy
 *
 */
public class Message2 extends BaseMessage {
	private String date;
	
	private String name ;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Message2 [date=" + date + ", name=" + name + ", bizType=" + bizType + "]";
	}

	
}
