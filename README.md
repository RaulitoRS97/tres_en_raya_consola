# Descripción
Clásico Juego del *Tres En Raya*.
# Funcionamiento
El juego se lleva a cabo por **consola**, el usuario a través de un menú dispone de dos opciones principales, jugar contra un jugador o contra la máquina (La máquina no tiene diferentes niveles de inteligencia, dispone solo de uno, y es el máximo, no se le puede ganar, como mucho empatar, independientemente de que se empiece primero o no).
# Notas
Aunque está hecho en consola, la **interfaz es muy intuitiva y vistosa**, hace uso de caracteres **ASCII extendido**, y usa distintos colores gracias a los **códigos de escape ANSI**, los cuales por defecto se soportan en la mayoría de consolas de **Linux**, y en **Windows 10**, se puede habilitar de manera manual modificando el registro de Windows y añadiendo una clave *[HKEY_CURRENT_USER\Console] "VirtualTerminalLevel"=dword:00000001*, este programa antes de iniciarse comprueba si dicha clave existe y si tiene valor *1*, en caso contrario la escribe por ti (este proceso se realiza en la clase Lanzadera).

Importante decir que la ejecución del programa funcionara correctamente en consola *cmd* o *bash*, pero en las consolas de los *IDE's* depende de como estén desarrolladas, por ejemplo en *Eclipse 2019-12* no interpreta los **códigos ANSI**, además, a la hora de desarrollar, hay que tener cierto cuidado con la codificación que le damos a nuestros *.java* debido a que puede reemplazar los caracteres **ASCII extendido** utilizados. Destacar que según la **distribución de Linux**, y la consola que se utilice, funcionara correctamente la **Lanzadera** o no, en este caso con *bash* no da ningún problema.
# Obtención del JAR
Voy a mostrar una forma fiable y simple de obtener el **JAR** en **Linux**, para esto necesitaremos como es obvio tener *JAVA* instalado:
1. Accedemos a la *bash*, y desde ahí nos posicionamos en la carpeta *src* donde se encuentran los *.java* de este proyecto.
2. Ejecutamos el siguiente **comando** para **eliminar** la primera línea de los archivos *.java* donde hace referencia a un **paquete**, **`sed -i '1d' *.java`** (Se puede hacer de forma manual, abriendo cada fichero y borrando esa línea que contiene dicha referencia)
3. Ejecutamos el siguiente **comando** para **compilar** y obtener los *.class* de los archivos *.java*, importante indicar que la **codificación** debe ser en *UTF8*, **`javac -encoding "UTF8" *.java`**
4. Por último, pasamos a la **creación final del JAR**:
   1. Creamos un archivo de texto denominado *manifest.txt*, el cual tiene que tener el siguiente contenido: ***Main-Class: Lanzadera*** y un **Enter** (dejando una línea vacía al final). (**Importante** este fichero, ya que será el manifiesto de nuestro JAR e indica la clase principal por donde inicia el flujo de ejecución)
   2. Una vez tenemos los *.class* y el *manifest.txt*, pasamos a ejecutar el siguiente **comando**, **`jar -cvmf manifes.txt TresEnRaya.jar *.class`** (De esta forma creamos el JAR indicando el manifiesto, como se va a llamar el JAR y cuáles son los '.class')(Importante poner ese nombre al JAR o dará errores al lanzarse el programa)
5. Si no hemos realizado ningún paso mal, deberíamos tener un **JAR** el cual se debería llamar ***TresEnRaya.jar***, y para ejecutarlo realizamos el siguiente **comando**, **`java -jar TresEnRaya.jar`**.
# Capturas
### 1. Portada inicial al ejecutar el programa:
![Captura portada](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaPortada.png)
### 2. Menú con las diferentes opciones:
![Captura menú](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaMenu.png)  
### 3. Tablero en mitad de la partida:
![Captura mitad partida](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaMitadPartida.png)  
### 4. Tablero al finalizar la partida:
![Captura final partida](https://raw.githubusercontent.com/RaulitoRS97/tres_en_raya_consola/master/capturas/CapturaFinalPartida.png)  
