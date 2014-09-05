package by.pvt.epam.exception;

public class LeafTechnicalException extends Exception {

	private static final long serialVersionUID = -5262593137433944606L;

	public LeafTechnicalException() {
		super();
	}

	public LeafTechnicalException(String message, Throwable exception) {
		super(message, exception);
	}

	public LeafTechnicalException(String message) {
		super(message);
	}

	public LeafTechnicalException(Throwable exception) {
		super(exception);
	}

}
