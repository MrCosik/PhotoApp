package pl.learning.apigateway.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Autowired
    Environment env;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange,"No authorization header", HttpStatus.UNAUTHORIZED  );
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwtValid(jwt)){
                return onError(exchange,"Jwt is not valid", HttpStatus.UNAUTHORIZED);
            }




            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
//        ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
//        response.setStatusCode(httpStatus);


        return Mono.error(new Error());
    }

    public static class Config{

    }



    private boolean isJwtValid(String jwt){
        boolean returnValue = true;
//        String signingKeyB64= Base64.getEncoder().encodeToString("signingKey".getBytes(StandardCharsets.UTF_8));

        String subject = null;

        try {
            subject = Jwts.parser()
                    .setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            returnValue = false;
        }

        if(subject == null || subject.isEmpty()){
            returnValue = false;
        }

        return returnValue;
    }


}
