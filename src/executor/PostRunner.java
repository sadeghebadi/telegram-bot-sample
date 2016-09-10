package executor;

import java.util.ArrayList;

import model.AppModel;
import parser.HtmlParser;
import telegram.Telegram;

public class PostRunner {

	public static void main(String[] args) {
		
		
		// read an app to post;
		AppModel app = new AppModel("test 1", "1.jpg", "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=en_GB");

		Telegram.post(app);
	}
	
}
