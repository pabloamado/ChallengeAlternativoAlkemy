# ChallengeAlternativoAlkemy
Desafio alternativo de Alkemy 2021
Esta es una aplicacion construida particulamente con el framework spring. Se ha utilizado la arquitectura Rest en el uso del protocolo Http.
Se ha adjuntado un archivo de pruebas de endpoint que pueden importarse a postman.com.
Se ha utilizado JPA para la persistencia de entidades y el patron dto para la transferencia de informacion.
Tambien se ha implementado Spring Security personalizado para poder registrar y hacer un login, para devolver un jwt (jason web token),
necesario para hacer uso de los distintos endpoints.
Para el envio de mail  se utilizo Sendgrid. Necesitaran gestionar una api key de sendgrid.com e ingresarla en el mailservice en el paquete service para hacer uso
de la misma.
