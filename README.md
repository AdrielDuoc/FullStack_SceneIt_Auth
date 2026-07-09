# SceneIt
SceneIt es un proyecto de una "Watchlist" que consiste en una app que permita a los usuarios guardar Peliculas que quieran ver en un futuro.

La finalidad de esta app es que los usuarios que ocupen la app puedan organizarce a la hora de querer ver peliculas que tengan pendiente.

---
>[!NOTE]
>
>Para ejecutar esta aplicacion se recomienda el uso de las siguientes aplicaciones que nos permitiran correr el proyecto sin ningun problema.
>
>---
>[HeidiSQL]
>
>HeidiSQL es una base de datos, en el cual almacenaremos todos los datos atraves de un POST.
>
>[DESCARGA HEIDISQL](https://heidisql.com/download.php)
>
>---
>
>[Laragon]
>
>Laragon es una herramienta que sera utilizada para la conexion de la base de datos ocupando el sistema de MySQL.
>
>[DESCARGA LARAGON](https://laragon.org/download)
>
>---
>
>[Postman]
>
>Postman sera utilizada para utilizar los metodos https(GET, POST, PUT y DELETE) para interactuar con la base de datos.
>
>[DESCARGA POSTMAN](https://www.postman.com/downloads)
>
>---
---

## Indice
 1. [Estructura del Proyecto](#estructura-del-proyecto)
     * 1.1. [Dependencias](#dependencias)
     * 1.2. [Packages](#packages)
 2. [APIs](#apis)

---
# Estructura del Proyecto

Para comenzar con el proyecto se utilizaron las siguientes **dependencias** que nos permiten optimizar el codigo o tambien **realizar** la **conexion** con otras **herramientas**, **APIs**, ect.

## Dependencias

### Lombok

Lombok nos permite generar automaticamente todos los **metodos, Getters, Setters y constructores** de los [modelos](#model) del poyecto atraves de la anotacion **@Data, @AllArgsConstructor y @NoArgsConstructor**.

### DataJPA

DataJPA sirve para declarar nuestros [modelos](#model) como **entidades de una Base de Datos** para usarla se necesita usar la anotacion **@Data** y para declarar el modelo como entidad se usa la anotacion **@Entity** y luego la anotacion **@Table** que nos permite asignarle un nombre para el modelo en nuestra base de datos.

### Validation

Validation la utilizamos para declarar algunas caracteristicas de nuestros [modelos](#model) como tipo Id con la anotacion @Id.

Tambien la usamos para agregarles validaciones a las caracteristicas como por ejemplo hacer que sean **NotNull** o **NotBlank** con las anotaciones **@NotNull y @NotBlank**.

### WebMVC

WebMVC es una dependencia que se utiliza dentro del package de [controller]() con la anotacion **@RestController** esta nos permite hacer el **puente entre el Back-End y el Front-End**.

Esta libreria se utiliza para crear el EndPoint del controller con la anotacion **@RequestMapping** y tambien nos permite hacer los llamados **HTTP(GET, POST, PUT y DELATE)** con las anotaciones **@GetMapping, @PostMapping, @PutMapping y @DeleteMapping**.

Utilizaremos las anotaciones **@Valid** que su funcion es validar los argumentos que se ingresan dentro del metodo, **@RequestBody** que se utiliza para recibir un objeto **JSON** entero para convertirlo en un objeto y **@PathVariabe** que se utiliza para atrapar datos que vienen en una **URL** como el **Id** del modelo.

### WebFlux

WebFlux es una dependencia que nos permite hacer conexiones con **APIs** externas.

### MySQL Connector

MySQL Connector es la libreria que nos permite conectarnos a nuestra Base de Datos MySQL.

---

## Packages

Dentro del codigo del proyecto hay en total de 8 packages(Archivos) los cuales tenemos;

- [model](#model)
- [repository](#repository)
- [service](#service)
- [controller](#controller)
- [dto](#dto)
- [exception](#exception)
- [config](#config)
- [application.properties](#applicationproperties)


### Model
Para ir en orden comenzaremos con el **Package** de **model** en cual declaramos las entidades o modelos que consideramos mas importante.

Cada modelo tiene tambien sus caracteristicas como se ven a continuacion;

>---
>[Usuario]
> - id_usuario
> - nombre_usuario
> - apellido_usuario
> - email_usuario
> - contraseña_usuario
> - fecha_creacion_cuenta_usuario
> - edad_usuario
>
>---
>
> [Autor]
> - id_autor
> - nombre_autor
> - apellido_autor
> - edad_autor
>
>---
>
> [Productora]
> - id_productora
> - nombre_productora
>
>---
>
> [Pelicula]
> - id_pelicula
> - titulo_pelicula
> - autor
> - descripcion
> - genero_pelicula
> - duracion
> - fecha_estreno_pelicula
> - productora
>
>---
>
> [Watchlist]
> - id_watchlist
> - pelicula
> - usuario
> - tamano_watchlist
>---

### Repository

En el package solamente colocamos la anotacion **@Repository** y hacemos un **extends JpaRepository<Modelo, Integer>** que lo que hace es crear de forma automatica todos los metodos necesarios para mas adelante como **.findAll(), .save(), .findById(), ect** .

```java
@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> { }
```

---

### Service

El package service colocamos la anotacion **@Service** donde se crean los metodos que se utilizaran en controller, en ella utilizaremos los mismo metodos creados en el package repository utilizando la anotacion **@Autowired** para utilizar los metodos.

```java
    @Autowired
    private AutorRepository autorRepository;
```

Dentro del proyecto creamos principalmente 5 metodos que utilizaremos para cada service estos son;

---

#### **Obtener todos los datos**

```java
   public List<Autor> getAutor(){
       return autorRepository.findAll();
  }
```
---
#### **Guardar datos**

```java
   public Autor saveAutor(Autor autor){
       return autorRepository.save(autor);
    }
```
---
#### **Obtener dato por id**

```java
   public Autor getAutorId(Integer id){
       return autorRepository.findById(id).orElse(null);
    }
```
---
#### **Actualizar datos**

```java
   public Autor updateAutor(Autor autor){
       if(!autorRepository.existsById(autor.getId_autor())){
              return null;
       }
       return autorRepository.save(autor);
   }
```
---
#### **Borrar datos**

```java
   public void deleteAutor(Integer id){
       autorRepository.deleteById(id);
   }
```
---

### Controller

En el package controller creamos la conexion del Back-End y el Front-End para ello colocamos dentro la anotacion **@RestController** y **@RequestMapping("/api/v1/modelo")**.

```java
@RestController
@RequestMapping("api/v1/autores")
public class AutorController {
```

Tambien hacemos la conexion con el package [service](#service) y utilizar los metodos creados en service.

```java
    @Autowired
    private AutorService autorService;
```
---
En el controller construimos los metodos HTTP

#### Obtener todo
```java
    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores(){
        System.out.println("[AutorController] -> getAllAutores");
        return ResponseEntity.ok(autorService.getAutor());
    }
```
---
#### Obtener por id
```java
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getUsuarioId(@PathVariable Integer id){
        System.out.println("[AutorController] -> getUsuarioId id=" + id);
        Autor autor = autorService.getAutorId(id);
        if(autor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }
```
---
#### Guardar
```java
    @PostMapping
    public ResponseEntity<Autor> saveAutor(@Valid @RequestBody Autor autor){
        System.out.println("[AutorController] -> saveAutor");
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.saveAutor(autor));
    }
```
---
#### Actualizar
```java
    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Integer id, @Valid @RequestBody Autor autor){
        System.out.println("[AutorController] -> updateAutor id=" + id);
        autor.setId_autor(id);
        Autor actualizado = autorService.updateAutor(autor);
        if(actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
```
---
#### Borrar
```java
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAutor(@PathVariable Integer id){
        System.out.println("[AutorController] -> deleteAutor id=" + id);
        autorService.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }
```
---

El proyecto tiene un total de 6 End Points para acceder a sus metodos HTTP estas son:

#### End Points

 **Controller Autor:** `http://localhost:8080/api/v1/autores`

 **Controller Pelicula:** `http://localhost:8080/api/v1/peliculas`

 **Controller Productora:** `http://localhost:8080/api/v1/productoras`

 **Controller Usuario:** `http://localhost:8080/api/v1/usuarios`

 **Controller Watchlist:** `http://localhost:8080/api/v1/watchlist`

 **Controller Weather(API):** `http://localhost:8080/api/v1/clima`
 
---

### DTO

La funcionalidad de el package **DTO** es agarrar caracteristicas de otros modelos con la finalidad de tener varias caracteristicas de dos modelos en un solo **"modelo"**.

```java
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PeliculaDTO {

    private String titulo_pelicula;
    private String genero_pelicula;
    private String nombre_productora;

}
```

Despues de crear el DTO creamos el metodo para llamar al DTO dentro del package service del que estemos haciendo un DTO.

```java
    public List<PeliculaDTO> getPeliculaDTO(){
        return peliculaRepository.findAll().stream()
        .map(p -> new PeliculaDTO(
            p.getTitulo_pelicula(),
            p.getGenero_pelicula(),
            p.getProductora().getNombre_productora()
        ))
        .toList();
    }
```

Finalmente crearemos su metodo HTTP dentro del package controller para llamar al DTO.

```java
    @GetMapping("/por-genero")
    public ResponseEntity<List<PeliculaDTO>> PeliculasPorGenero(){
        System.out.println("[PeliculaController] -> PeliculasPorGenero");
        return ResponseEntity.ok(peliculaService.getPeliculaDTO());
```
---

### Exception

En este package es practicamente nuestra red de seguridad lo que hace es mostrar los errores que pueden haber a la hora de interactuar con la base de datos pero de una forma mucho mas entendible,
con la anotacion **@RestControllerAdvice** captura el error y los transforma en estas tres situaciones.

---
#### Error 400 Bad Request

En el caso de que la anotacion **@Valid** en los metodos del controller falla la anotacion antes mencionada toma ese error y lo transforma a un error mas amigable para entender.



```java
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {

        // Recorre todos los campos que fallaron la validación y arma un mensaje
        StringBuilder detalle = new StringBuilder();
        for (FieldError campo : ex.getBindingResult().getFieldErrors()) {
            detalle.append(campo.getField())           // nombre del campo (ej: "nombre")
                   .append(": ")
                   .append(campo.getDefaultMessage())  // mensaje de la anotación (ej: "no debe estar vacío")
                   .append(", ");
        }

        ApiError error = new ApiError(400, "Error de validación", detalle.toString());
        return ResponseEntity.badRequest().body(error);
    }
```
---
#### Error 500 Internal Server Error

En este caso sigue la misma logica por detras que la del **error 400** solo que este error se fija en problemas como que la base de datos de caiga o que falle la conexion.

```java
    // Maneja cualquier otra excepción no esperada → 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericError(Exception ex) {
        ApiError error = new ApiError(500, "Error interno del servidor", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
```
---
### Config

Este package tiene como funcion configurar servicios externos en este caso sera utilizada para configurar la **API Open-Meteo** la cual se explicara mas a fondo un poco mas adelante.

```java
@Configuration
public class WebClientConfig {

    @Value("${openmeteo.base-url}")
    private String openMeteoBaseUrl;

    @Bean
    public WebClient weatherWebClient() {
        return WebClient.builder()
                .baseUrl(openMeteoBaseUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }

}
```

La funcion de este codigo comienza con la anotacion **@Configuration** declara que este codigo es de tipo configuracion para construir la app, luego la linea **@Value("${openmeteo.base-url}")** hace un llamado
para que busque una archivo de texto dentro de application.properties que seria la **URL** de la **API Open-Meteo** y guarda en la variable **openMeteoBaseUrl** y finalmente se construye el metodo de configuracion
de la **API**, en este caso solo pide que **devuelva los datos que te da la API en formato JSON**.

---

## Application.properties

En el package **application.properties** que vendria a ser la configuracion principal de nuestro proyecto es esta podemos ver el codigo para realizar la conexion con **MySQL**.

```java
# Conexion a MySQL local
spring.datasource.url=jdbc:mysql://localhost:3307/FullStack_SceneIt?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
Tambien podemos ver la configuracion de **Hibernate/JPA**.

```java
# Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
Y finalmente la configuracion de la **API Open-Meteo**.

```java
# Open-Meteo API (clima, sin API key)
openmeteo.base-url=https://api.open-meteo.com
```

## APIs

Ahora explicaremos la API que utilizaremos en este caso por ahora solmente utilizaremos la API llamada Open-Meteo,
esta API lo que hace es que muestra informacion del clima.

Lo que tendremos que hacer primero es configurar la API en el package [application.properties](#applicationproperties).

```java
# Open-Meteo API (clima, sin API key)
openmeteo.base-url=https://api.open-meteo.com
```

---

Luego de configurar su URL en el package [config](#config) esta vez configuramos la API para que los datos que se devuelven sean de tipo **JSON**.

```java
    @Value("${openmeteo.base-url}")
    private String openMeteoBaseUrl;

    @Bean
    public WebClient weatherWebClient() {
        return WebClient.builder()
                .baseUrl(openMeteoBaseUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }
```

---

Despues creamos un [DTO](#dto) para que solo muestre la informacion que nos interese.

```java
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentWeather {
        private Double temperature;
        private Double windspeed;
    }
```
---
Finalmente creamos el **controller** de la API junto a su end point en el package [controller](#controller).

```java
@Controller
@RequestMapping("/api/v1/clima")
public class WeatherController {

@Autowired
private WeatherService weatherService;

@GetMapping
public ResponseEntity<WeatherDTO> clima(
    @RequestParam(defaultValue = "-33.45") double lat,
    @RequestParam(defaultValue =  "70.65") double lon){

        System.out.println("[WeatherController] -> clima lat =" + lat + ", lon =" + lon);
        WeatherDTO resultado = weatherService.obtenerClima(lat, lon);
        return ResponseEntity.ok(resultado);
    }
    
}
```
---
 ## **autores**
 - [Javier Fuentealba](https://github.com/Javier9897)
 - [Adriel Yañez](https://github.com/AdrielDuoc)
