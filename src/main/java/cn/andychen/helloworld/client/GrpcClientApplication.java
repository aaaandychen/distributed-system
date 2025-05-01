package cn.andychen.helloworld.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 15:32
 * @Description
 */

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
//        int port = findAvailablePort(7000);
//        System.setProperty("server.port", String.valueOf(port));
//        System.out.println("Client started on port: " + port);
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    // 从 7000 开始查找可用端口
    private static int findAvailablePort(int startingPort) {
        int port = startingPort;
        while (port < 8000) {
            try (ServerSocket socket = new ServerSocket(port)) {
                return port;
            } catch (IOException ignored) {
                port++;
            }
        }
        throw new RuntimeException("No available port found between 7000–8000");
    }
}