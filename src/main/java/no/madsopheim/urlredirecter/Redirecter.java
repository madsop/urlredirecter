package no.madsopheim.urlredirecter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public class Redirecter implements HttpHandler {

    void moved(HttpExchange httpExchange) throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        URI uri = httpExchange.getRequestURI();
        responseHeaders.set("Content-Type", "text/html");
        responseHeaders.set("Location", getLocation(uri));
        httpExchange.sendResponseHeaders(301, -1);
        httpExchange.close();
    }

    String getLocation(URI uri) {
        String replaceSlackRedirect = uri.toString().replace("https://slack-redir.net/link?url=", "");
        String removeFacebookId = replaceSlackRedirect.split("/?fblcid=")[0];
        System.out.println(removeFacebookId);
        return removeFacebookId;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        moved(httpExchange);
    }
}
