package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class EchoServer {
    private static Map<String, Supplier<Boolean>> dispatch = new HashMap<>();
    private static final String STOP = "Exit";
    private static final String HELLO = "Hello";
    private static final String ANY = "Any";
    private static String msg;
    private static OutputStream out;
    private static Socket socket;
    private static ServerSocket server;

    public Supplier<Boolean> stop() {
        return () -> {
            try {
                out.write("Bye\n\n".getBytes(StandardCharsets.UTF_8));
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        };
    }

    public Supplier<Boolean> hello() {
        return () -> {
            try {
                out.write("Hello, dear friend!\n\n".getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        };
    }

    public Supplier<Boolean> any() {
        return () -> {
            try {
                out.write(("You wrote " + msg + "\n\n").getBytes(StandardCharsets.UTF_8));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        };
    }
    public static void main(String[] args) throws IOException {
        dispatch.put(STOP, new EchoServer().stop());
        dispatch.put(HELLO, new EchoServer().hello());
        dispatch.put(ANY, new EchoServer().any());
        try {
            server = new ServerSocket(9000);
            while (!server.isClosed()) {
                StringBuilder lines = new StringBuilder();
                String message;
                socket = server.accept();
                try {
                     out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        //System.out.println(str);
                        lines.append(str).append(System.lineSeparator());
                    }
                    System.out.println(lines);
                    Scanner readLine = new Scanner(lines.toString());
                    String line = readLine.nextLine();
                    message = line.substring(line.indexOf("msg=") + 4, line.indexOf("HTTP")).trim();
                    if (!STOP.equals(message) && !HELLO.equals(message)) {
                        msg = message;
                        message = ANY;
                    }
                    dispatch.get(message).get();
                    out.flush();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
