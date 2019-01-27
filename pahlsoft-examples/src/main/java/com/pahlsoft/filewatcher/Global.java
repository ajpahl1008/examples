package com.pahlsoft.filewatcher;

public class Global {

	public static String DevDBServer = new String("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=" +
    		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
    		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
    		")(CONNECT_DATA=(SERVICE_NAME=ICEDEV_SVC1)(SERVER=DEDICATED))" +
    		"(FAILOVER_MODE= " +
    		"(TYPE=SELECT)" +
    		"(METHOD=BASIC)" +
    		"(RETRIES=180)" +
    		"(DELAY=5)))");
	
	 public static String UatDBServer = new String("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		")(CONNECT_DATA=(SERVICE_NAME=ICEUAT_SVC)(SERVER=DEDICATED))" +
     		"(FAILOVER_MODE= " +
     		"(TYPE=SELECT)" +
     		"(METHOD=BASIC)" +
     		"(RETRIES=180)" +
     		"(DELAY=5)))");
     
     public static String ProdDBServer = new String("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		"(ADDRESS=(PROTOCOL=TCP)(HOST=servername)(PORT=1540))" +
     		")(CONNECT_DATA=(SERVICE_NAME=ICEPRD_SVC)(SERVER=DEDICATED))" +
     		"(FAILOVER_MODE= " +
     		"(TYPE=SELECT)" +
     		"(METHOD=BASIC)" +
     		"(RETRIES=180)" +
     		"(DELAY=5)))");
	
  public static String FILEWATCHLOCATION = "/data/hdfs_staging/ADF/in/";
  public static String FILEPROCESSINGLOCATION = "/prod/data/ADF/";
  public static Boolean DEBUGENABLED = Boolean.FALSE;
  public static Boolean PROD = Boolean.FALSE;
  public static Boolean UAT = Boolean.FALSE;
  public static int SLEEPTIME = 300000;

}
