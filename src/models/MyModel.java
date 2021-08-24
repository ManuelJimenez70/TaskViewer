package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class MyModel extends MyThreadClass{

	private static final String SEPARATOR = " ";
	private static final String SYSTEM_TASKS = System.getenv("windir") + "\\system32\\" + "tasklist.exe";
	private MouseTracker mouseTracker;
	private ArrayList<Task> taskList;

	public MyModel() {
		this.mouseTracker = new MouseTracker();
		this.taskList = new ArrayList<Task>();
		this.setSleepTime(TimeUnit.SECONDS.toMillis(3));
	}

	public void writeNewPosition(int x, int y) {
		this.mouseTracker.writeNewPosition(x, y);
	}

	private void loadTasks() {
		this.taskList.clear();
		try {
			String line;
			Process p = Runtime.getRuntime().exec(SYSTEM_TASKS);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			skipLines(input);
		    while ((line = input.readLine()) != null) {
		    	addToTaskList(line);
		    }
			input.close();
			sortList();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	private void skipLines(BufferedReader input) throws IOException {
		String line;
		line = input.readLine();
		line = input.readLine();
		line = input.readLine();
	}

	private void sortList() {
		this.taskList.sort(new Comparator<Task>() {
			public int compare(Task o1, Task o2) {
				return o2.getMemory() - o1.getMemory();
			};
		});
	}

	private void addToTaskList(String line) {
		String[] taskSlpit = line.split(SEPARATOR);
		String[] memorySplit = (taskSlpit[taskSlpit.length-2].replace('.', ' ')).split(SEPARATOR);
		taskList.add(new Task(taskSlpit[0], memorySplit[0]));
	}

	private void printTansks() {
		for (Task task : taskList) {
			System.out.println(task);
		}
	}

	@Override
	public void executeTask() {
		loadTasks();
	}

	public ArrayList<Task> getTasks() {
		return this.taskList;
	}

}
