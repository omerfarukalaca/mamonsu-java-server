package com.omerfarukalaca.mamonsujavaserver;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MamonsuJavaServerApplication {

	@Value("${server.socket.port}")
	private final static int port = 10051;

	public static void main(String[] args) {
		SpringApplication.run(MamonsuJavaServerApplication.class, args);

		try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(true) {
                new MamonsuServer(serverSocket.accept()).start();
            }
        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
	}

}
