package personal.user_management.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import personal.user_management.data.User;
import personal.user_management.error.UserManagementException;

public class UserManagementService {
	
	public UserManagementService() {
		users = new ArrayList<User>();
		User user  = new User("John", null, "Smith", 27, "M", "5554804801", null);
		user.setId(UUID.randomUUID().toString());
		users.add(user);
	}

	private List<User> users;
	
	public List<User> getAllUsers() {
		return users;
	}
	
	public User createUser(User user) {
		validateUser(user);
		if(users.contains(user))
			//TODO: Log exception here
			throw new UserManagementException("The posted user already exists");
		user.setId(UUID.randomUUID().toString());
		users.add(user);
		return user;
	}
	
	public User updateUser(User user) {
		validateUserForUpdate(user);
		User foundUser = getUserById(user.getId());
		if(foundUser == null) {
			//TODO: Log exception here
			throw new UserManagementException("User not found");
		}
		foundUser.copyFrom(user);
		return foundUser;
	}
	
	private void validateUserForUpdate(User user) {
		if(StringUtils.isBlank(user.getId()))
			//TODO: Log exception here
			throw new UserManagementException("id cannot be blank for this operation");
		validateUser(user);
	}
	
	private void validateUser(User user) {
		validateName(user.getFirstName(), "firstName");
		if(StringUtils.isNotBlank(user.getMiddleName()))
			validateOnlyContainsAlphabets(user.getMiddleName(), "middleName");
		validateName(user.getLastName(), "lastName");
		validateAge(user.getAge());
		validateGender(user.getGender());
		validatePhone(user.getPhone());
	}

	private void validatePhone(String phone) {
		if(!phone.matches("\\d{10}"))
			//TODO: Log exception here
			throw new UserManagementException("phone must contain only numbers and should be of length 10");
	}

	private void validateGender(String gender) {
		String[] allowedGenderValues = {"M", "F"};
		if(!Arrays.asList(allowedGenderValues).contains(gender))
			//TODO: Log exception here
			throw new UserManagementException("gender has to be M or F");
	}

	private void validateAge(int age) {
		if(age <= 0)
			//TODO: Log exception here
			throw new UserManagementException("age has to be a non zero positive integer");
	}

	private void validateName(String name, String propertyName) {
		if(StringUtils.isEmpty(name))
			//TODO: Log exception here
			throw new UserManagementException(propertyName + " cannot be empty");
		validateOnlyContainsAlphabets(name, propertyName);
	}

	private void validateOnlyContainsAlphabets(String name, String propertyName) {
		if(!name.matches("[A-Za-z]+"))
			//TODO: Log exception here
			throw new UserManagementException(propertyName + " cannot contain numbers or special characters");
	}

	private User getUserById(String id) {
		for(User user: users) {
			if(id.equals(user.getId()))
				return user;
		}
		return null;
	}
}
