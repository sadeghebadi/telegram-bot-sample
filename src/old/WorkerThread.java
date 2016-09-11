package old;
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

	private boolean sendMessage(App app) {
		return Telegram.sendAppToChannel(app);
	}
}