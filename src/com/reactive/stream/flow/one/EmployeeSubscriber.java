package com.reactive.stream.flow.one;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class EmployeeSubscriber implements Subscriber<Employee> {

	private Subscription subscription;

	private int counter = 0;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println("Subscribed");
		this.subscription = subscription;
		this.subscription.request(3);
		System.out.println("onSubscribe requested 3 item");
	}

	@Override
	public void onNext(Employee item) {
		System.out.println("Processing data : " + item);
		counter++;
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("Some error occurred");
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("All processing done");
	}

	public int getCounter() {
		return counter;
	}

}
