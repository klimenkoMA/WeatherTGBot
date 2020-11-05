import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try{
			telegramBotsApi.registerBot(new Bot());
		}catch(TelegramApiRequestException e){
			e.printStackTrace();
		}
	}

	public String getBotToken() {
		return "1316810256:AAGyqy_ONFo60esLCD4JrRyg7qOlihqYzPg";
	}

	public void onUpdateReceived(Update update) {

	}

	public String getBotUsername() {
		return null;
	}
}
