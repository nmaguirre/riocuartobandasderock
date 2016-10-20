#Responsabilidades de desarrolladores


Documentación correspondiente a las responsabilidades de los desarrolladores según en que están trabajando.
Este documento también puede incluir especificaciones muy básicas de partes del sistema.


**Los desarrolladores se pueden dividir para trabajar con controladores, modelos, o dao.**

Las responsabilidades de quienes trabajen con controladores son:

- Definir métodos necesarios en el controlador correspondiente, en principio los métodos necesarios para responder las distintas consultas generadas desde la aplicación web.
- Si se requiere interactuar con persistencia se debe utilizar el método correspondiente en el/los DAO correspondiente/s pasándole los argumentos necesarios luego de un chequeo "liviano" como revisar valores nulos (no se pasan objetos RESPONSE ni REQUEST a un DAO).
- Definir la o las rutas correspondientes para Spark para las distintas consultas que se pueden realizar desde la aplicación web.
- Unir las rutas Spark con los métodos necesarios de los controladores.
- Implementar los pasos requeridos por los escenarios gherkin.

Las responsabilidades de quienes trabajen con modelos son:

- Definir el modelo agregando las anotaciones necesarias para hibernate
- Definir las tablas necesarias en la base de datos
- Escribir definiciones auxiliares en la base de datos

Las responsabilidades de quienes trabajan con dao son:

- Ofrecer como mínimo métodos:
  - iniciar/cerrar sesión
  - hacer persistente a una instancia del modelo correspondiente al DAO actual
  - obtener de la capa de persistencia todos los elementos del modelo correspondiente al DAO actual
- Los métodos no deben usar ninguna clase de Spark (como response o request)

Las siguientes tareas pueden ser realizadas por cualquiera:

- Escribir un escenario gherkin (considerando que la funcionalidad que se define en el escenario fue previamente acordada por todo el equipo)
- Escribir tests junit

**Todos tienen la responsabilidad de escribir la documentación correspondiente como escribir Javadoc en caso de trabajar con java.**
