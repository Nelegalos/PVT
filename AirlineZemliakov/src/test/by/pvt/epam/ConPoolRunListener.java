package test.by.pvt.epam;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class ConPoolRunListener extends RunListener {

	@Override
	public void testStarted(Description description) throws Exception {
		System.out.println("Start: " + description.getMethodName());
	}

	@Override
	public void testFinished(Description description) throws Exception {
		System.out.println("Finish: " + description.getMethodName() + "\n----");
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		System.out.println("The test was failed with exception: "
				+ failure.getException());
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		System.out.println("The test was ignored: "
				+ description.getMethodName() + "\n----");
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		System.out.println("Results:");
		System.out.println("Runtime: (" + result.getRunTime() + ") millis");
		System.out.println("Number of started tests: " + result.getRunCount());
		System.out.println("Number of failed tests: "
				+ result.getFailureCount());
		System.out.println("Number of ignored tests: "
				+ result.getIgnoreCount());
		System.out.println("All tests were successfully completed: "
				+ result.wasSuccessful());
	}
}