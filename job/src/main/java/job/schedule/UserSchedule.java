package job.schedule;

import job.service.UserUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
@Slf4j
@Component
public class UserSchedule {

    @Autowired
    private UserUpdateService userUpdateService;

    private Boolean isRunning = false;

    @Scheduled(fixedDelayString = "${schedules}")
    public void excute() {
        if (isRunning) {
            return;
        }

        isRunning = true;

        try {
            ArrayList<String> users = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                users.add("施森" + i);
            }
            CountDownLatch latch = new CountDownLatch(users.size());
            for (String user : users) {
                userUpdateService.run(user, latch);
            }
            latch.await();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            isRunning = false;
        }


    }


}
