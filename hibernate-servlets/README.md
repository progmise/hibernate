# pokedex-lite

# Tecnologías utilizadas:
Backend: Hibernate 4, Servlets
Frontend: HTML5, CSS3, Javascript, JQuery, Bootstrap 4
Formato de texto: JSON

# Entorno de ejecución:
Pre-requisitos: Apache Tomcat, MySQL y crear una base de datos llamada 'db_pokemons'

1 - Una vez buildeado el proyecto, dirigirse al package com.certant.app.dll
2 - Ejecutar la clase DatabaseGenerator.java, ésta clase se encarga de ingresar registros en la base de datos
3 - Ejecutar el proyecto, con el Apache Tomcat como servidor, o bien, copiar el .war de la carpeta target, y pegarlo en el directorio de Apache Tomcat .\apache-tomcat-x.x.xx\webapps
4 - Ingresar desde cualquier navegador, al siguiente link: http://localhost:8080/pokedex-lite/index.html

Nota: El puerto puede variar, dependiendo la configuración del Apache Tomcat, pero por defecto es 8080


# Pendiente:
1 - Redireccionamiento desde crear.html a mostrar.html, una vez recibido el estado '200' del backend
2 - Corregir sobrecarga de checkboxes de Tipos, cuando se apreta varias veces el botón 'Buscar' de editar.html
3 - Redireccionamiento desde editar.html a mostrar.html, una vez recibido el estado '200' del backend
4 - Corregir sobrecarga de media de Pokemon, cuando se apretar varias veces el botón 'Buscar' de buscar.html
5 - Mostrar medias de Pokemones, al primer click del 'a' de mostrar.html, ya que recién se carga la lista, al segundo click.