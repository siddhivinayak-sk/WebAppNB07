Stereotype
@Component - Make a class bean so that it can be created in IOC container while component scanning
@Repository - It is a specialized component type class only for DAO (it makes class eligible to translate DAOException to DataAccessException)
@Service - It is also a specialized component for service layer which contains business logic
@Controller - It makes class enable for Spring Web MVC

@ControllerAdvice
@ModelAttribute
@ExceptionHandler
@Path
@RequestParam
@Model
@RequestBody
@ResponseBody
@RequestMapping


@Async

All are use for auto-wiring
@Resource
@Inject
@Qualifier
@Autowired

@Configurable

JSR-250 Annotations supported by Spring since 2.5
@Resource
@PostConstruct
@PreDestory

JSR-300 Annotation supported by Spring
@Inject
@Name

@Primary - If two or more implementation found of same interface, it could be used to define primary implementation so that @Autowired can get precedence and wire with the primary implementation

@Bean - Used to define bean to tell the spring container that this class is a bean, a name can be associated with it
@Configuration
@Import
@Scope


Autowire
-No
-ByName
-ByType
-Contructor
-AutoDetect

@Cachable
@CacheEvict
@Caching


Zuul - Zuul Proxy is used for routing request and used as gateway filtering @EnableZuulProxy
Eureka - Discovery Server and client for service discovery and status @EnableEurekaServer and @EnableEurekaClient
Consul - Discovery server same like Eureka but powerful clustering and configuration feature; create client by @EnableDiscoveryClient
Admin Server - Used to created admin server for microservice health monitoring @EnableAdminServer
Admin client - Used to create admin client by spcifying pom dependency and property spring.boot.admin.client.url=http://localhost:8890 
Sleuth - It is used to define the logging format which include the service id, request id, event id to trace the details for the logs
ZipKin - It is used to log managment with the help of Sleuth; to discover the logs in to managment app @EnableZipkinServer
Kafka - It is powerful topic based, highly scalable and stream proving messaing system
ActiveMQ - Enterprises messaging queue system by Apache, supports queue, topic
RabbitMQ - Enterprises messassing queue system like ActiveMQ by Enlarg supports queue, topic
Twilo - SMS and Call api form spring application
Hystrix - If any request takes much time than defined, calls a fallback method to provide a default immediate response 
Themelyfe - It is HTML processor and work only when InternalResourceViewResolver is off
Swagger - Swagger API for API documentation, swagger-ui.html
H2 database - In memory database for spring boot applications
Flyway - It is an application to manage SQL script for the application
HAL Browser - Spring HAL Browser is integrated browser for discovering urls data
HAWTIO - It is an application which is used to see the details of application by various ways like, MBean browsering, JMX etc. Work on Actuator port with localhost:8081/actuator/hawtio
Jolokia - It is client for HAWTIO which sends data to HAWTIO so that HAWTIO dashboard can show information

