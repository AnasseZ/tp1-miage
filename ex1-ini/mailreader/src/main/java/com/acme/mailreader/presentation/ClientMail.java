package com.acme.mailreader.presentation;

public class ClientMail {

	private static boolean production;
	
	// Point d'entrée
	public static void main(String[] args) {
		production = Boolean.parseBoolean(args[0]);
		//...
	}

}
