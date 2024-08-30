# What is API Gateway exactly? Spring Cloud Gateway?

./mvnw install  -DskipTests

./mvnw spring-boot:run -DskipTests -pl product-service

./mvnw spring-boot:run -DskipTests -pl order-service

./mvnw spring-boot:run -DskipTests -pl auth-service

./mvnw spring-boot:run -DskipTests -pl gateway-service

http :8080/products/getProducts

http POST :8080/orders/makeOrder