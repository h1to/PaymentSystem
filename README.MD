#Murzaliev Eldiiar ITSE-1908

##Topic: Payment system ##
PS (payment system) - system that allows clients to pay for different
services through different ways.



### Receiving payments ###
System receives payments from terminals, websites, payment centers etc. 
The source is not matter. 


### Processing payments ###
After receiving payment request the system sends request to provider and
after getting status from provider system assigns final status for 
payment.

###Lab 1 - IoC and DI ###
* application properties used
* table classes added
* database designed and configured through in memory H2 DB
* JPA repositories added
* Services implemented

Application properties
````
spring.datasource.url=jdbc:h2:mem:paydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=payer
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto= update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-con
spring.data.jpa.repositories.enabled=true
````

### Lab 2 - Spring configuration ###

 - AppConfig class added
 - Configuration by annotations added
 - Initialized and destructive beans are added
 - Particular classes config added

````
    @Lazy
    @Bean(initMethod = "init")
    ServiceToPay ServiceInit() {
        return new ServiceToPay("1xbet", 1.5);
    }

    @Lazy
    @Bean(destroyMethod = "destroy")
    ServiceToPay ServiceDestr() { ... }
````

### Lab 3 - AOP ###
 - AspectJ used
 - Pointcut annotation used
 - Advices added (@Before and @AfterReturn)

````
    @Pointcut("within(kz.iitu.itse1908.murzaliev.service.PaymentService)")
    public void payment () {};

    @AfterReturning(pointcut = "execution(public Object kz.iitu.itse1908.murzaliev.service.PaymentService.makePayment())",
            returning = "result")
    public void providerStatus (JoinPoint jp, Object result) { ... }

    @Before("payment()")
    public void testConnection () { ... }
````

### Lab 4 - Spring JDBC ###
 - Datasource configured in application properties
 - Repositories implemented using JdbcTemplate
 - Batch operations are used

````
    @Repository
public class PaymentRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
````

### Lab 5 - Hibernate ###
 - Batch operations are used
 - Hibernate JPA annotations used
 - PostgreSQL database is used

````

    @Bean
    public DataSource dataSource() {
        ...
    }
    
    private Properties hibernateProperties() {
        ...
    }
    
    @Bean
    public SessionFactory sessionFactory() throws IOException {
        ...
    }

````

### Lab 6 - JpaRepositories ###
 - Cache config added
 - Different types of query creation are used
 - NamedQueries are used
 - SpEL used
 - @Query used
 - Transactional annotation is used
 - PostgreSQL database is used

````

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Cacheable("pays")
    @CacheEvict("pays")
    List<Payment> findAll ();

````

### Lab 7 - Transaction management ###
 - @Transactional annotation used
 - Custom validator created
 - Custom converter implemented
 - Custom formatter implemented
 - AssertTrue used

### Lab 8 - Scheduling ###
 - Cron expressions are used
 - Tasks run in parallel
 - Schedule parametrized

First scheduled method adds new payments with fixed delay 2 sec. When new payment added, second scheduled payment checks for payments with status "False" and changes it to status "True".

````

    @Scheduled(cron = "*/1 * * * * *")
    public void scheduledStatusUpdate () {
        ...
    }

````

### Lab 9 - REST ###

- GET, POST, PUT, DELETE, HEAD, OPTIONS Http request handlers implemented.
- @GetMapping, @PostMapping, @Putmapping and @DeleteMapping annotation are used.
- Swagger and swagger UI configured
- File uploading and downloading implemented