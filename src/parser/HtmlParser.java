package parser;
import java.util.ArrayList;

import model.AppModel;


public class HtmlParser {


	
	
	public ArrayList<AppModel> parse(String html){

		
		AppModel app = new AppModel("test 1", "1.jpg", "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=en_GB");
		
		ArrayList<AppModel> apps = new ArrayList<AppModel>();
		apps.add(app);
		return apps;
	}
}
