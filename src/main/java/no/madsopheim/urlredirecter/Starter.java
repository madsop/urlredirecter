package no.madsopheim.urlredirecter;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Starter {

    public static void main(String[] args) throws IOException {
        new Starter().run();
    }

    void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/link", new Redirecter());
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
        System.out.println(" Server started on port 8001");
    }
}
