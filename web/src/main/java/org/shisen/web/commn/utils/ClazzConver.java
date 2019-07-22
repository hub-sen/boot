package org.shisen.web.commn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
@Component
public class ClazzConver {

	private static ObjectMapper mapper;

	@Autowired
	private void init(ObjectMapper mapper) {
		ClazzConver.mapper = mapper;
	}

	/**
	 * 通过 json 转换, 保证属性一致的转换
	 *
	 * @return 转换后的结果集
	 * @throws IOException 转换异常
	 */
	public static <T, V> V conversion(T dest, Class<V> target) throws IOException {
		return mapper.readValue(mapper.writeValueAsString(dest), target);
	}

}
