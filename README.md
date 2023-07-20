# TiendaBackend

Para la base de datos se utilizó MYSQL

usuario: root
password: (vacio)

Primero se crea la base de datos

CREATE DATABASE tienda;

USE tienda;

Corremos el código fuente backend en Eclipse con Springboot, luego de eso el creará automáticamente  las tablas que se usaran para este proyecto

Y debemos insertar los siguientes productos

INSERT INTO `productos` (`id`, `nombre`, `precio`, `cantidad`, `imagen_url`) VALUES (NULL, 'Lapiceros', '3.45', '40', 'https://www.hanjigifts.com/cdn/shop/products/ScreenShot2020-10-14at1.39.03PM_300x300.png'), (NULL, 'Cuadernos', '4.59', '30', 'https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-13at3.21.52PM_1024x1024@2x.png'), (NULL, 'Borradores', '1.99', '10', 'https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-12at3.00.37PM_1024x1024@2x.png'), (NULL, 'Marcadores', '9.55', '50', 'https://www.hanjigifts.com/cdn/shop/products/ScreenShot2021-11-24at12.01.13AM_1024x1024@2x.png'), (NULL, 'Cinta', '2.99', '20', 'https://www.hanjigifts.com/cdn/shop/products/c92eca1c-a08a-4c0e-a62e-88aa1bc35ab5_1024x1024@2x.png'), (NULL, 'Tarjetas', '6.99', '0', 'https://www.hanjigifts.com/cdn/shop/products/Notecard-AMillionTimesThankYou_2_1024x1024@2x.jpg')