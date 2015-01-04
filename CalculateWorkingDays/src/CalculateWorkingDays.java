import java.io.File;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CalculateWorkingDays {
	static LocalDate lc = LocalDate.now();
	static DateTimeFormatter dateFormatter = DateTimeFormatter
			.ofPattern("dd.MM.yyyy");
	static DateTimeFormatter monthYearFormatter = DateTimeFormatter
			.ofPattern("MM.yyyy");
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("OPTIONS:");
		System.out
				.println("1 - To Enter Holidays and Working Saturdays During Year");
		System.out
				.println("2 - Check wheather a Date is a holiday or a working day");
		System.out.println("3 - Print Working Days during Month");
		System.out.println("4 - Print Working Days in a Period");
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();

		switch (choice) {
		case 1:
			enterHolidaysAndWorkingSaturdaysDuringYear();
			break;
		case 2:
			checkDate();
			break;
		case 3:
			printWorkingDaysDuringMonth();
			break;
		case 4:
			printWorkingDaysInAPeriod();
			break;
		default:
			break;
		}
		s.close();
	}

	private static void printWorkingDaysInAPeriod() {

		Scanner s = new Scanner(System.in);
		String startDate = null;
		String endDate = null;
		while (startDate == null || endDate == null) {

			if (startDate == null) {
				System.out.println("Enter Start Date of a period (dd.mm.yyyy)");
				startDate = s.nextLine();
				if (startDate.equals("")) {
					break;
				}
				try {
					lc = LocalDate.parse(startDate, dateFormatter);
				} catch (java.time.format.DateTimeParseException e) {
					// TODO: handle exception
					System.out.println("Incorrect Date");
					continue;
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}

			if (endDate == null) {
				System.out.println("Enter End Date of a period (dd.mm.yyyy)");
				endDate = s.nextLine();
				if (endDate.equals("")) {
					break;
				}
				try {
					LocalDate.parse(endDate, dateFormatter);
				} catch (java.time.format.DateTimeParseException e) {
					// TODO: handle exception
					System.out.println("Incorrect Date");
					continue;
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}
		}

		long period = ChronoUnit.DAYS.between(lc,
				LocalDate.parse(endDate, dateFormatter));
		System.out.println(period);

		for (int i = 1; i <= period; i++) {

			if ((boolean) lc.query(new IsWorkingDayQuery())) {
				System.out.println(lc);
			}
			lc = lc.plusDays(1);
		}

		s.close();
	}

	private static void printWorkingDaysDuringMonth() {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out
					.println("Enter month and year to view all working days - (mm.yyyy)");

			String str = s.nextLine();
			String month = "01." + str;
			if (str.equals("")) {
				break;
			}

			try {

				lc = LocalDate.parse(month, dateFormatter);
				for (int i = 1; i <= lc.lengthOfMonth(); i++) {
					if ((boolean) lc.query(new IsWorkingDayQuery())) {
						System.out.println(lc);
					}
					lc = lc.plusDays(1);
				}
				System.out.println();

			} catch (java.time.format.DateTimeParseException e) {
				// TODO: handle exception
				System.out.println("Incorrect IN");
				continue;
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}

		}
		s.close();

	}

	public static void checkDate() {

		String date;

		while (true) {
			System.out.println("Enter a Date (dd.MM.yyyy)");
			date = s.nextLine();

			if (date.equals("")) {

				break;
			}
			try {
				lc = LocalDate.parse(date, dateFormatter);
				String fileName = "Calendar_" + lc.getYear() + ".txt";
				File file = new File(fileName);
				if (!file.exists()) {
					System.out.println("File for year " + lc.getYear()
							+ " does not exists!");
					continue;
				}

				System.out.println("It is Working Day - "
						+ lc.query(new IsWorkingDayQuery()));
			} catch (java.time.format.DateTimeParseException e) {
				System.out.println("Entered Date is not correct");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void enterHolidaysAndWorkingSaturdaysDuringYear() {
		MyFileWriter fw = null;
		Year year = null;
		String holidayDate = "";
		String workingSaturday = "";
		Scanner s = new Scanner(System.in);
		boolean validYear = false;
		boolean wantToAddHolidays = true;
		boolean wantToAddWorkingSaturdays = true;

		while (!validYear) {
			System.out.println("Enter an year: ");
			String str = s.nextLine();

			try {
				year = Year.of(Integer.parseInt(str));
			} catch (NumberFormatException e) {
				System.out.println("Year is not valid!");
				continue;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			if (year.getValue() < 1970) {
				System.out.println("Years under 1970 are not allowed!");
			} else if (year.getValue() > 2050) {
				System.out.println("Years beyond 2050 are not allowed");
			} else {
				validYear = true;
				fw = new MyFileWriter(year.toString());
			}

		}

		if (fw != null) {
			System.out.println("Enter Holidays During " + year
					+ " (dd.MM.yyyy)");
			while (wantToAddHolidays) {
				holidayDate = s.nextLine();
				if (holidayDate.equals("")) {
					wantToAddHolidays = false;
					break;
				}
				try {
					lc = LocalDate.parse(holidayDate, dateFormatter);
					if (lc.getYear() != year.getValue()) {
						System.out.println("Wrong Year!");
						continue;
					} else if (lc.getDayOfWeek() == DayOfWeek.SATURDAY
							|| lc.getDayOfWeek() == DayOfWeek.SUNDAY) {
						System.out.println("The Entered Day is "
								+ lc.getDayOfWeek());
					} else {
						fw.write(holidayDate + "\n");
						System.out.println("DATE ADDED");
					}
				} catch (DateTimeParseException dateTimeParseException) {
					System.out.println("The Date is Not Valid!");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			System.out.println("Enter Working Saturdays During " + year
					+ " (dd.MM.yyyy)");
			while (wantToAddWorkingSaturdays) {
				workingSaturday = s.nextLine();
				if (workingSaturday.equals("")) {
					wantToAddWorkingSaturdays = false;
					break;
				}
				try {
					lc = LocalDate.parse(workingSaturday, dateFormatter);
					if (lc.getYear() != year.getValue()) {
						System.out.println("Wrong Year!");
						continue;
					} else if (lc.getDayOfWeek() != DayOfWeek.SATURDAY) {
						System.out.println("The Entered Day is Not Saturday!");
						continue;
					} else {
						fw.write("W" + workingSaturday + "\n");
						System.out.println("DATE ADDED");
					}
				} catch (DateTimeParseException dateTimeParseException) {
					System.out.println("The Date is Not Valid!");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			s.close();

		}
	}
}
