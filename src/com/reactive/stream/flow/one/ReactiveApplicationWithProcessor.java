package com.reactive.stream.flow.one;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveApplicationWithProcessor {

	public static void main(String[] args) throws InterruptedException {

		// creating a end publisher
		SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

		// create processor
		MyProcessor processor = new MyProcessor(emp -> {
			return new Freelance(emp.getId(), emp.getName(), emp.getId() + 100);
		});

		// creating a end subscriber
		FreelancerSubscriber subscriber = new FreelancerSubscriber();

		// create a chain of publisher processor subscriber
		publisher.subscribe(processor);

		processor.subscribe(subscriber);

		List<Employee> emps = EmpHelper.getEmps();

		// Publish items
		System.out.println("Publishing Items to Subscriber");
		emps.stream().forEach(i -> publisher.submit(i));

		// Logic to wait for messages processing to finish
		while (emps.size() != subscriber.getCounter()) {
			Thread.sleep(10);
		}

		// Closing publishers
		publisher.close();
		processor.close();

		System.out.println("Exiting the app");

	}

}
