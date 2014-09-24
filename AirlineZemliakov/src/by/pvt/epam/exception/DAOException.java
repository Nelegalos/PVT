package by.pvt.epam.exception;

import java.sql.SQLException;

public class DAOException extends SQLException {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(String arg0) {
		super(arg0);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}

}
