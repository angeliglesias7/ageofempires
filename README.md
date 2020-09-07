# Age of Empires

El proyecto consiste en el desarrollo de una mini versión del juego Age of Empires para una interfaz de texto. La interacción entre el jugador y el juego se realizará a través de acciones pasadas a través de la consola del juego.

Por ejemplo, comandos como mover a un paisano, recolectar fruta o construir un edificio formarán parte del paquete básico de comandos a implementar. El objetivo básico del juego será construir nuestra civilización y derrotar a la/s civilizaciones enemigas del mapa.

Instrucciones básicas:

Cada celda del mapa será de un determinado tipo. Las celdas podrán ser de uno de los siguientes tipos: bosque, cantera, arbusto, y pradera. De los tipos anteriores, únicamente las praderas son transitables.
Alguno de los tipos de celdas pueden contener recursos.
El juego dispondrá de tres tipos de recursos: madera, piedra, y alimentos.
Se podrán crear varios tipos de edificios: ciudadelas, casas, y cuarteles. Cada edificio ocupará una única celda del mapa.
Cada edificio tendrá una propiedad que representa los puntos de salud del edificio, el cual es distinto en función del tipo de edificio. Cuando un edificio tiene 0 puntos de salud, se considera destruido y ya no se podrá reparar.
Cada edificio tendrá un coste de reparación en recursos (piedra y madera) en función de los puntos de salud a recuperar.

El juego dispondrá de dos tipos de personajes: paisanos y soldados. Los personajes tendrán ciertas propiedades que definirán su salud, armadura, y ataque.
Los paisanos pueden recolectar madera de los bosques, piedra de las canteras, y comida de los arbustos, podrán recolectar cuando estén en celdas adyacentes a un determinado recurso y tienen una capacidad de recolección de recurso que se restará del correspondiente contenedor de recurso de la celda (pej. bosque).

Para recuperar su capacidad de recolección, el paisano tiene que llevar los recursos a la ciudadela, donde se almacenarán. La recolección de recursos es instantánea.

Únicamente los paisanos podrán construir edificios.
Cada edificio tendrá unos requisitos mínimos de madera y piedra para poder ser construido.
Los edificios solo se podrán construir si las celdas destino son transitables.

Únicamente los paisanos podrán reparar edificios.

● La ciudadela permite crear nuevos paisanos. Para crear un paisano se necesita una cierta cantidad de comida. Solo se podrán crear ciudadanos si existe espacio para alojarlos.
● La creación de personajes y la construcción y reparación de edificios es instantánea.

● Los cuarteles permiten crear nuevos soldados. Se necesita una cierta cantidad de comida para crear un soldado, mayor que la necesaria para crear un paisano.

El jugador únicamente podrá ver las celdas del mapa por el que haya transitado alguno de sus personajes y las celdas adyacentes.
Los soldados y paisanos podrán atacar a personajes y edificios enemigos cuando estén en celdas adyacentes. El daño generado dependerá del daño de ataque del personaje y de la defensa del enemigo.

Al inicio del juego, el jugador dispondrá de una ciudadela y de un paisano, y de un conjunto de recursos almacenados en la ciudadela.
