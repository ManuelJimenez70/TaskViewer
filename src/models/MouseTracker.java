package models;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class MouseTracker {

	private static final int IDENT_PARAMETER = 4;
	private static final String POSITION_LIST_TAG = "positionList";
	private static final String X = "x";
	private static final String Y = "y";
	private static final String JSON_POS_PATH = "src/resources/data/positionList.txt";

	private int x, y;
	
	public MouseTracker() {
		this.x = 0;
		this.y = 0;
	}

	private void writeJson() throws IOException {
		String text = String.join(" ", Files.readAllLines(Paths.get(JSON_POS_PATH)));
		JSONObject json = new JSONObject(text);
		JSONArray posList = json.getJSONArray(POSITION_LIST_TAG);
		JSONObject jsonPos = new JSONObject();
		jsonPos.put(X, this.x);
		jsonPos.put(Y, this.y);
		posList.put(jsonPos);
		json.put(POSITION_LIST_TAG, posList);
		FileWriter file = new FileWriter(JSON_POS_PATH);
		file.write(json.toString(IDENT_PARAMETER));
		file.flush();
	}
	
	public void writeNewPosition(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			writeJson();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
