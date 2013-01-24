package com.marakana.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(31337);
				Socket client = server.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						client.getInputStream()))) {
			System.out.println(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
