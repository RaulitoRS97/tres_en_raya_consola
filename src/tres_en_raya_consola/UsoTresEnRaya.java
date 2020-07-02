package tres_en_raya_consola;
//Importamos los siguientes paquetes:
import java.io.IOException;
import java.util.Scanner;
//Clase principal:
public class UsoTresEnRaya {
	//Codigos de escape ANSI que representan diferentes colores, destacar que cada codigo tiene delante el codigo de reinicio '\u0001B[0m':
	private static final String ROJO_BRILLANTE="\u001B[0m\033[0;91m", ROJO_SUBRAYADO_BRILLANTE="\u001B[0m\033[4;91m", VERDE_BRILLANTE="\u001B[0m\033[1;32m", 
								CYAN_BRILLANTE="\u001B[0m\033[0;96m", BLANCO="\u001B[0m\033[37m", BLANCO_BRILLANTE="\u001B[0m\033[1;37m",
								NEGRO_BRILLANTE="\u001B[0m\033[1;30m", AMARILLO_BRILLANTE="\u001B[0m\033[0;93m";
	//Tablero del juego:
	private static LogicaTresEnRaya miJuego = new LogicaTresEnRaya();		//Creamos un objeto de la clase LogicaTresEnRaya.
	//Metodo principal:
	public static void main(String[] args) {
		//Imprimimos la portada llamando al siguiente metodo:
		imprimirPortada();
		borrarConsola();
		Scanner in = new Scanner(System.in);	//Creamos nuestro objeto scanner para poder leer por teclado.
		int opc;		//Variable que contendra la opcion elegida por el usuario referente al menu.
		//Bucle 'do While' que se mantendra iterando hasta que el usuario elija la opcion '0'.
		do {
			opc=menu(in);	//Llamamos al menu para recoger la opcion elegida por el usuario.
			borrarConsola();
			//La opcion 1 del menu es para que jueguen JugadorX vs JugadorO:	(Empiezan las X)
			if(opc == 1) {
				opcP1vsP2(in);
			}
			//La opcion 2 del menu es para que jueguen JugadorX vs IAO:		(Empiezan las X)
			else if(opc == 2) {
				opcP1vsIA2(in);
			}
			//La opcion 3 del menu es para que jueguen IAX vs JugadorO:		(Empiezan las X)
			else if(opc == 3) {
				opcIA1vsP2(in);
			}
			//La opcion 4 del menu es para que jueguen IAX vs IAO:		(Empiezan las X)
			else if(opc == 4) {
				opcIA1vsIA2(in);
			}
			//Siempre que no sea la opcion '0', se mostrara el siguiente mensaje y se borrara la consola:
			if(opc!=0) {
				System.out.println(NEGRO_BRILLANTE+"│ "+BLANCO_BRILLANTE+"Pulse enter para continuar ...                           "+NEGRO_BRILLANTE+"│");
				System.out.println("└──────────────────────────────────────────────────────────┘");
				in.nextLine();
				borrarConsola();
			}
		}while(opc!=0);
	}
	//Metodo estatico que ejecutara la opcion numero 4 del menu, es decir IA X vs IA O:
	public static void opcIA1vsIA2(Scanner in) {
		borrarConsola();
		miJuego.getDibujaTablero();
		//Mostramos un mensaje para que la IA 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
			System.out.println("│ "+BLANCO_BRILLANTE+"IA X moverá cuando pulse un enter:                       "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero();
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"La IA X ha ganado la partida.                           "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Ni la IA PUEDE CONTRA LA IA.   "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"IA O moverá cuando pulse un enter:                       "+NEGRO_BRILLANTE+"│");
				System.out.println("└──────────────────────────────────────────────────────────┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero();
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"La IA O ha ganado la partida.                           "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Ni la IA PUEDE CONTRA LA IA.   "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 3 del menu, es decir IA X vs Jugador O:
	public static void opcIA1vsP2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero();
		//Mostramos un mensaje para que la IA 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
			System.out.println("│ "+BLANCO_BRILLANTE+"IA X moverá cuando pulse un enter:                       "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero();
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"Has sido derrotado, la IA X te ha humillado..            "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...   "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Sino, pasara a jugar el jugador las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"Jugador O introduzca su posición por favor:              "+NEGRO_BRILLANTE+"│");
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero();
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador O, usted ganó la partida.        "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...   "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 2 del menu, es decir Jugador X vs IA O:
	public static void opcP1vsIA2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero();
		//Mostramos un mensaje para que el jugador 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
			System.out.println("│ "+BLANCO_BRILLANTE+"Jugador X introduzca su posición por favor:              "+NEGRO_BRILLANTE+"│");
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero();
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador X, usted ganó la partida.        "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...   "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"IA O moverá cuando pulse un enter:                       "+NEGRO_BRILLANTE+"│");
				System.out.println("└──────────────────────────────────────────────────────────┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero();
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"Has sido derrotado, la IA O te ha humillado..            "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...   "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 1 del menu, es decir Jugador X vs Jugador O:
	public static void opcP1vsP2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero();
		//Mostramos un mensaje para que el jugador 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
			System.out.println("│ "+BLANCO_BRILLANTE+"Jugador X introduzca su posición por favor:              "+NEGRO_BRILLANTE+"│");
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero();
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador X, usted ganó la partida.        "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Bien jugado.                   "+NEGRO_BRILLANTE+"│");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
			//Sino, pasara a jugar el jugador de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+BLANCO_BRILLANTE+"Jugador O introduzca su posición por favor:              "+NEGRO_BRILLANTE+"│");
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero();
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador O, usted ganó la partida.        "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Bien jugado.                   "+NEGRO_BRILLANTE+"│");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que mostrara un mensaje para recoger la posicion elegida por el usuario:
	public static int recogerPosicion(Scanner in) {
		//Variables necesarias para la obtencion de la posicion:
		String aux = "";
		int pos = -1;
		//Pedimos la posicion y controlamos la entrada de datos:
		System.out.print  ("├──────────────────────────────────────────────────────────┤\n│ "+BLANCO_BRILLANTE);
		do {
			aux = in.nextLine();
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				pos = Integer.parseInt(aux.trim());
				if(pos < 1 ||  pos > 9 || !(miJuego.getMovimientoValido(pos))) {	//Si 'pos' no esta en el valor indicado mostramos mensaje de error:
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+ROJO_BRILLANTE+"Error, introduce un valor válido por favor:              "+NEGRO_BRILLANTE+"│");
					System.out.print  ("├──────────────────────────────────────────────────────────┤\n│ "+BLANCO_BRILLANTE);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+ROJO_BRILLANTE+"Error, introduce un valor válido por favor:              "+NEGRO_BRILLANTE+"│");
				System.out.print  ("├──────────────────────────────────────────────────────────┤\n│ "+BLANCO_BRILLANTE);
			}
		}while(pos < 1 || pos > 9 || !(miJuego.getMovimientoValido(pos)));
		System.out.println(NEGRO_BRILLANTE+"└──────────────────────────────────────────────────────────┘");
		//Retornamos la posicion:
		return pos;
	}
	//Metodo estatico que mostrara un menu para recoger y devolver la opcion elegida por el usuario:
	public static int menu(Scanner in) {
		int opc = -1;		//Variable opcion que guardara el valor de la opcion elegida.
		String aux="";		//String auxiliar para leer los datos con el y luego castear a entero.
		System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
		System.out.println("│ "+BLANCO_BRILLANTE+"Introduzca una de las siguientes opciones:               "+NEGRO_BRILLANTE+"│ ");
		System.out.println("├──────────────────────────────────────────────────────────┤");
		//Con un bucle 'do While' controlamos la opcion elegida por el usuario:
		do {
			System.out.println("│ "+BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"1"+BLANCO_BRILLANTE+") Jugador Vs Jugador.                                  "+NEGRO_BRILLANTE+"│");
			System.out.println("│ "+BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"2"+BLANCO_BRILLANTE+") Jugador Vs IA.                                       "+NEGRO_BRILLANTE+"│");
			System.out.println("│ "+BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"3"+BLANCO_BRILLANTE+") IA Vs Jugador.                                       "+NEGRO_BRILLANTE+"│");
			System.out.println("│ "+BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"4"+BLANCO_BRILLANTE+") IA Vs IA.                                            "+NEGRO_BRILLANTE+"│");
			System.out.println("│     "+BLANCO_BRILLANTE+"("+ROJO_BRILLANTE+"0"+BLANCO_BRILLANTE+") Salir del juego.                                 "+NEGRO_BRILLANTE+"│");
			System.out.print  ("├──────────────────────────────────────────────────────────┤\n│ "+BLANCO_BRILLANTE);
			aux=in.nextLine();	//La primera vez leemos en 'aux'.
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				opc=Integer.parseInt(aux.trim());
				if(opc<0||opc>4) {	//Si 'opc' no esta en el valor indicado mostramos mensaje de error:
					System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
					System.out.println("│ "+ROJO_SUBRAYADO_BRILLANTE+"Error, reintroduzca una opcion valida:"+NEGRO_BRILLANTE+"                   │");
					System.out.println("├──────────────────────────────────────────────────────────┤");
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				System.out.println(NEGRO_BRILLANTE+"├──────────────────────────────────────────────────────────┤");
				System.out.println("│ "+ROJO_SUBRAYADO_BRILLANTE+"Error, reintroduzca una opcion valida:"+NEGRO_BRILLANTE+"                   │");
				System.out.println("├──────────────────────────────────────────────────────────┤");
			}
		}while(opc<0||opc>4);
		return opc;	//Devolvemos el valor de 'opc'.
	}
	//Metodo estatico encargado de imprimir una portada al iniciar el programa con una animacion:
	static void imprimirPortada() {
		int tiempoAnimacion=200;
		//Imprimimos la animacion, y controlamos con 'try/catch' la excepcion 'InterruptedException':
		try {
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
			System.out.println("│                                                          │");
			System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
			System.out.println("│                                                          │");
			System.out.println("│  "+BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano                               "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
			System.out.println("│                                                          │");
			System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
			System.out.println("│                                                          │");
			System.out.println("│  "+BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano                               "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
			System.out.println("│                                                          │");
			System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
			System.out.println("│                                                          │");
			System.out.println("│  "+BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano                               "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
			System.out.println("│                                                          │");
			System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
			System.out.println("│                                                          │");
			System.out.println("│  "+BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano                               "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
			System.out.println("│                                                          │");
			System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│     "+VERDE_BRILLANTE+"██     "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │     "+VERDE_BRILLANTE+"██     "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │     "+VERDE_BRILLANTE+"██     "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
			System.out.println("│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
			System.out.println("│                                                          │");
			System.out.println("│  "+BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano                               "+NEGRO_BRILLANTE+"│");
			System.out.println("└──────────────────────────────────────────────────────────┘");
			Thread.sleep(500);
		}catch (Exception e) {
			System.out.println(ROJO_SUBRAYADO_BRILLANTE+"Error imprimir la portada del programa."+BLANCO);
		}
	}
	//Metodo estatico encargado de borrar la consola segun el sistema operativo en el que trabaja:
	static void borrarConsola() {
		//Segun el sistema operativo borramos la consola de una forma u otra:
		if(System.getProperty("os.name").toString().toUpperCase().contains("WINDOWS")) {
			//En el caso de 'Windows' creamos un proceso anonimo que lanza 'CMD', y el comando 'CLS', es inherente a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(ROJO_SUBRAYADO_BRILLANTE+"Error al borrar la consola de Windows."+BLANCO);}
		}
		else if(System.getProperty("os.name").toString().toUpperCase().contains("LINUX")){
			//En el caso de 'Linux' creamos un proceso anonimo que lanza 'bash', y el comando 'clear', es inherenete a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(ROJO_SUBRAYADO_BRILLANTE+"Error al borrar la consola de Linux."+BLANCO);}
		}
	}
}
