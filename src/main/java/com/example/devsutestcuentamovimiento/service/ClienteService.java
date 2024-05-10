package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.pojo.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ClienteService {

    private final RestTemplate restTemplate;
    @Value("${clienteService.url}")
    private String clienteServiceUrl;

    public ClienteService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Cliente> getById(long clientId) throws InterruptedException {
        if (clienteServiceUrl.isEmpty()) {
            return null;
        }

        String url = clienteServiceUrl + "/param?clienteId=" + clientId;

        Cliente cliente = restTemplate.getForObject(url, Cliente.class);

        return CompletableFuture.completedFuture(cliente);

    }

    @Async
    public CompletableFuture<Cliente> getByNombre(String nombreCliente) throws InterruptedException {
        if (clienteServiceUrl.isEmpty()) {
            return null;
        }

        String url = clienteServiceUrl + "/param?nombre=" + nombreCliente;

        Cliente cliente = restTemplate.getForObject(url, Cliente.class);

        return CompletableFuture.completedFuture(cliente);

    }
}
