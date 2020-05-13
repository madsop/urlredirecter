package no.madsopheim.urlredirecter;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class BusinessLogicTests {

    @Test
    public void canMove() throws IOException {
        HttpExchange httpExchange = createHttpExchange();
        new Main().moved(httpExchange);
    }

    @Test
    public void removesSlackPath() {
        URI uri = URI.create("https://slack-redir.net/link?url=http://www.google.com");
        String location = new Main().getLocation(uri);
        assertEquals(location, "http://www.google.com");
    }

    @Test
    public void doesNothingWithNormalPath() {
        URI uri = URI.create("http://www.google.com");
        String location = new Main().getLocation(uri);
        assertEquals(location, "http://www.google.com");

    }

    private HttpExchange createHttpExchange() {
        return new HttpExchange() {
            @Override
            public Headers getRequestHeaders() {
                return new Headers();
            }

            @Override
            public Headers getResponseHeaders() {
                return new Headers();
            }

            @Override
            public URI getRequestURI() {
                return URI.create("https://slack-redir.net/link?url=https://slashdot.org");
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return null;
            }

            @Override
            public OutputStream getResponseBody() {
                return null;
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
    }
}
