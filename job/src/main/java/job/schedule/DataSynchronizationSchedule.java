package job.schedule;

import job.service.DataSynchronizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Description  数据同步
 * </pre>
 */
@Slf4j
@Component
public class DataSynchronizationSchedule {

	@Autowired
	private DataSynchronizationService dataSynchronizationService;

	private Boolean isRunning = false;

	@Scheduled(fixedDelayString = "${schedules}")
	public void excute() {
		if (isRunning) {
			return;
		}

		isRunning = true;

		try {
			//CountDownLatch latch = new CountDownLatch(10);
			dataSynchronizationService.run();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			isRunning = false;
		}


	}
}
