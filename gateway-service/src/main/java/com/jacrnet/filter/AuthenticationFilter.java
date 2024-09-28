package com.jacrnet.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jacrnet.dto.TokenValidationRequest;
import com.jacrnet.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final Map<String, List<String>> routeAuthoritiesMap = new HashMap<>();

    static {
        routeAuthoritiesMap.put("/plans/all", List.of("READ"));
        routeAuthoritiesMap.put("/plans/add", List.of("CREATE"));
        routeAuthoritiesMap.put("/plans/update/id", List.of("UPDATE"));
        routeAuthoritiesMap.put("/plans/delete/id", List.of("DELETE"));
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuración si fuera necesario
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Extraer el token del header "Authorization"
            String token = extractToken(exchange.getRequest().getHeaders());
            String requestPath = exchange.getRequest().getURI().getPath();

            if (token == null) {
                return Mono.error(new TokenException("Missing Authorization Header"));
            }


            // Llamar al auth-service para validar el token
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8090/auth/validate")
                    .bodyValue(new TokenValidationRequest(token))
                    .retrieve()
                    .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                            clientResponse -> Mono.error(new TokenException("Invalid Token")))
                    .bodyToMono(Void.class)
                    .then(Mono.defer(() -> {

                        DecodedJWT decodedJWT = JWT.decode(token);
                        List<String> userAuthorities = List.of(decodedJWT.getClaim("authorities").asString().split(","));

                        // Obtener autoridades requeridas según la ruta
                        List<String> requiredAuthorities = routeAuthoritiesMap.get(requestPath);

                        if (requiredAuthorities != null && !userAuthorities.containsAll(requiredAuthorities)) {
                            return Mono.error(new TokenException("Unauthorized"));
                        }

                        return chain.filter(exchange);
                    }));

        };
    }

    private String extractToken(HttpHeaders headers) {
        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            String bearerToken = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
        }
        return null;
    }

}
