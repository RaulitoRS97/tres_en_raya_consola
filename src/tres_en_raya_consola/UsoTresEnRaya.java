package tres_en_raya_consola;
//Importamos los siguientes paquetes:
import java.io.IOException;
import java.util.Scanner;
//Clase principal:
public class UsoTresEnRaya {
	//Codigos de escape ANSI que representan diferentes colores, destacar que cada codigo tiene delante el codigo de reinicio '\u0001B[0m':
	protected static final String ROJO_BRILLANTE="\u001B[0m\033[0;91m", ROJO_SUBRAYADO_BRILLANTE="\u001B[0m\033[4;91m", VERDE_BRILLANTE="\u001B[0m\033[1;32m", 
								CYAN_BRILLANTE="\u001B[0m\033[0;96m", BLANCO="\u001B[0m\033[37m", BLANCO_BRILLANTE="\u001B[0m\033[1;37m",
								NEGRO_BRILLANTE="\u001B[0m\033[1;30m", AMARILLO_BRILLANTE="\u001B[0m\033[0;93m";
	//Tablero del juego:
	private static LogicaTresEnRaya miJuego = new LogicaTresEnRaya();		//Creamos un objeto de la clase LogicaTresEnRaya.
	//Separador de interfaz que se va a usar:
	private static final String separadorInterfaz ="──────────────────────────────────────────────────────────";
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
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Pulse enter para continuar ...", 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				borrarConsola();
			}
		}while(opc!=0);
	}
	//Metodo estatico que ejecutara la opcion numero 4 del menu, es decir IA X vs IA O:
	public static void opcIA1vsIA2(Scanner in) {
		borrarConsola();
		miJuego.getDibujaTablero(separadorInterfaz);
		//Mostramos un mensaje para que la IA 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"IA X moverá cuando pulse un enter:", 0));
			System.out.println("└"+separadorInterfaz+"┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"La IA X ha ganado la partida.", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Ni la IA PUEDE CONTRA LA IA.", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"IA O moverá cuando pulse un enter:", 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"La IA O ha ganado la partida.", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Ni la IA PUEDE CONTRA LA IA.", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 3 del menu, es decir IA X vs Jugador O:
	public static void opcIA1vsP2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero(separadorInterfaz);
		//Mostramos un mensaje para que la IA 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"IA X moverá cuando pulse un enter:", 0));
			System.out.println("└"+separadorInterfaz+"┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Has sido derrotado, la IA X te ha humillado...", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar el jugador las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Jugador O introduzca su posición por favor:", 0));
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador O, usted ganó la partida.", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 2 del menu, es decir Jugador X vs IA O:
	public static void opcP1vsIA2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero(separadorInterfaz);
		//Mostramos un mensaje para que el jugador 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Jugador X introduzca su posición por favor:", 0));
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador X, usted ganó la partida.", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"IA O moverá cuando pulse un enter:", 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Has sido derrotado, la IA O te ha humillado...", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, nadie gana contra esta IA...", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
			}
		}while(!(miJuego.getGanaJugador(1)) && !(miJuego.getGanaJugador(2)) && miJuego.getQuedanMovimientos());
		miJuego.setIniciar();
	}
	//Metodo estatico que ejecutara la opcion numero 1 del menu, es decir Jugador X vs Jugador O:
	public static void opcP1vsP2(Scanner in) {
		int pos = -1;			//Variable que contendra la posicion elegida por el jugador.
		borrarConsola();
		miJuego.getDibujaTablero(separadorInterfaz);
		//Mostramos un mensaje para que el jugador 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Jugador X introduzca su posición por favor:", 0));
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador X, usted ganó la partida.", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Bien jugado.", 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar el jugador de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Jugador O introduzca su posición por favor:", 0));
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡FELICIDADES!! Jugador O, usted ganó la partida.", 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"¡¡Se produjo un EMPATE!!, Bien jugado.", 0));
					System.out.println("├"+separadorInterfaz+"┤");
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
		System.out.print  ("├"+separadorInterfaz+"┤\n│ "+BLANCO);
		do {
			aux = in.nextLine();
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				pos = Integer.parseInt(aux.trim());
				if(pos < 1 ||  pos > 9 || !(miJuego.getMovimientoValido(pos))) {	//Si 'pos' no esta en el valor indicado mostramos mensaje de error:
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_BRILLANTE+"Error, introduce un valor válido por favor:", 0));
					System.out.print  ("├"+separadorInterfaz+"┤\n│ "+BLANCO);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_BRILLANTE+"Error, introduce un valor válido por favor:", 0));
				System.out.print  ("├"+separadorInterfaz+"┤\n│ "+BLANCO);
			}
		}while(pos < 1 || pos > 9 || !(miJuego.getMovimientoValido(pos)));
		System.out.println("└"+separadorInterfaz+"┘");
		//Retornamos la posicion:
		return pos;
	}
	//Metodo estatico que mostrara un menu para recoger y devolver la opcion elegida por el usuario:
	public static int menu(Scanner in) {
		//Strings para guardar el texto procesado a mostrar e imprimirlo del tiron y asi que no se vea el cursor moverse como locooo:
		String cuerpo = "", cabeceraError = "";
		int opc = -1;		//Variable opcion que guardara el valor de la opcion elegida.
		String aux="";		//String auxiliar para leer los datos con el y luego castear a entero.
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"1"+BLANCO_BRILLANTE+") Jugador Vs Jugador.", 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"2"+BLANCO_BRILLANTE+") Jugador Vs IA.", 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"3"+BLANCO_BRILLANTE+") IA Vs Jugador.", 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"("+CYAN_BRILLANTE+"4"+BLANCO_BRILLANTE+") IA Vs IA.", 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "    "+BLANCO_BRILLANTE+"("+ROJO_BRILLANTE+"0"+BLANCO_BRILLANTE+") Salir del juego.", 0)+"\r\n";
		cuerpo+=  ("├"+separadorInterfaz+"┤\r\n│ "+BLANCO);
		cabeceraError+=(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐\r\n");
		cabeceraError+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+"Error, reintroduzca una opcion valida:", 0)+"\r\n";
		cabeceraError+=("├"+separadorInterfaz+"┤\r\n");
		//Procesamos el texto a mostrar:
		System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
		System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+"Introduzca una de las siguientes opciones:", 0));
		System.out.println("├"+separadorInterfaz+"┤");
		//Con un bucle 'do While' controlamos la opcion elegida por el usuario:
		do {
			System.out.print(cuerpo);
			aux=in.nextLine();	//La primera vez leemos en 'aux'.
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				opc=Integer.parseInt(aux.trim());
				if(opc<0||opc>4) {	//Si 'opc' no esta en el valor indicado mostramos mensaje de error:
					borrarConsola();
					System.out.print(cabeceraError);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				borrarConsola();
				System.out.print(cabeceraError);
			}
		}while(opc<0||opc>4);
		return opc;	//Devolvemos el valor de 'opc'.
	}
	//Metodo estatico encargado de imprimir una portada al iniciar el programa con una animacion:
	static void imprimirPortada() {
		//Strings para guardar el texto procesado a mostrar e imprimirlo del tiron y asi que no se vea el cursor moverse como locooo:
		String estado1 = "", estado2 = "", estado3 = "", estado4 = "", estado5 = "";
		//Calculamos las partes a imprimir:
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Version: "+AMARILLO_BRILLANTE+"1.1", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"┌────────────┬────────────┬────────────┐", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"└────────────┴────────────┴────────────┘", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano"+BLANCO+"   │   "+AMARILLO_BRILLANTE+"github.com/RaulitoRS97", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Version: "+AMARILLO_BRILLANTE+"1.1", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"┌────────────┬────────────┬────────────┐", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"└────────────┴────────────┴────────────┘", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano"+BLANCO+"   │   "+AMARILLO_BRILLANTE+"github.com/RaulitoRS97", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Version: "+AMARILLO_BRILLANTE+"1.1", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"┌────────────┬────────────┬────────────┐", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"└────────────┴────────────┴────────────┘", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano"+BLANCO+"   │   "+AMARILLO_BRILLANTE+"github.com/RaulitoRS97", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Version: "+AMARILLO_BRILLANTE+"1.1", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"┌────────────┬────────────┬────────────┐", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │     "+CYAN_BRILLANTE+"██     "+BLANCO+"│", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │   "+CYAN_BRILLANTE+"██  ██   "+BLANCO+"│", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"└────────────┴────────────┴────────────┘", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano"+BLANCO+"   │   "+AMARILLO_BRILLANTE+"github.com/RaulitoRS97", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Version: "+AMARILLO_BRILLANTE+"1.1", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"┌────────────┬────────────┬────────────┐", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│     "+VERDE_BRILLANTE+"██     "+BLANCO+"│            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │     "+VERDE_BRILLANTE+"██     "+BLANCO+"│            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"├────────────┼────────────┼────────────┤", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │     "+VERDE_BRILLANTE+"██     "+BLANCO+"│", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │   "+VERDE_BRILLANTE+"██  ██   "+BLANCO+"│", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"│            │            │            │", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"└────────────┴────────────┴────────────┘", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+"Autor: "+AMARILLO_BRILLANTE+"Raul Ramos Serrano"+BLANCO+"   │   "+AMARILLO_BRILLANTE+"github.com/RaulitoRS97", 1)+"\r\n";
		int tiempoAnimacion=225;
		//Imprimimos la animacion, y controlamos con 'try/catch' la excepcion 'InterruptedException':
		try {
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
			System.out.print(estado1);
			System.out.println("└"+separadorInterfaz+"┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
			System.out.print(estado2);
			System.out.println("└"+separadorInterfaz+"┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
			System.out.print(estado3);
			System.out.println("└"+separadorInterfaz+"┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
			System.out.print(estado4);
			System.out.println("└"+separadorInterfaz+"┘");
			Thread.sleep(tiempoAnimacion);borrarConsola();
			//Mostramos el tablero por pantalla
			System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
			System.out.print(estado5);
			System.out.println("└"+separadorInterfaz+"┘");
			Thread.sleep(500);
		}catch (Exception e) {
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+"Error imprimir la portada del programa.", 0));
		}
	}
	//Metodo estatico encargado de borrar la consola segun el sistema operativo en el que trabaja:
	static void borrarConsola() {
		//Segun el sistema operativo borramos la consola de una forma u otra:
		if(System.getProperty("os.name").toString().toUpperCase().contains("WINDOWS")) {
			//En el caso de 'Windows' creamos un proceso anonimo que lanza 'CMD', y el comando 'CLS', es inherente a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+"Error al borrar la consola de Windows.", 0));}
		}
		else if(System.getProperty("os.name").toString().toUpperCase().contains("LINUX")){
			//En el caso de 'Linux' creamos un proceso anonimo que lanza 'bash', y el comando 'clear', es inherenete a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+"Error al borrar la consola de Linux.", 0));}
		}
	}
}
