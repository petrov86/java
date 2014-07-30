package calucalotor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	private static boolean isFirstWrite = true;

	protected WriteFile() {

	}

	public static void write(String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {

			File file = new File("C:\\log.txt");

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
