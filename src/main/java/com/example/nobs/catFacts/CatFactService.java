package com.example.nobs.catFacts;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.example.nobs.product.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service

public class CatFactService implements Query<Long, CatFactDTO> {
    private final RestTemplate restTemplate;
    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";
    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Long input) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(MAX_LENGTH, input)
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try{
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CatFactResponse.class);
            CatFactResponse catFactResponse = response.getBody();

            CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());
            return ResponseEntity.ok(catFactDTO);

        } catch (Exception e){
            throw new RuntimeException("Cat fact api is down!!");
        }


    }
}
