package by.pvt.epam.logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class ParsingLogger {

	static {
		DOMConfigurator domConfogurator = new DOMConfigurator();
		domConfogurator.doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
	}

	public static Logger logger = Logger.getRootLogger();

}
