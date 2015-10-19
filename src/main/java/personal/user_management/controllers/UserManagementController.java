package personal.user_management.controllers;

import static personal.user_management.utils.JsonUtil.json;
import static personal.user_management.utils.JsonUtil.parseUser;
import static personal.user_management.utils.JsonUtil.toJson;
import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.JsonSyntaxException;

import personal.user_management.error.ResponseError;
import personal.user_management.error.UserManagementException;
import personal.user_management.services.UserManagementService;

public class UserManagementController {
	public UserManagementController(final UserManagementService userManagementService) {
		//Routes
		get("/users", (req, res) -> userManagementService.getAllUsers(), json());
		post("/createUser", (req, res) -> {
			return userManagementService.createUser(parseUser(req.body()));
		}, json());
		put("/updateUser", (req, res) -> {
			return userManagementService.updateUser(parseUser(req.body()));
		}, json());
		
		//Filters
		after((req, res) -> {
			res.type("application/json");
		});
		
		//Exception Handlers
		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});

		exception(UserManagementException.class, (e, req, res) -> {
			res.status(500);
			res.body(toJson(new ResponseError(e)));
		});
		
		exception(JsonSyntaxException.class, (e, req, res) -> {
			res.status(500);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
