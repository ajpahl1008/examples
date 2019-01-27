package com.pahlsoft.filewatcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class FileWatcher implements Runnable {
	static boolean running;
	
	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			for (String watcherArgs : args) {
				if (watcherArgs.contains("-UAT")) {
						Global.UAT = Boolean.TRUE;
						System.out.println("FileWatcher: Database: " + Global.UatDBServer);
					
				} else if (watcherArgs.contains("-PROD")) {
					System.out.println("FileWatcher: Database: " + Global.ProdDBServer);
					    Global.PROD = Boolean.TRUE;
					
				} else if (watcherArgs.contains("-DEBUG")) {
					StringTokenizer stokARGDBG = new StringTokenizer(watcherArgs, ":");
					//String stARGDBG = stokARGDBG.nextToken();
					if (stokARGDBG.hasMoreTokens()) {
						Global.DEBUGENABLED = Boolean.TRUE;
					}
				}	
			}
//			// .isEmpty is only available on java6 platforms.
//			if (( || (Global.FileWatchLocation.isEmpty())) {
//				System.out.println("Usage: FileWatcher.jar -PROD|-UAT");
//				System.exit(0);
//			} 
		}

		System.out.println("FileWatcher: Starting FileWatcher...");
		System.out.println("FileWatcher: File (IN)        Location: " + Global.FILEWATCHLOCATION);
		System.out.println("FileWatcher: File (PROCESSED) Location: " + Global.FILEPROCESSINGLOCATION);

		running=Boolean.TRUE;
		FileWatcher fileWatcher = new FileWatcher();

		fileWatcher.run();
        
	}
	
	public void run() {
		File[] fileList;
		@SuppressWarnings("unused")
		ArrayList<String> cmdOut = new ArrayList<String>();
		@SuppressWarnings("unused")
		Boolean insertResult;
		Boolean checkResult;
		String fileTagTds= "";
		
		int ctr;
		try {
			while (running) {

				Date tds = new Date();
				// Format for Time Date Stamp Tag
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd-hhmmss");
				
				Thread.sleep(1000);
				
				// Create a new Time Date Stamp tag for files being dropped
				fileTagTds=formatter.format(tds);
				
				System.out.println("FileWatcher: Scanning for Files: " + tds);
				
				// Add any files that exist to an arrayList
				
				//fileList = new UnixCommand().exec("ls " + Global.FileWatchLocation);
				File nfile = new File(Global.FILEPROCESSINGLOCATION);
				fileList = nfile.listFiles(); //DEBUG-AJP: Implement
				

				for (ctr=0;ctr<fileList.length;ctr++) {
					System.out.println("FileWatcher: New File(s) Found: " + Global.FILEWATCHLOCATION + fileList[ctr]);
					
					// Look at the file being staged. Check if it's being updated.
					String stagingFileName = Global.FILEWATCHLOCATION + fileList[ctr];
					File fileHandle = new File(stagingFileName);
					Date fileLm = new Date(fileHandle.lastModified());
					
					// If the current time date stamp is AFTER the files Last modified
					if (tds.after(fileLm)) {

						// Create a string representing the processed file location
						String processedFileName = Global.FILEPROCESSINGLOCATION + fileList[ctr] + "-" + fileTagTds;
						checkResult =  new UpdateDatabase().checkDBURI(processedFileName);

						if (!checkResult) {
							// Move file from inbound directory to Processing Directory
							cmdOut = new UnixCommand().exec("/opt/apps/applications/hadoop-0.20.1/bin/hadoop fs -copyFromLocal " + Global.FILEWATCHLOCATION + fileList[ctr] + " " + processedFileName);
							insertResult = new UpdateDatabase().writeDBURI(processedFileName);
							cmdOut = new UnixCommand().exec("rm " + stagingFileName );
						} else {
							System.out.println("FileWatcher: File Already exists. Adding Again.");
							// Move file to Processing Directory
							cmdOut = new UnixCommand().exec("/opt/apps/applications/hadoop-0.20.1/bin/hadoop fs -rm " + Global.FILEWATCHLOCATION + fileList[ctr] + " " + processedFileName);
							cmdOut = new UnixCommand().exec("/opt/apps/applications/hadoop-0.20.1/bin/hadoop fs -copyFromLocal " + Global.FILEWATCHLOCATION + fileList[ctr] + " " + processedFileName);
							cmdOut = new UnixCommand().exec("rm " + stagingFileName );
						}
					} else {
						System.out.println("FileWatcher: Skipping File: " + stagingFileName +   ", its still being updated.");
						System.out.println("DEBUG: fileLastModified: " + fileLm);
						System.out.println("DEBUG: currentTime: " + tds);
					}

				} 
				Thread.sleep(Global.SLEEPTIME);

			}
		} catch (Exception e) {
			if (Global.DEBUGENABLED) {e.printStackTrace(); }
		} 
	}
	
}
