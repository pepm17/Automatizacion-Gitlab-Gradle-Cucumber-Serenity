@Gitlab
Feature: FUNCIONALIDAD - Crear proyecto publico en GitLab
Como un usuario pepm17
Quiero crear un proyecto publico
Para practicar con cucumber y Gherking
		
@tag
Scenario Outline: PRUEBA A REALIZAR - Crear proyecto publico desde GitLab
Given Que el usuario abre el navegador en la pagina de GitLab
And User login with the following <Username> and <Password>
When User create project with <NombreProyecto> and description <DescripcionProyecto>
Then User created project <NombreProyecto>

Examples: 
| Username | Password |     NombreProyecto      | DescripcionProyecto |
 ##@externaldata@./src/test/resources/filesexcel/Datos.xlsx@Datos
   |pepm17   |Pupo0217   |ProyectoEjemploCucumber   |PruebaDelProyecto|
