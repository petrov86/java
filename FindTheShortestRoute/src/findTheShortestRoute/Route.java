package findTheShortestRoute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Route {

	String place1;
	String place2;
	Graph g = null;
	int nodes = 0;
	int start = 1;

	public Route(String place1, String place2) {
		// TODO Auto-generated constructor stub
		this.place1 = place1;
		this.place2 = place2;
		nodes = countVertices();
		
		// System.out.println("Size is " + nodes);
		g = new Graph(nodes);

		setVertices();
		g.printGraph();
		g.dijkstra(start - 1);
		g.printResult(start - 1, getCities());
		g.printGraph();
	}

	public String[] getCities() {
		List<String> l_cities = new ArrayList<String>();
		String[] cities;
		File file = new File("map.txt");
		FileInputStream fis = null;
		if (!file.exists()) {
			System.out.println("File Does not exists!");
		}

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		String[] tmp = new String[2];
		boolean emptyLine = false;

		try {
			while ((line = br.readLine()) != null) {

				if (!emptyLine && !line.equals("")) {
					tmp = line.split(",");
					l_cities.add(tmp[1]);
				}

				if (line.equals(""))
					emptyLine = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cities = new String[l_cities.size()];
		l_cities.toArray(cities);
		return cities;
	}

	public int countVertices() {
		int verticesCount = 0;
		File file = new File("map.txt");
		FileInputStream fis = null;
		if (!file.exists()) {
			System.out.println("File Does not exists!");
		}

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		boolean emptyLine = false;

		try {
			while ((line = br.readLine()) != null) {

				if (!emptyLine && !line.equals("")) {
					verticesCount++;
				}

				if (line.equals(""))
					emptyLine = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return verticesCount;
	}

	public void setVertices() {
		File file = new File("map.txt");
		FileInputStream fis = null;
		if (!file.exists()) {
			System.out.println("File Does not exists!");
		}

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		boolean emptyLine = false;
		String[] tmp = null;

		try {
			while ((line = br.readLine()) != null) {

				if (emptyLine) {
					tmp = line.split(",");

					// System.out.println(Integer.parseInt(tmp[0]) - 1);
					// System.out.println(Integer.parseInt(tmp[1]) - 1);
					// System.out.println(Float.parseFloat(tmp[2]));
					// System.out.println("==============");
					g.addEdge(Integer.parseInt(tmp[0]) - 1,
							Integer.parseInt(tmp[1]) - 1,
							Integer.parseInt(tmp[2]));

				}

				if (line.equals("")) {
					emptyLine = true;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
