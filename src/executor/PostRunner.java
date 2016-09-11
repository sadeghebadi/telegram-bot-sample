package executor;

import java.util.ArrayList;

import model.AppModel;
import parser.HtmlParser;
import telegram.Telegram;
import web.DataProvider;

public class PostRunner {

	public static void main(String[] args) {
		new DataProvider().getHtml("https://cafebazaar.ir/app/ir.carbon3d.return/?l=fa");
//		new DataProvider().getHtml("https://fax.demo.com/");
		
		//https://nigel.stg.taluslabs.com/subscription/api/v1/accounts/

		//AppModel app = new AppModel("test 1", "1.jpg", "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=en_GB");

		//Telegram.post(app);
	}
	
}
