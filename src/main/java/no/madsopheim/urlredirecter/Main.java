package no.madsopheim.urlredirecter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URI;

public class Main {

    void moved(HttpExchange httpExchange) throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        URI uri = httpExchange.getRequestURI();
        responseHeaders.set("Content-Type", "text/html");
        responseHeaders.set("Location", getLocation(uri));
        httpExchange.sendResponseHeaders(301, -1);
        httpExchange.close();
    }

    String getLocation(URI uri) {
        return uri.toString().replace("https://slack-redir.net/link?url=", "");
    }
}
