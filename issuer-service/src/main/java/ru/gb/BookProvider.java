package ru.gb;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import ru.gb.api.Book;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookProvider {

  // HttpClient - java.net
  // RestTemplate - spring.web
  // WebClient - spring.reactive

  private final WebClient webClient;
//  private final EurekaClient eurekaClient;

  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
//    this.eurekaClient = eurekaClient;
  }

  public UUID getRandomBookId() {
    Book randomBook = webClient.get()
      .uri("http://book-service/api/book/random")
      .retrieve()
      .bodyToMono(Book.class)
      .block();

    return randomBook.getId();
  }

  // round robbin
//  private String getBookServiceIp() {
//    Application application = eurekaClient.getApplication("BOOK-SERVICE");
//    List<InstanceInfo> instances = application.getInstances();
//
//    int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//    InstanceInfo randomInstance = instances.get(randomIndex);
//    return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
//  }

}
