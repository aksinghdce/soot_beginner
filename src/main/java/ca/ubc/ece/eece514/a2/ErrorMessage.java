package ca.ubc.ece.eece514.a2;

public enum ErrorMessage {
	NEGATIVE_SHIFT("shifting by a negative value"),
	SHIFT_TOO_LARGE("shifting by 32 bits or more"),
	UNREAD_FIELD("this field is never read"),
    ;
    
	private ErrorMessage(String message) {
		this.errorMessage = message;
	}

    public String getErrorMessage() {
        return errorMessage;
    }
    
	private String errorMessage;    
}
