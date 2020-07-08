# Spring anotation (beans)
* @SpringBootApplication (Boilterplate for Spring web application)
* @Controller (Marked as controller)
* @RestController (Marked as REST controller -return directly values)
* @RequestMapping("Path") ("Mapping to controller to template to display information")
  * Wanna a custom [error page](https://www.baeldung.com/spring-boot-custom-error-page)
  * Return a String -> automatically get a file from templates folder
  * can use "redirect:PATH" to another mapping 
  * Can specify the path (value) and the method 
   <br>Ex : ` @RequestMapping(value ="/signup", method = RequestMethod.GET)`
* @ModelAttribute("variable name") retrieve variable from thymeleaf template page submitted by post




# Thymeleaf Syntax
  * declare in propety in html tags xmlns:th="http://www.w3.org/1999/xhtml"
  * th:fragment="name" -> register block between tags as a fragment and can be reused in other html files
  * th:href="@{application path}" -> anchor tags in thyme leaf template -> to render tag from html
  * th:replace="relative path :: name of fragment"
  * th:if(check if true) -> display
  * th:action="@{PATH}" -> where to post to
  * th:value="{value}" -> binding to variable specifies initial value -> name must have value sames as model field 
  <br> -> or thymeleaf page will fall back to white label error page
  