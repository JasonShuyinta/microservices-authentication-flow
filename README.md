# Microservice Authentication Flow
This tutorial demonstrates how to handle authentication and authorization through JWT in a microservice architecture.

The tech stack used is the following:

- Spring 3 
- Java 17
- Spring Eureka
- Spring Security
- MapStruct
- MySQL
- Lombok
- JWT

### Architecture
The project is made up of a Service Registry that uses Spring Eureka, where the main class is annotated with @EnableEurekaServer, and you can find it in the
**service-registry** module. This registry is listening on default port 8761.

Then the **api-gateway** was created, listening at port 8080. This means that every request from the client is made to this port and the gateway is in charge
of dispatching requests to the different microservices. 
We will see how the authentication process works when using an api gateway.

3 microservices are then created: **authentication-service**, **interview-service** and **jobsearch-service**.
The authentication service is the one in charge of creating and validating a token, while the other 2 are just basic microservices with feasible functionalities.

#### Authentication Service
We implement the authentication service as usual, following the Spring Security best practices and implementing the UserDetailsService interface.
We create a custom user object the extends the UserDetails and have it saved from a custom repository we create.
After the configuration is done we implement 3 endpoints: */register*, */token* and */validate*.

- **/register**: it simply saves a user to the MySQL Database.
- **/token**: once a user is saved to the DB, we retrieve that user passing to this endpoint the username and password. If a record is found then a 
JWT token is created and returned to the client as a String.
- **/validate**: this endpoint simply serves the purpose of validating a token, for example to verify that it is not corrupted or expired. This is the endpoint
that is goind to be replicated by the API Gateway before passing any request further down the chain.

#### API Gateway
This is where the magic happens:
First remember to add Webflux to the pom.xml of your gateway:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```
Then create an AuthenticationFilter class that extends from AbstractGatewayFilterFactory<AuthenticationFilter.Config>.
To do so you need to add an empty constructor of type Config.

Implement the suggested method and inject a Route Validator described in the specific file as follows: 
```java

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
```
In this extract we are telling the gateway to ignore the specified endpoints as we don't need authentication or authorization to access does.
We can now inject this validator in the AuthenticationFilter class.

```java
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //check the existence of a Bearer header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    throw new RuntimeException("Unauthorized");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {}
}

```
As our first step we need to verify that the requests doesn't come from a whitelisted endpoint, and to do so we use the validator.
If this first test is passed we then retrieve the header from the request containing the "Bearer " keyword.

If no header is present, an exception is thrown.

If, on the other hand, a header is found, we validate the token with the same method we used in the authentication-service module. In fact, 
we could directly implement the logic right in the API Gateway and avoid adding the endpoint in the authentication microservice but instead
using it as a utility function as we did here.

Once the validation is successful we can then tell the gateway to continue its chain and pass the request to the correct microservice.

#### Author
Jason Shuyinta