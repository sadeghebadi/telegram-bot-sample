package telegram;
import java.io.File;
import java.io.IOException;

import model.AppModel;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;

public class Telegram {

	public static TelegramBot bot;
	static {

		bot = TelegramBotAdapter
				.build("186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8");
	}

	public static boolean postImageToChannel(String channelName,
			String ImagePath) {

		bot.execute(new SendPhoto(channelName, new File(ImagePath)));
		return true;

	}
	
	
	public static void post(AppModel app){
		
		String cn = "@cafecafecafe";
		postImageToChannel(cn, app.icon);
		
		postTextToChannel(cn, app.url);
	}

	public static boolean postImageToChannel(String channelName, File imageFile) {

		bot.execute(new SendPhoto(channelName, imageFile));

		return true;
	}

	public static boolean postTextToChannel(String channelName, String text) {

		bot.execute(new SendMessage(channelName, text),
				new Callback<SendMessage, SendResponse>() {
					@Override
					public void onResponse(SendMessage request,
							SendResponse response) {
					}

					@Override
					public void onFailure(SendMessage request, IOException e) {

					}
				});
		return true;
	}

	public static void main(String[] args) {
		postImageToChannel("@cafecafecafe", "1.jpg");
	}
}
