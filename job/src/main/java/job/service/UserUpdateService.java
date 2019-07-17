package job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@Slf4j
@Service
public class UserUpdateService {

    @Async("asyncServiceExecutor")
    public void run(String userName, CountDownLatch latch) {
        try {
            log.info("start update {} !",userName);
            System.out.println("userName = " + userName);
            log.info("update {} success !", userName);
        } catch (Exception e) {
            log.error("update {} failed !", userName);
            log.error(e.getMessage(), e);
        } finally {
            latch.countDown();
        }
    }
}
