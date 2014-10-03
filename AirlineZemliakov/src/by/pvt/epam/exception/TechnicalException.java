package by.pvt.epam.exception;

import java.sql.SQLException;

public class TechnicalException extends SQLException {

	private static final long serialVersionUID = 1L;

	public TechnicalException() {
		super();
	}

	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TechnicalException(String arg0) {
		super(arg0);
	}

	public TechnicalException(Throwable arg0) {
		super(arg0);
	}

}
