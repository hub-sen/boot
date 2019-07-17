package org.shisen.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shisen.web.models.dto.BaseDto;
import org.shisen.web.models.vo.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <pre>
 * Description  基础服务提供, 可用于数据转换等
 * </pre>
 *
 * @author shishi
 */
@Component
public abstract class BaseService {

	@Autowired
	private ObjectMapper mapper;

	/**
	 * 通过 json 转换, 保证属性一致的转换
	 * DTO conversion to VO
	 *
	 * @param dest   xxxDTO
	 * @param target xxxVO
	 * @param <T>    DTO类型
	 * @param <V>    VO类型
	 * @return 转换后的结果集
	 * @throws IOException  转换异常
	 */
	 public <T extends BaseDto, V extends BaseVo> V conversion(T dest, Class<V> target) throws IOException {
		return mapper.readValue(mapper.writeValueAsString(dest), target);
	}

}
