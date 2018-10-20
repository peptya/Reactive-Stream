package com.reactive.stream.flow.one;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MyReactiveApplication {

	public static void main(String[] args) throws InterruptedException {

		// creating publisher
		SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

		// registering the subscriber
		EmployeeSubscriber subscriber = new EmployeeSubscriber();
		publisher.subscribe(subscriber);

		// generating the data to publish
		List<Employee> empList = EmpHelper.getEmps();

		// publishing the items
		System.out.println("Publishing the items to subscriber");
		empList.stream().forEach(emp -> publisher.submit(emp));

		// logic to wait till processing of all messages are over
		while (empList.size() != subscriber.getCounter()) {
			Thread.sleep(10);
		}
		
		// close the Publisher
		publisher.close();

		System.out.println("Exiting the app");

	}

}
