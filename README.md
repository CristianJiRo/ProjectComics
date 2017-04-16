# ProjectComics

Utilizando la API de ComicsVine creamos una aplicación para crear una lista de series de TV que queremos/estamos viendo
y mantener el control de que capitulos hemos visto ya.

Tambien tendremos acceso a una pequeña selección de informacion sobre las series.

---------------------------------------------------------------------------------------------------------------------------------------

# Bugs conocidos:

Cuando una busqueda en la base de datos retorna menos de 7 resultados la app crashea.
Da el error:

FATAL EXCEPTION: main java.lang.IllegalArgumentException: Invalid position: X

El X es el numero de resultados encontrados. Creo que es un problema del adapter del cupboard, pero no he encontrado ninguna solución.

Asi que si se abre una serie de la que se descargaron (o tiene) menos de 7 capitulos o en la lista de favoritos se tienen
menos de 7 series añadidas la app se cierra.

---------------------------------------------------------------------------------------------------------------------------------------

Los listview a veces muestran los datos de una serie en una celda incorrecta (normalmente una mas abajo), asi que al abrirla
se abre una serie distinta de la que nosotros veiamos. Normalmente pasa con el primer y ultimo iten que se ve en cada página.

---------------------------------------------------------------------------------------------------------------------------------------