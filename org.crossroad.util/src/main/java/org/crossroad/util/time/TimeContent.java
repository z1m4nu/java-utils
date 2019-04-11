package org.crossroad.util.time;

public class TimeContent {
	private long time = 0L;
	private long member = 0L;

	public TimeContent(long time) {
		this.time = time;
		this.member = 1;
	}

	public long getTime() {
		return time;
	}

	public void addTime(long time) {
		this.time += time;
		this.member++;
	}
	
	public void update(long time, long member)
	{
		this.time += time;
		this.member += member;
	}

	public long getMember() {
		return member;
	}
}