# Sistema Gestor de Remisería

Sistema desarrollado en Java para gestionar una remisería, permitiendo administrar choferes, autos y viajes.

## Descripción

Esta aplicación permite gestionar:
- Choferes (con o sin auto propio)
- Autos de la remisería
- Viajes realizados

## Base de datos

- El script de base de datos se encuentra en db/db.sql

## Tecnologías utilizadas

- MySQL
  - Conector de mysql en la carpeta lib/ 
- Java 17  

## Aspectos tecnicos

- Login del sistema
  - usuario: invitado
  - contraseña: 123

- Configuracion de base de datos
  - Se encuentra en el archivo src/DAO/DbConn 

````java
    // src/DAO/DbConn 
    private final String db = "app_gestor_remiseria_avanzada"; // Aca va el nombre de tu base de datos
    private final String url = "jdbc:mysql://localhost:3306/"+db;
    private final String user = "root";
    private final String password = "";
````

## Flujo de programa

- Crear autos (es obligatorio para testear los choferes que no tienen auto propio)

- Crear choferes (con auto propio y sin), tener en cuenta que para crear un chofer sin auto (o sea, que debe alquilar), es obligatorio seleccionar un auto
  - Al asignar un auto al chofer sin auto propio, se actualizara el estado del auto a no disponible.
  - En caso de editar el chofer (ahora tiene auto propio) o eliminar el chofer, el estado del auto pasara a ser disponible

- Crear viajes
    - Si el estado del viaje se encuentra 'en curso' se actualizara el estado del chofer a no disponible
    - Una vez el estado pase a 'finalizado', el estado del chofer volvera a ser disponible
    - Al finalizar el viaje (estado 'finalizado') los kilometros se sumaran al auto del chofer (en caso de que sea auto de la remiseria)

- Consultar sueldos semanales
  - Clickear en el boton 'cerrar semana', esto mostrara la informarcion de cada chofer que realizo viajes dentro de los ultimos 7 dias al momento de dar click.
  - En la columna semana es lo que deberia cobrar el chofer.

- Consultar balance mensual
  - Muestra actualizado el balance mensual en el momento de consultar.
