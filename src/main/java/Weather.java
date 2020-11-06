import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {


	//c10f0bab5383776ad8edd21ee1862c40
	//b0fc0f141b45ef5975c88b61a00d044a

	public static String getWeather(String message, Model model) throws IOException {

		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=b0fc0f141b45ef5975c88b61a00d044a");

		Scanner scanner = new Scanner((InputStream) url.getContent());
		String result = "";
		while(scanner.hasNext()) {
			result += scanner.nextLine();
		}

		JSONObject jsonObject = new JSONObject(result);
		model.setName(jsonObject.getString("name"));

		JSONObject main = jsonObject.getJSONObject("main");
		model.setHumidity(main.getDouble("humidity"));
		model.setTemp(main.getDouble("temp"));

		JSONArray jsonArray = jsonObject.getJSONArray("weather");
		for(int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			model.setIcon(obj.getString("icon"));
			model.setMain(obj.getString("main"));
		}
		return "Город: " + model.getName() + "\n" +
				"Температура: " + model.getTemp() + "C\n" +
				"Влажность: " + model.getHumidity() + "%\n" +
				"Осадки: " + model.getMain() + " " + "\n" +
				"https://openweathermap.org/img/wn/" + model.getIcon() + "@2x.png";
	}
}
