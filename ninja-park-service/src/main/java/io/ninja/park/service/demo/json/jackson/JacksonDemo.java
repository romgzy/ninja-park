/**
 * 
 */
package io.ninja.park.service.demo.json.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author romgzy
 *
 */
public class JacksonDemo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Map<String, Object> map = new HashMap();
		// 第一层
		map.put("reqId", "1");
		// message 层
		Map<String, Object> message = new HashMap();
		// 业务 id
		message.put("bizType", "biz1");
		message.put("date", "2019-01-01");
		map.put("message", message);
		String json = objectMapper.writeValueAsString(map);
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode messageNode = node.get("message");
		JsonNode bizTypeNode = messageNode.get("bizType");
		String bizType = bizTypeNode.asText();

		if (bizType.equals("biz1")) {
			RequestBase<Message2> req = objectMapper.readValue(json, new TypeReference<RequestBase<Message2>>() {
			});
			System.out.println(req);
		}
	}

}
