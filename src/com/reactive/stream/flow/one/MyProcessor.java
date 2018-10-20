package com.reactive.stream.flow.one;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class MyProcessor extends SubmissionPublisher<Freelance> implements Processor<Employee, Freelance> {

	private Subscription subscription;
	private Function<Employee, Freelance> function;

	public MyProcessor(Function<Employee, Freelance> function) {
		super();
		this.function = function;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(Employee item) {
		submit(function.apply(item));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("Done");
	}

}
