package personal.user_management.error;

public class UserManagementException extends RuntimeException {
	public UserManagementException(String string) {
		super(string);
	}

	public UserManagementException() {
		super();
	}

	private static final long serialVersionUID = 6115350193312895564L;

}
