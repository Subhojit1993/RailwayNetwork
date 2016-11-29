package com.Railway.Plotting;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GetStationList {

	public String[] NEStationList() {
		String fileName = "NorthEast.txt";
		String stName[] = new String[41];
		ArrayList<String> stName1 = new ArrayList<>();

		String line = null;
		stName[0] = "";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int i = 1;

			while ((line = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				String[] tokens = line.split("\\s+");
				float lat = Float.parseFloat(tokens[0]);
				float lon = Float.parseFloat(tokens[1]);
				String station = tokens[2];
				stName1.add(station);
				stName[i] = station;

				i++;
				// System.out.println(line);
				// System.out.println(var_1+" "+var_2+" "+var_3);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		Arrays.sort(stName);
		stName[0] = "---Select---";

		// Collections.sort((java.util.List<T>) stName1);
		return stName;

	}

	public String[] NStationList() {
		String fileName = "North.txt";
		String stName[] = new String[37];
		ArrayList<String> stName1 = new ArrayList<>();

		String line = null;
		stName[0] = "";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int i = 1;

			while ((line = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				String[] tokens = line.split("\\s+");
				float lat = Float.parseFloat(tokens[0]);
				float lon = Float.parseFloat(tokens[1]);
				String station = tokens[2];
				stName1.add(station);
				stName[i] = station;

				i++;
				// System.out.println(line);
				// System.out.println(var_1+" "+var_2+" "+var_3);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		Arrays.sort(stName);
		stName[0] = "---Select---";
		// Collections.sort((java.util.List<T>) stName1);
		return stName;

	}
	
	public String[] EStationList() {
		String fileName = "EasternZone.txt";
		String stName[] = new String[45];
		ArrayList<String> stName1 = new ArrayList<>();

		String line1 = null;
		stName[0] = "";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int i = 1;

			while ((line1 = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				String[] tokens = line1.split("\\s+");
				float lat = Float.parseFloat(tokens[0]);
				float lon = Float.parseFloat(tokens[1]);
				String station = tokens[2];
				stName1.add(station);
				stName[i] = station;

				i++;
				// System.out.println(line);
				// System.out.println(var_1+" "+var_2+" "+var_3);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		Arrays.sort(stName);
		stName[0] = "---Select---";
		// Collections.sort((java.util.List<T>) stName1);
		return stName;

	}
}
