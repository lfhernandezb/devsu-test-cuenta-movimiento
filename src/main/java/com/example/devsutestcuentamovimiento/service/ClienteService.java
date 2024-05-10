package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.error_handling.RestErrorHandler;
import com.example.devsutestcuentamovimiento.exception.MissingConfigurationValueException;
import com.example.devsutestcuentamovimiento.pojo.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final RestTemplate restTemplate;
    @Value("${clienteService.url}")
    private String clienteServiceUrl;

    public ClienteService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Cliente> getById(long clientId) { //throws InterruptedException, RestClientException, MissingConfigurationValueException {
        if (clienteServiceUrl.isEmpty()) {
            throw new MissingConfigurationValueException("No existe la URL del servicio de clientes");
        }

        String url = clienteServiceUrl + "/param?clienteId=" + clientId;

        Cliente cliente = restTemplate.getForObject(url, Cliente.class);

        return CompletableFuture.completedFuture(cliente);

    }

    @Async
    public CompletableFuture<Cliente> getByNombre(String nombreCliente) { // throws InterruptedException, RestClientException, MissingConfigurationValueException {

        logger.info("called getByNombre");
        if (clienteServiceUrl.isEmpty()) {
            throw new MissingConfigurationValueException("No existe la URL del servicio de clientes");
        }

        String url = clienteServiceUrl + "/param?nombre=" + nombreCliente;

        Cliente cliente = restTemplate.getForObject(url, Cliente.class);

        logger.info("after restTemplate.getForObject(url, Cliente.class)");

        return CompletableFuture.completedFuture(cliente);

    }
}
