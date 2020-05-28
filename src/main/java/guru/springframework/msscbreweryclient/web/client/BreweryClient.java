package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.ConsumerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CONSUMER_PATH_V1 = "/api/v1/consumer/";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID id, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + id.toString(), beerDto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apihost + BEER_PATH_V1 + id.toString());
    }

    public ConsumerDto getConsumerById(UUID id) {
        return restTemplate.getForObject(apihost + CONSUMER_PATH_V1 + id, ConsumerDto.class);
    }

    public URI saveNewConsumer(ConsumerDto consumerDto) {
        return restTemplate.postForLocation(apihost + CONSUMER_PATH_V1, consumerDto);
    }

    public void updateConsumer(UUID id, ConsumerDto consumerDto) {
        restTemplate.put(apihost + CONSUMER_PATH_V1 + id, consumerDto);
    }

    public void deleteConsumer(UUID id) {
        restTemplate.delete(apihost + CONSUMER_PATH_V1 + id);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
