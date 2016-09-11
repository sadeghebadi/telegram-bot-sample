package old;

import java.io.File;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.InputFile;
import com.pengrad.telegrambot.model.request.ReplyKeyboardHide;
import com.pengrad.telegrambot.response.SendResponse;

public class Telegram {

	public static long chatId = -1001036894190l;

	public static void main(String[] args) {
		// sendPhotoMessage("ss" , "a.jpg");
		String html = "<p></p><p>برنامه 141 نرم افزار موبایل مرکز مدیریت راههای سازمان راهداری و حمل و نقل جاده ای کشور برای هموطنان عزیز می باشد ، بخشی از&nbsp;ویژگیهای برنامه :<br><b>نمایش دوربین های نظارتی بر روی نقشه:</b></p><br><p>امکان نمایش دوربینهای نظارتی بر سطح نقشه و آخرین تصاویر لحظه ای متناظر با هر دوربین نظارتی</p><br>";
		sendTextMessage(convertHtmlToString(html));
	}

	private static SendResponse sendTextMessage(String message) {
		TelegramBot bot = TelegramBotAdapter
				.build("186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8");
		SendResponse response = bot.sendMessage("@cafecafecafe", message);
		return response;
	}

	private static SendResponse sendPhotoMessage(String caption,
			String file_name) {
		TelegramBot bot = TelegramBotAdapter
				.build("186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8");
		SendResponse response = bot.sendPhoto(chatId, new InputFile("img",
				new File(file_name)), caption, null, new ReplyKeyboardHide());
		return response;
	}

	public static boolean sendAppToChannel(App app) {
		boolean txt = proccessMessage(sendTextMessage(createMessage(app)));
		boolean img = proccessMessage(sendPhotoMessage(app.app_name, "icons/"
				+ app.img.substring(app.img.lastIndexOf("/"), app.img.length())));
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
		return app.app_name;
	}

	public static String convertHtmlToString(String html) {
		return html.replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", " ");
	}
}
