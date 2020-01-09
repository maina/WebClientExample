package com.honeacademy.webclientexample.utils;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriBuilder;

import reactor.core.publisher.Mono;
/**
 * Class with helper CRUD utility methods for making requests using webclient
 * @author james
 *
 */
public class WebClientUtil {

	public static <T> Mono<List<T>> fetchList(
		      final WebClient webClient,
		      final String bearerToken,
		      final ParameterizedTypeReference<List<T>> parameterizedTypeReference,
		      final Function<UriBuilder, URI> uriBuilderFunction) {
		    return webClient
		        .get()
		        .uri(uriBuilder -> uriBuilderFunction.apply(uriBuilder))
		        .header(HttpHeaders.AUTHORIZATION, bearerToken)
		        .retrieve()
		        .onStatus(
		            HttpStatus::isError,
		            clientResponse ->
		                clientResponse
		                    .bodyToMono(WebClientException.class)
		                    .flatMap(bodyValue -> Mono.error(bodyValue)))
		        .bodyToMono(parameterizedTypeReference);
		  }
	public static <T> Mono<T> fetchSingleObject(
		      final WebClient webClient,
		      final String bearerToken,
		      final Function<UriBuilder, URI> uriBuilderFunction,
		      final Class<T> modelImplementation) {
		    return webClient
		        .get()
		        .uri(uriBuilder -> uriBuilderFunction.apply(uriBuilder))
		        .header(HttpHeaders.AUTHORIZATION, bearerToken)
		        .retrieve()
		        .onStatus(
		            HttpStatus::isError,
		            clientResponse ->
		                clientResponse
		                    .bodyToMono(WebClientException.class)
		                    .flatMap(bodyValue -> Mono.error(bodyValue)))
		        .bodyToMono(modelImplementation);
		  }
	public static <T> Mono<T> update(
		      final WebClient webClient,
		      final String bearerToken,
		      final Function<UriBuilder, URI> uriBuilderFunction,
		      final T request,
		      final Class<T> modelImplementation) {
		    return webClient
		        .put()
		        .uri(uriBuilder -> uriBuilderFunction.apply(uriBuilder))
		        .body(Mono.just(request), modelImplementation)
		        .header(HttpHeaders.AUTHORIZATION, bearerToken)
		        .retrieve()
		        .onStatus(
		            HttpStatus::isError,
		            clientResponse ->
		                clientResponse
		                    .bodyToMono(WebClientException.class)
		                    .flatMap(bodyValue -> Mono.error(bodyValue)))
		        .bodyToMono(modelImplementation);
		  }
	
	  public static <T, U> Mono<T> add(
		      final WebClient webClient,
		      final String bearerToken,
		      final Function<UriBuilder, URI> uriBuilderFunction,
		      final U request,
		      final Class<T> responseModelClass) {
		    return webClient
		        .post()
		        .uri(uriBuilder -> uriBuilderFunction.apply(uriBuilder))
		        .body(BodyInserters.fromObject(request))
		        .header(HttpHeaders.AUTHORIZATION, bearerToken)
		        .retrieve()
		        .onStatus(
		            HttpStatus::isError,
		            clientResponse ->
		                clientResponse
		                    .bodyToMono(WebClientException.class)
		                    .flatMap(bodyValue -> Mono.error(bodyValue)))
		        .bodyToMono(responseModelClass);
		  }
	  
	  public static <T, U> Mono<List<T>> create(
		      final WebClient webClient,
		      final String bearerToken,
		      final Function<UriBuilder, URI> uriBuilderFunction,
		      final U request,
		      final ParameterizedTypeReference<List<T>> parameterizedTypeReference) {
		    return webClient
		        .post()
		        .uri(uriBuilder -> uriBuilderFunction.apply(uriBuilder))
		        .body(BodyInserters.fromObject(request))
		        .header(HttpHeaders.AUTHORIZATION, bearerToken)
		        .retrieve()
		        .onStatus(
		            HttpStatus::isError,
		            clientResponse ->
		                clientResponse
		                    .bodyToMono(WebClientException.class)
		                    .flatMap(bodyValue -> Mono.error(bodyValue)))
		        .bodyToMono(parameterizedTypeReference);
		  }
	  
	  public static WebClient getWebClient(String baseUrl) {
		  WebClient webClient = WebClient.builder()
			        .baseUrl(baseUrl)
			        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			        .build();
		return webClient;
		  
	  }

}
