# API ENJOY PADEL
## Se trata de una aplicación que permite gestionar partidos de padel y cuyo modelo de datos consta de 3 clases relacionadas ente sí
### - Diseña la API y escribe el fichero OpenAPI 3.0 de la API. Incluye, al menos, los casos de éxito (20X), los 404 y los 500.
#### Tanto los casos de éxito 20X cómo los errores 400, 404 y 500 están controlados
### - Diseña una API Virtual de forma que existan, al menos, 2 Casos de Uso para cada operación (uno de OK y otro para KO).
#### La API virtual tiene 2 casos de uso para cada operación. Link API virtual: https://github.com/raulcoroe/virtual-enjoypadelapi
### - Prepara una colección Postman de prueba para la API diseñada y otra que permita probar todos los Casos de Uso de la API virtual
#### La aplicación está probada mediante una colección Postman que se adjuntará. También se realiza una colección de la APi virtual
### - Diseña, al menos, 3 operaciones para que funcionen de forma reactiva con WebFlux.
#### Se realiza una API con WebFlux en la que se encuentran las mismas operaciones que en la API no reactiva. Link de la API con WebFlux: https://github.com/raulcoroe/enjoypadelAPI-react-2EV
### - Ajusta el desarrollo de tu proyecto para que cumpla todas las decisiones de diseño adoptadas en los puntos anteriores
#### Se ajusta el diseño de la API de la 1ª EV para que cumpla con los requisitos de la 2ª EV
### - Parametriza ambas colecciones Postman de forma que sea fácil cambiar el host, puerto o basePath de la API
#### Se parametriza la colección Postman para modificar de forma rápida el host y puerto
### - Añade en el diseño de tu API casos de fallo para el código de estado 400 (Bad Request)
#### Se controlan los errores 400 de la API.
### - Añade al fichero de especificación de la API (OpenAPI 3.0) un par de ejemplos para cada operación
#### Se añaden 2 ejemplos para cada operación en el fichero Swagger
### - Utiliza las herramientas Git y GitHub durante todo el desarrollo de la aplicación. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo.
#### Se utiliza GitHub a lo largo de la ejecución del proyecto. Link de la API: https://github.com/raulcoroe/enjoypadelAPI-2EV
### - Añade 2 operaciones más donde se haga uso de DTOs y se opere con datos de más de una clase utilizando la librería Spring Validation y ModelMapper para implementarlas
#### Se realiza la operación de añadir partido con DTO y se opera con los datos usando Spring Validation
### - Configura el proyecto para que el log recoja cualquier traza de aplicación tanto para los casos OK como para los KO (incluyendo la traza de la excepción)
#### El log de la aplicación hace un seguimiento tanto de los métodos de la API como de los errores y sus trazas.
### - Añade una operación PATCH para cada clase (donde se controlen todos los posibles errores)
#### La API cuenta con operaciones PATCH en todas sus clases
### - Utiliza la librería Spring Validation para validar el formato de la información que venga como entrada en cada operación
#### Utilización de la librería Spring Validation para validar el formato de entrada en cada operación
