package old;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DBTest {

	public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        WorkerThread worker = new WorkerThread("processing...");
        scheduledThreadPool.scheduleWithFixedDelay(worker, 0,1, TimeUnit.HOURS);
	}
}
