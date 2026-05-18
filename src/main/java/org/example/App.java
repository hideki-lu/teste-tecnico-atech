package org.example;

import com.google.gson.*;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Contexto que serve arquivos estáticos (front-end)
        server.createContext("/", new StaticFileHandler());

        // Endpoint da API
        server.createContext("/api/maxSimPassengers", new SumHandler());

        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();
        System.out.println("Servidor rodando em http://localhost:" + port);
    }

    // Servidor de arquivos estáticos (apenas index.html na raiz)
    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String path = exchange.getRequestURI().getPath();
                if ("/".equals(path) || "/index.html".equals(path)) {
                    InputStream is = getClass().getResourceAsStream("/static/index.html");
                    if (is == null) {
                        exchange.sendResponseHeaders(404, -1);
                        return;
                    }
                    byte[] bytes = is.readAllBytes();
                    exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                    exchange.sendResponseHeaders(200, bytes.length);
                    exchange.getResponseBody().write(bytes);
                    exchange.getResponseBody().close();
                    return;
                }
            }
            exchange.sendResponseHeaders(404, -1);
        }
    }

    // Handler da API
    static class SumHandler implements HttpHandler {
        private final Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Método não permitido\"}");
                return;
            }

            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            try {
                JsonObject json = JsonParser.parseString(body).getAsJsonObject();
                JsonArray jsonArrayE = json.getAsJsonArray("entradas");
                JsonArray jsonArrayS = json.getAsJsonArray("saidas");

                if (jsonArrayE == null || jsonArrayS == null) {
                    sendJson(exchange, 400, "{\"error\":\"Arrays 'entradas' e 'saídas' são obrigatórios\"}");
                    return;
                }
                if (jsonArrayE.size() != jsonArrayS.size()) {
                    sendJson(exchange, 400, "{\"error\":\"Os arrays devem ter tamanhos iguais\"}");
                    return;
                }
                int[] arrE = Utils.jsonToIntArray(jsonArrayE);
                int[] arrS = Utils.jsonToIntArray(jsonArrayS);

                int quantidadeMaximaPassageiros = MaxSimultaneousPassengers.maxOverlap(arrE, arrS);


                String response = String.format("{\"result\": %s}", quantidadeMaximaPassageiros);
                sendJson(exchange, 200, response);
            } catch (JsonSyntaxException | IllegalStateException e) {
                sendJson(exchange, 400, "{\"error\":\"JSON inválido. Esperado: {\\\"a\\\": [...], \\\"b\\\": [...]}\"}");
            }
        }

        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(status, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();
        }
    }
}
