package comms;

import java.io.*;
import java.net.*;

public class Client {
	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public Client() {
	}

	// public void turnOn() {
	// out.println("light:on");
	// }
	//
	// public void turnOff() {
	// out.println("light:off");
	// }
	
	public void sendCommand(String command){
		out.println(command);
	}

	public void connectFromServlet() {
		try {
			echoSocket = new Socket("194.47.40.162", 8888);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O");
			System.exit(1);
		}
	}

	public void login(String username, String password) {
		String msgToServer = username + ":" + password;
		out.println(msgToServer);
	}

	public String getDeviceState() {
		String stateFromServer = "";
		// out.println(data);// send e.g.light:on/off
		try {
			stateFromServer = in.readLine();
			// System.out.println("reply from server: " +stateFromServer);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to get state from server");
		}
		return stateFromServer;

	}

	public String listen() {
		String msgFromServer = "";
		try {
			msgFromServer = in.readLine();
			// System.out.println("Msg from server: "+msgFromServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgFromServer;
	}
}
