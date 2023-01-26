# APIRest-Tienda
Para el desarrollo se utilizó: ● Java 11 - Maven ● Spring Boot ● Base de datos en memoria (H2) ● Lombok ● Apache commons-lang3 ● SLF4J (Simple Logging Facade for Java)
● Postman (para el testeo de los endpoints)

El sistema registra proveedores, clientes, productos, compra y ventas realizadas:
- * Consulta el listado de todos ellos, da de alta/baja(conservando su estado previo,recuperando sus datos guardados anteriormente),los edita y también guarda.
- * Consulta un listado con los productos que tienen un stock bajo (no es un valor fijo).
- * Consulta las ventas por día y las compras realizadas a determinados proveedores.
- * Consulta un API Rest externa (alojada en un servidor de Mockup APis) y retorna una lista de productos transformando el formato de cada producto.
