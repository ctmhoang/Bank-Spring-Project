# Spring anotation (beans)

- @SpringBootApplication (Boilterplate for Spring web application)
- @Controller (Marked as controller)
- @RestController (Marked as REST controller -return directly values)
- @RequestMapping("Path") ("Mapping to controller to template to display information")
  - Wanna a custom [error page](https://www.baeldung.com/spring-boot-custom-error-page)
  - Return a String -> automatically get a file from templates folder
  - can use "redirect:PATH" to another mapping
  - Can specify the path (value) and the method
    <br>Ex : `@RequestMapping(value ="/signup", method = RequestMethod.GET)`
- @ModelAttribute("variable name") retrieve variable from thymeleaf template page submitted by post
- @Service -> registered as service beans
- CrudRepository Class<Entity , ID> -> Help generate entity method by keyword
- @Autowired (dependency injection) -> do not need to init
- Model .addAttribute
- @Transactional -> transaction method -> roll back provided by spring and hibernate with database provided
- @Repository fullfill DAO
- entityManager.persist()
- @RequestParam
- JSESSION -> spring create and sent by tomcat or jetty service inorder to keep the session -> set by browser -> cuz http only cannot be retrive by js when intercept
- @Order
-

# Thymeleaf Syntax

- declare in propety in html tags xmlns:th="http://www.w3.org/1999/xhtml"
- th:fragment="name" -> register block between tags as a fragment and can be reused in other html files
- th:href="@{application path}" -> anchor tags in thyme leaf template -> to render tag from html
- th:replace="relative path :: name of fragment"
- th:if(check if true) -> display
- th:action="@{PATH}" -> where to post to
- th:value="{value}" -> binding to variable specifies initial value -> name must have value sames as model field
  <br> -> or thymeleaf page will fall back to white label error page
- data-th-each = "variableName : List"
- data-th-text = "" to replace value for html tags
- th-text [for form](https://stackoverflow.com/questions/62098700/whats-the-difference-between-data-th-text-and-thtext-in-spring-thymeleaf)

# Hibernate (Persistence Layer)

- based on JPA specs
- @Entity -> in SQL -> Need Primary Key
- @Id -> Indicate Primary Key
  - [GeneratedValue](https://www.objectdb.com/java/jpa/entity/generated)
- Relationship
  - @ManyToOne
  - cascade
  - [mappedBy](https://www.baeldung.com/jpa-joincolumn-vs-mappedby)
    <br> Association
  - fetch
- [@JoinColum](https://www.baeldung.com/jpa-join-column) Own
- @JsonIgnore
- Some configs for hibernate view at application config file

# Spring security

- @Configuration -> Class level -> configuration class
- @EnableWebSecurity -> Enable Spring security
- Abtract class WebSecurityConfigurerAdapter -> Make it as an bean adapter for web security
  - Rewite configure method
- Enviroment
- SecureRandom
- GrantedAuthority
- need create authority roles and role with user
- Interface UserDetailService -> Load the user specific data -> Need UserDetailService Provider and Password Encoder in Authentication ManageBuild -> Need UserDetails extend Object
- LoadUserByUsername(String name must be same as in thymeleaf login form)
- LOG in slf4
- [Principal class](https://stackoverflow.com/questions/37499307/whats-the-principal-in-spring-security)
- Need to add token if enable crfs
- @EnableGlobalMethodSecurity(prePostEnabled = true) -> give us ability to pre authorlize
- @PreAuthorize("hasRole('ADMIN')") -> use hasRole will automatically add `ROLE_` prefix as class level or method level

# Angular

- Not change stick with it for now (Ver 1.0.0)
- Inorder to communicate between Angular and the server (Spring) <br>
  -> need to send a get a credential by post method in angular `'Content-Type': 'application/x-www-form-urlencoded'` <br>
  -> but angular use options method (called pre-filght method) make sure spring boot allow to send credential -> but we disable cfrs in config -> need to filter the call from angular server to bypass it.
- `|` filter format to another formate
- `:param` pass param to path can get with activeRoute class
