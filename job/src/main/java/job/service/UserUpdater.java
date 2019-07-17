package job.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@Slf4j
public class UserUpdater implements Runnable {

    private String userName;

    private CountDownLatch latch;

    public UserUpdater(String userName, CountDownLatch latch) {
        this.userName = userName;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
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
