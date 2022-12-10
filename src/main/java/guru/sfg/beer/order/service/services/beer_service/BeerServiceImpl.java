package guru.sfg.beer.order.service.services.beer_service;

import guru.sfg.beer.order.service.services.beer_service.interfaces.BeerService;
import guru.sfg.beer.order.service.services.beer_service.models.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@ConfigurationProperties(prefix = "com.babalola", ignoreUnknownFields = false)
@Service
public class BeerServiceImpl implements BeerService {
    public final static String BeerServiceIdPath = "api/beer/";
    public final static String BeerServiceUpcPath = "api/beer/beerUpc/";

    private final RestTemplate restTemplate;
    private String beerServiceHost;


    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        System.out.println("Calling Beer Service getById");

        return Optional.of(restTemplate.getForObject(beerServiceHost + BeerServiceIdPath + id.toString(), BeerDTO.class));
    }

    @Override
    public Optional<BeerDTO> getBeerByUpc(String upc) {

        Optional<BeerDTO> newBeer =  Optional.ofNullable(restTemplate.getForObject(beerServiceHost + BeerServiceUpcPath + upc, BeerDTO.class ));

        System.out.println("Beer Service Called at: " + beerServiceHost + BeerServiceUpcPath + upc);
        return newBeer;
    }
}
