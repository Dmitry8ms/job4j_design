package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                StringBuilder lines = new StringBuilder();
                String message;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        //System.out.println(str);
                        lines.append(str).append(System.lineSeparator());
                    }
                    System.out.println(lines);
                    message = lines.substring(lines.indexOf("msg=") + 4, lines.indexOf("HTTP")).trim();
                    if (message.equals("Bye")) {
                        out.write("Bye\n\n".getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        server.close();
                    }
                    out.flush();
                }
            }
        }
    }
}
