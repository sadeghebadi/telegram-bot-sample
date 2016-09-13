package old;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkerThread implements Runnable {

	private String command;

	public WorkerThread(String s) {
		this.command = s;
	}

	@Override
	public void run() {
		processCommand();
	}

	private void processCommand() {
		try {
			if(!isInTime()){
				System.err.println("not valid time");
				return;
			}
			new CafeCrowler().saveApps();
			System.out.println("crawle completed.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		App app = DB.getNewApp();
		System.out.println("new App ....");
		if (app != null) {
			System.out.println("****************"+app.app_name);
			if(sendMessage(app))
			DB.updateApp(app.url, 0);
		}

	}

	private boolean isInTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		System.out.println(hour+":"+minutes);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(cal.getTime());

		if ((hour >= 10 && minutes >= 30 )&&( hour <22 && minutes <= 10) ){
			return true;
		}
		return false;
	}

	private boolean sendMessage(App app) {
		return Telegram.sendAppToChannel(app);
	}
}