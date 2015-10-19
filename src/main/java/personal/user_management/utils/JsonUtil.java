package personal.user_management.utils;

import com.google.gson.Gson;

import personal.user_management.data.User;
import spark.ResponseTransformer;

public class JsonUtil {
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
	
	public static User parseUser(String json) {
		User user = new Gson().fromJson(json, User.class);
		return user;
	}
}
