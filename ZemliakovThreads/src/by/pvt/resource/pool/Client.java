package by.pvt.resource.pool;

import by.pvt.resource.exception.OperatorTechnicalException;
import static by.pvt.resource.main.Runner.logger;

public class Client extends Thread {

	private boolean talking = false;
	private CallCenter<Operator> callCenter;

	public Client(CallCenter<Operator> callCenter) {
		this.callCenter = callCenter;
	}

	public void run() {
		Operator operator = null;
		try {
			operator = callCenter.getOperator(200);
			
			while (operator == null) {
				System.out.println("Client #" + this.getId()
						+ " had no more time to wait and hung up");
				Thread.sleep(90);
				operator = callCenter.getOperator(200);
			}
			talking = true;
			System.out.println("Client #" + this.getId()
					+ " called operator #" + operator.getOperatorId());
			operator.talking();
			
		} catch (OperatorTechnicalException e1) {
			logger.error("OperatorTechnicalException", e1);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} finally {
			if (operator != null) {
				talking = false;
				System.out.println("Client #" + this.getId()
						+ " released operator #" + operator.getOperatorId());
				callCenter.releaseOperator(operator);
			}
		}
	}

	public boolean isTalking() {
		return talking;
	}
}
