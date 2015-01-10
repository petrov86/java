package findTheShortestRoute;

import java.util.Scanner;

public class FindTheShortestRoute {
	public static void main(String[] args) {

		int choice = 0;

		while (true) {
			System.out.println("OPTIONS:");
			System.out.println("1 - Enter places and distances");
			System.out.println("2 - Calculate route between two places");
			System.out.println("3 - Calculate route");
			Scanner s = new Scanner(System.in);

			try {
				choice = s.nextInt();
				break;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Incorrect choice!");
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
			s.close();
		}
		


		switch (choice) {
		case 1:
			fillMap();
			break;
		case 2:
			calculateRoute();
			break;
		case 3:

			break;
		case 4:

			break;
		default:
			break;
		}

	}

	private static void calculateRoute() {
		// TODO Auto-generated method stub
		Route r = new Route("", "");

	}

	private static void fillMap() {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String place = null;
		String distance = null;
		MyFileWriter writer = new MyFileWriter();

		while (true) {
			System.out.println("Enter places - Example -> 1,Sofia");
			place = s.nextLine();
			if (place.equals("")) {
				writer.write("\n");
				break;
			}

			String[] temp = place.split(",");

			if (temp.length != 2) {
				System.out.println("Invalid place!");
				continue;
			}

			try {
				Integer.parseInt(temp[0]);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid place!");
				continue;
			}
			writer.write(place + "\n");

		}
		while (true) {
			System.out
					.println("Enter disatnces (Place1,Place2,Distance in km.) - Example -> 1,2,54.2");
			distance = s.nextLine();
			if (distance.equals("")) {
				s.close();
				return;
			}

			String[] temp = distance.split(",");

			if (temp.length != 3) {
				System.out.println("Invalid distance!");
				continue;
			}

			try {
				Integer.parseInt(temp[0]);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid distance!");
				continue;
			}
			writer.write(distance + "\n");

		}

	}
}
