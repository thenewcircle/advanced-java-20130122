package com.marakana.clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			Socket server = new Socket(InetAddress.getLocalHost(), 31337);
			try {
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
				try {
					out.println("Hello, Server!");
				} finally {
					out.close();
				}
			} finally {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
