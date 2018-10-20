package com.reactive.stream.flow.one;

public class Freelance extends Employee {

	private int fid;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public Freelance(int id, String name, int fid) {
		super(id, name);
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "[id=" + super.getId() + ",name=" + super.getName() + ",fid=" + fid + "]";
	}

}
