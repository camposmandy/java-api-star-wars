package br.com.amandacampos.starwarsplanets.connectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReactiveServerConnector {
    private final WebClient client;

    @Autowired
    public ReactiveServerConnector(WebClient client) {
        this.client = client;
    }

    public Mono<ClientResponse> doPost(String url, Object request) {
        return  client.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(request))
                .exchange();
    }

    public Mono<ClientResponse> doGet(String url) {
        return  client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange();
    }
}
