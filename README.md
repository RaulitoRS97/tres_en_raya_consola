# Descripción
Clásico Juego del *Tres En Raya*.
# Funcionamiento
El juego se lleva a cabo por **consola**, el usuario a través de un menú dispone de dos opciones principales, jugar contra un jugador o contra la máquina (La máquina no tiene diferentes niveles de inteligencia, dispone solo de uno, y es el máximo, no se le puede ganar, como mucho empatar, independientemente de que se empiece primero o no).
# Capturas
### 1. Portada inicial al ejecutar el programa:
![Captura portada](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaPortada.png)
### 2. Menú con las diferentes opciones:
![Captura menú](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaMenu.png)  
### 3. Tablero en mitad de la partida:
![Captura mitad partida](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaMitadPartida.png)  
### 4. Tablero al finalizar la partida:
![Captura final partida](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaFinalPartida.png)  
# Notas
Aunque está hecho en consola, la **interfaz es muy intuitiva y vistosa**, hace uso de caracteres **ASCII extendido**, y usa distintos colores gracias a los **códigos de escape ANSI**, los cuales por defecto se soportan en la mayoría de consolas de **Linux**, y en **Windows 10**, se puede habilitar de manera manual modificando el registro de Windows y añadiendo una clave *[HKEY_CURRENT_USER\Console] "VirtualTerminalLevel"=dword:00000001*, este programa antes de iniciarse comprueba si dicha clave existe y si tiene valor *1*, en caso contrario la escribe por ti (este proceso se realiza en la clase Lanzadera).

Importante decir que la ejecución del programa funcionara correctamente en consola *cmd* o *bash*, pero en las consolas de los *IDE's* depende de como estén desarrolladas, por ejemplo en *Eclipse 2019-12* no interpreta los **códigos ANSI**, además, a la hora de desarrollar, hay que tener cierto cuidado con la codificación que le damos a nuestros *.java* debido a que puede reemplazar los caracteres **ASCII extendido** utilizados. Destacar que según la **distribución de Linux**, y la consola que se utilice, funcionara correctamente la **Lanzadera** o no, en este caso con *bash* no da ningún problema.
