package exception;

public class EmptyStackException extends RuntimeException {
	
	private static final long serialVersionUID = 604974004437550067L;

	public EmptyStackException() {
		this("Stack");
	}
	
	public EmptyStackException(String name) {
		super(name + " is empty");
	}
}
