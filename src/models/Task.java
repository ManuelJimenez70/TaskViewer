package models;

public class Task {
	
	private static final String SEPARATOR = ", ";
	private static final String MEGABYTE = " Mb";
	private String name;
	private int memory;
	
	public Task(String name, String memory) {
		super();
		this.name = name;
		this.memory = Integer.parseInt(memory);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + SEPARATOR + this.memory + MEGABYTE;
	}
	
}
