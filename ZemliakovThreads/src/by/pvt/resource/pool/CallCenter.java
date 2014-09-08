package by.pvt.resource.pool;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;

import by.pvt.resource.exception.OperatorTechnicalException;

public class CallCenter<T> {
	private final static int NUMBER_OF_OPERATORS = 5;
	private final Semaphore semaphore = new Semaphore(NUMBER_OF_OPERATORS, true);

	private final Queue<T> operators = new LinkedList<T>();

	public CallCenter(Queue<T> source) {
		operators.addAll(source);
	}

	public T getOperator(long maxWaitMilSec) throws OperatorTechnicalException {
		try {
			if (semaphore.tryAcquire(maxWaitMilSec, TimeUnit.MILLISECONDS)) {
				T operator = operators.poll();
				return operator;
			}
		} catch (InterruptedException e) {
			throw new OperatorTechnicalException("InterruptedException", e);
		}
		return null;
	}

	public void releaseOperator(T operator) {
		operators.add(operator);
		semaphore.release();
	}
}