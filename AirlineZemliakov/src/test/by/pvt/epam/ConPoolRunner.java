package test.by.pvt.epam;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runner.notification.RunNotifier;

public class ConPoolRunner extends BlockJUnit4ClassRunner {

	private ConPoolRunListener runListener;

	public ConPoolRunner(Class<ConPoolRunListener> clazz)
			throws InitializationError {
		super(clazz);
		runListener = new ConPoolRunListener();
	}

	public void run(RunNotifier notifier) {
		notifier.addListener(runListener);
		super.run(notifier);
	}
}
