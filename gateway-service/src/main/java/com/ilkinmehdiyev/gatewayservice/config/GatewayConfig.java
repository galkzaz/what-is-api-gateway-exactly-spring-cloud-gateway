package com.ilkinmehdiyev.gatewayservice.config;

import com.ilkinmehdiyev.gatewayservice.config.constant.ConfigurationConstants;
import com.ilkinmehdiyev.gatewayservice.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static com.ilkinmehdiyev.gatewayservice.config.constant.ConfigurationConstants.API_V1;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class GatewayConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Value("${ms.product.root}")
        private String msProductRoot;

        @Value("${ms.auth.root}")
        private String msAuthRoot;

        @Value("${ms.order.root}")
        private String msOrderRoot;

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
                return routeLocatorBuilder
                                .routes()
                                .route(
                                                ConfigurationConstants.AUTH_SERVICE_ID,
                                                getRoute(ConfigurationConstants.AUTH_SERVICE_ROOT, msAuthRoot))
                                .route(
                                                ConfigurationConstants.PRODUCT_SERVICE_ID,
                                                getRoute(ConfigurationConstants.PRODUCTS_SERVICE_ROOT, msProductRoot))
                                .route(
                                                ConfigurationConstants.ORDER_SERVICE_ID,
                                                getRoute(ConfigurationConstants.ORDERS_SERVICE_ROOT, msOrderRoot))
                                .build();
        }

        /*
         * What we are passing to this routing method is root (e,g /products) and actual
         * URI (e,g localhost:8082). What it means, when the Client executes an API call
         * to any endpoint(let's assume our /products/getProducts) with ‘/products’
         * root, first we are concatenating the path from the root (we are getting
         * everything after the root which is ‘/getProducts’) and we are replacing the
         * root with our internal API which is (e,g localhost:8082).
         * 
         * So when we finished the request, client root + ‘/products/getProducts’ became
         * internal server root + ‘/api/v1/products/getProducts’.
         */
        private Function<PredicateSpec, Buildable<Route>> getRoute(String root, String uri) {
                return r -> r.path(root.concat("/**"))
                                .filters(filterSpec -> getGatewayFilterSpec(filterSpec, root))
                                .uri(uri);
        }

        private GatewayFilterSpec getGatewayFilterSpec(GatewayFilterSpec f, String serviceUri) {
                /*
                 * We will create a JwtAuthenticationFilter, we will mock it instead of applying
                 * real implementation. before we rewrite our HTTP path, we can apply as many
                 * custom filters as we want
                 */
                /*
                 * So what is happening, basically, in every Client call, we are always adding
                 * this JwtFilter, as we are doing in traditional SpringSecurity. In this API
                 * call, we are calling the Auth microservice with WebClient, for the simplicity
                 * we are just returning a ‘true’ as access granted in every request, this is
                 * the AuthController:
                 */
                return f.rewritePath(
                                serviceUri.concat("(?<segment>.*)"), API_V1.concat(serviceUri).concat("${segment}"))
                                .filter(jwtAuthenticationFilter);
        }
}
