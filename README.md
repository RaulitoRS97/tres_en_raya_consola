# Descripcion
Clasico Juego del Tres En Raya.
# Funcionamiento
El juego se lleva a cabo por consola, el usuario a traves de un menu dispone de dos opciones principales, jugar contra un jugador o contra la maquina (La maquina no tiene diferentes niveles de inteligencia, dispone solo de uno, y es el maximo, no se le puede ganar, como mucho empatar).
# Notas
Aunque esta hecho en consola, la interfaz es muy intuitiva y vistosa, hace uso de caracteres ASCII extendido, y usa distintos colores gracias a los codigos de escape ANSI, los cuales por defecto se soportan en la mayoria de consolas de Linux, y en Windows 10, se puede habilitar de manera manual modificando el registro de windows y añadiendo una clave '[HKEY_CURRENT_USER\Console] "VirtualTerminalLevel"=dword:00000001', este programa antes de iniciarse comprueba si dicha clave existe y si tiene valor 1, en caso contrario la escribe por ti.

Importante decir que la ejecucion del programa funcionara correctamente en consola 'cmd' o 'bash', pero en las consolas de los IDE's depende de como esten desarrolladas, por ejemplo en Eclipse 2019-12 no interpreta los codigos ANSI, ademas, a la hora de desarrollar, hay que tener cierto cuidado con la codificacion que le damos a nuestros '.java' debido a que puede reemplazar los caracteres ASCII extendido utilizados.

# Conclusion
Es un programa sencillo, y cuidado, espero que si lo prueban lo disfruten al igual que hecho yo al programarlo.
