package by.pvt.resource.pool;

import static by.pvt.resource.main.Runner.logger;

public class Operator {
	private int operatorId;

	public Operator(int id) {
		super();
		this.operatorId = id;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int id) {
		this.operatorId = id;
	}

	public void talking() {
		try {
			Thread.sleep(new java.util.Random().nextInt(500));
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);

		}
	}
}