import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class IsWorkingDayQuery implements TemporalQuery<Object> {

	private String fileName;
	BufferedReader br = null;
	FileReader fw = null;

	@Override
	public Boolean queryFrom(TemporalAccessor date) {
		int dayOfWeek = date.get(ChronoField.DAY_OF_WEEK);
		int year = date.get(ChronoField.YEAR);
		this.fileName = "Calendar_" + year + ".txt";
		File file = new File(fileName);
		
		String d = String.format("%02d", date.get(ChronoField.DAY_OF_MONTH)) + "."
				+ String.format("%02d", date.get(ChronoField.MONTH_OF_YEAR)) + "."
				+ date.get(ChronoField.YEAR);
		
		//System.out.println(d);
		if (!file.exists()) {
			System.out.println("File Does not exists!");
			return Boolean.FALSE;
		}

		if (dayOfWeek == DayOfWeek.SUNDAY.getValue()) {
//			System.out.println("Sunday");
			return Boolean.FALSE;
		} else if (dayOfWeek == DayOfWeek.SATURDAY.getValue()) {
			try {
//				System.out.println("It is Working Day - "
//						+ findDateInFile(file, ("W" + d)));
				return findDateInFile(file, ("W" + d));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
//				System.out.println("It is Working Day - "
//						+ !findDateInFile(file, d));
				return !findDateInFile(file, d);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return Boolean.FALSE;
	}

	private Boolean findDateInFile(File fin, String date) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.equals(date)) {
				br.close();
				return Boolean.TRUE;
			}
		}

		br.close();
		return Boolean.FALSE;
	}

}
