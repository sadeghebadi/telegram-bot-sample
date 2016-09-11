package old;

import java.io.File;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.InputFile;
import com.pengrad.telegrambot.model.request.ReplyKeyboardHide;
import com.pengrad.telegrambot.response.SendResponse;

public class Telegram {

	public static String chatId = "@cafecafecafe";
	public static String JOIN_LINK = "https://telegram.me/joinchat/BaTh5DwiBFZqj3V7LSHMVw";

	public static void main(String[] args) {
		// sendPhotoMessage("ss" , "a.jpg");
		String html = "<p></p><p>برنامه 141 نرم افزار موبایل مرکز مدیریت راههای سازمان راهداری و حمل و نقل جاده ای کشور برای هموطنان عزیز می باشد ، بخشی از&nbsp;ویژگیهای برنامه :<br><b>نمایش دوربین های نظارتی بر روی نقشه:</b></p><br><p>امکان نمایش دوربینهای نظارتی بر سطح نقشه و آخرین تصاویر لحظه ای متناظر با هر دوربین نظارتی</p><br>";
		sendTextMessage(convertHtmlToString(html));
	}

	private static SendResponse sendTextMessage(String message) {
		TelegramBot bot = TelegramBotAdapter
				.build("186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8");
		SendResponse response = bot.sendMessage(chatId, message);
		return response;
	}

	private static SendResponse sendPhotoMessage(App app,
			String file_name) {
		TelegramBot bot = TelegramBotAdapter
				.build("186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8");
		SendResponse response = bot.sendPhoto(chatId, new InputFile("img",
				new File(file_name)), app.app_name+"\n"+"@cafebazaar"+"\n"+"#"+getcategoryHashtaq(app), null, null);
		return response;
	}

	private static String getcategoryHashtaq(App app) {
		return DB.getCategory(app.category_id).getTitle().replace(" ", "_");
	}

	public static boolean sendAppToChannel(App app) {
		boolean img = proccessMessage(sendPhotoMessage(app, "icons"
				+ app.img.substring(app.img.lastIndexOf("/"), app.img.length())));
		boolean txt = proccessMessage(sendTextMessage(createMessage(app)));
		if (txt && img)
			return true;
		else
			return false;
	}

	private static boolean proccessMessage(SendResponse sendTextMessage) {
		// TODO Auto-generated method stub
		return true;
	}

	private static String createMessage(App app) {
		
		String content = "\n"+app.app_name+"\n"+app.description+"\n\n\n"+"#"+getcategoryHashtaq(app)+"\n\n"+"@cafebazaar";
		if(Math.random()<0.2){
			content+="\n"+JOIN_LINK;
		}
		
		return content;
	}

	public static String convertHtmlToString(String html) {
		return html.replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", " ");
	}
}
