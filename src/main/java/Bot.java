import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new Bot());


		} catch(TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}

	public void sendButtons(SendMessage sendMessage) {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);

		replyKeyboardMarkup.setSelective(true);

		replyKeyboardMarkup.setResizeKeyboard(true);

		replyKeyboardMarkup.setOneTimeKeyboard(false);


		List<KeyboardRow> keyboardRowList = new ArrayList<>();
		KeyboardRow keyboardFirstRow = new KeyboardRow();
		keyboardFirstRow.add(new KeyboardButton("/help"));
		keyboardFirstRow.add(new KeyboardButton("/settings"));

		keyboardRowList.add(keyboardFirstRow);

		replyKeyboardMarkup.setKeyboard(keyboardRowList);

	}

	public void sendMsg(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(false);

		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(text);
		try {
			sendButtons(sendMessage);
			execute(sendMessage);
		} catch(TelegramApiException e) {
			e.printStackTrace();
		}

	}

	public String getBotToken() {
		return "1316810256:AAGyqy_ONFo60esLCD4JrRyg7qOlihqYzPg";
	}

	public void onUpdateReceived(Update update) {

		Model model = new Model();

		Message message = update.getMessage();
		if(message != null && message.hasText()) {
			switch(message.getText()) {
				case "/help":
					sendMsg(message, "Чем могу помочь?");
					break;
				case "/settings":
					sendMsg(message, "Что будем настраивать?");
					break;
				default:
					try {
						sendMsg(message, Weather.getWeather(message.getText(), model));
					} catch(IOException e) {
						sendMsg(message, "Такой город не найден!");
					}

			}
		}

	}

	public String getBotUsername() {
		return "WeatherMasterBot";
	}
}
