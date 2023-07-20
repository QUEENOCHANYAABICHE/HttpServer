package org.app;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.ServerSocket;

import java.net.Socket;


public class Main {

    public static void main(String[] args) throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(2040)) {

            System.out.println("Server has started on port 2040");

            HttpServer server;

            Socket socket;

            while (true) {

                socket = serverSocket.accept();

                server = new HttpServer(socket);

                server.start();

            }

        }

    }

}







