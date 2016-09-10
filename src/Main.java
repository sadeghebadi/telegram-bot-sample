import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class Main {

	
	
	public static final String TOKENMYPROJECT = "186102655:AAFLUyVcbcqOs9KiY1GsFNKWMTzAngchuP8";
	public static final String USERNAMEMYPROJECT = "@Cafecheckbot";

	public static void main(String[] args) {

		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			ChannelHandlers h = new ChannelHandlers();
			System.out.println(h.getBotToken());
			telegramBotsApi.registerBot(new ChannelHandlers());

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}

class ChannelHandlers extends TelegramLongPollingBot {

	
	@Override
	public void onUpdateReceived(Update update) {
		// check if the update has a message
		if (update.hasMessage()) {
			Message message = update.getMessage();

			// check if the message has text. it could also contain for example
			// a location ( message.hasLocation() )
			if (message.hasText()) {

				// create a object that contains the information to send back
				// the message
				SendMessage sendMessageRequest = new SendMessage();
				sendMessageRequest.setChatId(message.getChatId().toString()); // who
																				// should
																				// get
																				// the
																				// message?
																				// the
																				// sender
																				// from
																				// which
																				// we
																				// got
																				// the
																				// message...
				ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
				ArrayList<KeyboardRow > keyrows= new ArrayList<>();
				KeyboardRow row1 = new KeyboardRow();
				row1.add("1");
				row1.add("2");
				row1.add("3");
				row1.add("4");
				row1.add("4");
				row1.add("4");
				row1.add("4");
				row1.add("4");
				row1.add("4");
				
				keyrows.add(row1);
				rkm.setKeyboard(keyrows);
//				sendMessageRequest.setReplayMarkup(rkm);
				sendMessageRequest.setReplyMarkup(rkm);
				sendMessageRequest.setText("aid: " + message.getText());
				try {
					sendMessage(sendMessageRequest); // at the end, so some
														// magic and send the
														// message ;)
				} catch (TelegramApiException e) {
					// do some error handling
				}// end catch()
			}// end if()
		}// end if()

	}// end onUpdateReceived()

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub

		return Main.USERNAMEMYPROJECT;
	}

	
	@Override
	public String getBotToken() {

		return Main.TOKENMYPROJECT;
	}

}