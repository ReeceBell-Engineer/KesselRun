package com.polirekt.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Scores {
	
	
	
	public Scores() {
		readScores();
	}

	private void readScores() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("res/scores.csv"));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeScores(String name, int rounds, int kills) {
		BufferedReader reader;
		StringBuffer buf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader("res/scores.csv"));
			String line = reader.readLine();

			while (line != null) {
//				System.out.println(line);
				buf.append(line).append("\n");
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		buf.append("04Dec2022,").append(name).append(",").append(rounds).append(",").append(kills).append("\n");
		
		BufferedWriter bwr;
		try {
			bwr = new BufferedWriter(new FileWriter(new File("res/scores.csv")));
			//write contents of StringBuffer to a file
			bwr.write(buf.toString());
			 
			//flush the stream
			bwr.flush();
			 
			//close the stream
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}
}
