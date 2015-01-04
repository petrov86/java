package findTheShortestRoute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
	private static boolean isFirstWrite = true;
	private String fileName = "map.txt";
	BufferedWriter bw = null;
	FileWriter fw = null;
	File file = null;

	public MyFileWriter() {
		// TODO Auto-generated constructor stub
		file = new File(fileName);
	}

	public void write(String content) {

		try {

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			if (isFirstWrite) {
				fw = new FileWriter(file.getAbsoluteFile());
				isFirstWrite = false;
			} else {
				fw = new FileWriter(file.getAbsoluteFile(), true);
			}

			bw = new BufferedWriter(fw);
			bw.write(content);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
