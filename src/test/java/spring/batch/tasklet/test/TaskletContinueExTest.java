package spring.batch.tasklet.test;

import junit.framework.TestCase;
import spring.batch.tasklet.ex.TaskletContinueEx;

public class TaskletContinueExTest extends TestCase {
	public TaskletContinueExTest(String testName) {
		super(testName);
	}

	public void testTaskletContinueEx() {
		TaskletContinueEx ex = new TaskletContinueEx();
		String[] args = new String[] {};
		ex.startProcess(args);
	}
}
