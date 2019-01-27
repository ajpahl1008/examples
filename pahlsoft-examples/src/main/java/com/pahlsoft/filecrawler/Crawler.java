package com.pahlsoft.filecrawler;

import java.util.*;
import java.io.*;

public class Crawler {
	
	public static long totalDirectories = 0;
	public static long totalFiles = 0;
	
	public static void main(String[] args) throws Exception {
		try {

			@SuppressWarnings("unused")
			String[] excludeDIR = { "C:\\Documents and Settings" };

			long StartTime = System.currentTimeMillis();

			String baseDir = "C:\\Documents and Settings"; // Change this to / for actual UNIX tests.

			Scanner in = new Scanner(baseDir);

			File root = new File(in.nextLine());

			if (root.isDirectory()) {
				totalFiles = totalFiles + listRecursively(root);
			} 
			
			System.out.println("Total Number of Files: " + totalFiles);
			System.out.println("Total Number of Directories: " + totalDirectories);
			
			// Determine How long we ran.
			long EndTime = System.currentTimeMillis();
			long duration = (EndTime - StartTime) / 1000;

			// Convert ms into somthing meaningful
			if (duration >= 60) {
				float minutes = duration / 60;
				System.out.println("Processing Time: " + minutes + " minutes ");
			} else if (duration >= 3600) {
				float hours = duration / 3600;
				System.out.println("Processing Time: " + hours + " hours ");
			} else {
				System.out
						.println("Processing Time: " + duration + " seconds ");
			}
		} catch (Exception err) {
			// Memory Parameters
			Runtime runtime = Runtime.getRuntime();
			long freeMem = runtime.freeMemory() / 1024;
			long totalMem = runtime.totalMemory() / 1024;
			freeMem = runtime.freeMemory() / 1024;
			totalMem = runtime.totalMemory() / 1024;
			System.out.println("DEBUG: Free memory : " + freeMem + " KB");
			System.out.println("DEBUG: Total memory : " + totalMem + " KB");
			err.printStackTrace();
		}
	}

	public static long listRecursively(File fdir) throws Exception {
		// Collect Attributes for each file
		Date lm = new Date(fdir.lastModified()); // Last Date the File was
		// changed

		long fsizeBytes = fdir.length(); // Get File Size
		if (fsizeBytes >= 1024) {
			float fsizeKBytes = fsizeBytes / 1024; // convert it to K.
			System.out.println(fdir.getAbsolutePath() + " modified: " + lm
					+ " size: ~" + fsizeKBytes + " KBytes"); // Print name.
		} else {
			System.out.println(fdir.getAbsolutePath() + " modified: " + lm
					+ " size: ~" + fsizeBytes + " Bytes"); // Print name.
		}

		long counter = 1;
		if (fdir.isDirectory()) { // DEBUG-AJP Add an excludes here for
			totalDirectories = totalDirectories + 1;
			// directories we don't want scanned
			Thread.sleep(10); // Currently commented for testing/development
			counter = 0; // It's a directory so we reset counter to zero.

			for (File f : fdir.listFiles()) { // Go over each file/subdirectory.
				counter = counter + listRecursively(f);
			}
		}

		return counter;
	}

}
