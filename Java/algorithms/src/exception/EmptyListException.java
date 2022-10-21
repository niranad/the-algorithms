package exception;

public class EmptyListException extends RuntimeException {

	private static final long serialVersionUID = -7501346647389223477L;

	public EmptyListException() {
		this("List");
	}
	
	public EmptyListException(String name) {
		super(name + " is empty");
	}
}
