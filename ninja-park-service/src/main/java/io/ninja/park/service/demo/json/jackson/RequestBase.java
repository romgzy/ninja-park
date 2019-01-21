/**
 * 
 */
package io.ninja.park.service.demo.json.jackson;

/**
 * @author romgzy
 *
 */
public class RequestBase<T extends BaseMessage> {
	private String reqId;
	private T message;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "RequestBase [reqId=" + reqId + ", message=" + message + "]";
	}

}
