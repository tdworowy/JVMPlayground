

public class ConectionInfo {
	
	private static String host = "192.168.99.100";
	private static String userName = "admin";
	private static String password = "s0m3p4ssw0rd";
	private static int port = 5672;
	private static String virtualHost = "/";
	private static String javaQueeName = "JavaQueue";
	private static String pythonQueeName = "PythonQueue";

	public static String getJavaQueeName() {
		return javaQueeName;
	}

	public  static void setJavaQueeName(String javaQueeName) {
		javaQueeName = javaQueeName;
	}

	public static String getPythonQueeName() {
		return pythonQueeName;
	}

	public static void setPythonQueeName(String pythonQueeName) {
		pythonQueeName = pythonQueeName;
	}




	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		host = host;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		password = password;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		port = port;
	}


	public static String getVirtualHost() {
		return virtualHost;
	}

	public static void setVirtualHost(String virtualHost) {
		virtualHost = virtualHost;
	}

	
	

}
