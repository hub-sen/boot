package job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@Slf4j
@Service
public class DataSynchronizationService {

	@Autowired
	private StringRedisTemplate redisTemplate;

    @Async("asyncServiceExecutor")
    public void run() {
        try {

        } catch (Exception e) {

        } finally {
        }
    }
}
