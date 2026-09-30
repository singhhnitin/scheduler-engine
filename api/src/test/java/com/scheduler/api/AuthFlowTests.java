package com.scheduler.api;

import com.scheduler.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * End-to-end check of the JWT flow against a real MongoDB:
 * register -> login -> no token = 401 -> with token = 200.
 */
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnabledIfEnvironmentVariable(named = "MONGODB_URI", matches = ".+")
class AuthFlowTests {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient client = HttpClient.newHttpClient();

    private HttpResponse<String> post(String path, String json, String token) throws Exception {
        HttpRequest.Builder b = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json));
        if (token != null) b.header("Authorization", "Bearer " + token);
        return client.send(b.build(), HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> get(String path, String token) throws Exception {
        HttpRequest.Builder b = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).GET();
        if (token != null) b.header("Authorization", "Bearer " + token);
        return client.send(b.build(), HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void registerLoginAndAccessProtectedEndpoint() throws Exception {
        String user = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String creds = "{\"username\":\"" + user + "\",\"password\":\"secret123\"}";

        assertEquals(201, post("/auth/register", creds, null).statusCode());
        assertEquals(409, post("/auth/register", creds, null).statusCode());

        HttpResponse<String> login = post("/auth/login", creds, null);
        assertEquals(200, login.statusCode());
        String body = login.body();
        assertTrue(body.contains("\"token\""));
        String token = body.replaceAll(".*\"token\"\\s*:\\s*\"([^\"]+)\".*", "$1");

        assertEquals(401, get("/schedule", null).statusCode());
        assertEquals(401, get("/schedule", "not-a-real-token").statusCode());
        assertEquals(200, get("/schedule", token).statusCode());

        String wrong = "{\"username\":\"" + user + "\",\"password\":\"wrongpass\"}";
        assertEquals(401, post("/auth/login", wrong, null).statusCode());
    }
}
