package by.pvt.resource.main;

import java.util.LinkedList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import by.pvt.resource.pool.CallCenter;
import by.pvt.resource.pool.Client;
import by.pvt.resource.pool.Operator;

public class Runner {
	static {
		DOMConfigurator domConfogurator = new DOMConfigurator();
		domConfogurator.doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
	}
	public static Logger logger = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		@SuppressWarnings("serial")
		LinkedList<Operator> operatorsList = new LinkedList<Operator>() {
			{
				this.add(new Operator(1));
				this.add(new Operator(2));
				this.add(new Operator(3));
				this.add(new Operator(4));
				this.add(new Operator(5));
			}
		};

		CallCenter<Operator> callCenter = new CallCenter<Operator>(operatorsList);
		for (int i = 1; i <= 20; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {				
				logger.error(e);
			}
			new Client(callCenter).start();
		}
	}
}