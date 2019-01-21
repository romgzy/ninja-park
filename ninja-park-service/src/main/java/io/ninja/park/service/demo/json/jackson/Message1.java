/**
 * 
 */
package io.ninja.park.service.demo.json.jackson;

/**
 * @author romgzy
 *
 */
public class Message1 extends BaseMessage {
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message1 [date=" + date + ", bizType=" + bizType + "]";
	}
	
}
