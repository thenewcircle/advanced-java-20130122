package com.marakana.clientserver;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket server = new Socket(InetAddress.getLocalHost(), 31337);
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println("Hello, Server!");
		server.close();
	}

}
