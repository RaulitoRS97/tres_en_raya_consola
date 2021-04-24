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
	//String para parametrizar el idioma de los textos externalizados (por defecto es español):
	private static String idioma="ES.";
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
			//La opcion 5 del menu es para cambiar de idioma:
			else if(opc==5) {	//Pasamos a llevar al submenu de idiomas para realizar un cambio de idioma.
				int opc2;	//Variable que contendra la opcion elegida por el usuario referente al menu.
				System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
				opc2=menuIdiomas(in);	//Obtenemos la opcion elegida por el usuario.
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				if(opc2==1) {
					//Cambio y mensaje Español
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.main.opc11.idiomaElegido.Español"), 0));
					idioma="ES.";
				}else if(opc2==2) {
					//Cambio y mensaje Ingles
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.main.opc11.idiomaElegido.Ingles"), 0));
					idioma="EN.";
				}
				if(opc2!=0) {
					System.out.println("├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.main.mensajeContinuar"), 0));
					System.out.println("└"+separadorInterfaz+"┘");in.nextLine();
				}
				borrarConsola();
			}
			//Siempre que no sea la opcion '0', se mostrara el siguiente mensaje y se borrara la consola:
			if(opc!=0&&opc!=5) {
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.main.mensajeContinuar"), 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				borrarConsola();
			}
		}while(opc!=0);
	}
	//Metodo estatico que mostrara un menu para recoger y devolver la opcion elegida por el usuario:
	//Recibe como parametros un escaner, un booleano indicando si se debe dibujar union o no, y el separador de interfaz que se utilizara.
	static int menuIdiomas(Scanner in) {
		//Strings para guardar el texto procesado a mostrar e imprimirlo del tiron y asi que no se vea el cursor moverse como locooo:
		String cuerpo = "", cabeceraError = "";
		int opc = -1;		//Variable opcion que guardara el valor de la opcion elegida.
		String aux="";		//String auxiliar para leer los datos con el y luego castear a entero.
		//Procesamos el texto a mostrar:
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc1.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc1.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc1.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc2.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc2.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc2.2"), 0)+"\r\n";
		cuerpo+="├"+separadorInterfaz+"┤\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc0.0")+ROJO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc0.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeOpc0.2"), 0)+"\r\n";
		cuerpo+= ("├"+separadorInterfaz+"┤\r\n│ "+BLANCO);
		cabeceraError+=(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐\r\n");
		cabeceraError+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.error.opcionNoValida"), 0)+"\r\n";
		cabeceraError+=("├"+separadorInterfaz+"┤\r\n");
		System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menuIdiomas.mensajeIntroducirDatos"), 0));
		System.out.println("├"+separadorInterfaz+"┤");
		//Controlamos la opcion elegida por el usuario:
		do {
			System.out.print(cuerpo);
			aux=in.nextLine();	//La primera vez leemos en 'aux'.
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				opc=Integer.parseInt(aux.trim());
				if(opc<0||opc>2) {	//Si 'opc' no esta en el valor indicado mostramos mensaje de error:
					borrarConsola();
					System.out.print(cabeceraError);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				borrarConsola();
				System.out.print(cabeceraError);
			}
		}while(opc<0||opc>2);
		return opc;	//Devolvemos el valor de 'opc'.
	}
	//Metodo estatico que ejecutara la opcion numero 4 del menu, es decir IA X vs IA O:
	public static void opcIA1vsIA2(Scanner in) {
		borrarConsola();
		miJuego.getDibujaTablero(separadorInterfaz);
		//Mostramos un mensaje para que la IA 1 introduzca la posicion en la que desea colocar su ficha:
		do {
			System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.mueve.IAX"), 0));
			System.out.println("└"+separadorInterfaz+"┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.gana.IAX"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.empate"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.mueve.IAO"), 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.gana.IAO"), 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsIA2.mensaje.empate"), 0));
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
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.mueve.IAX"), 0));
			System.out.println("└"+separadorInterfaz+"┘");
			in.nextLine();
			miJuego.setMueveOrdenador(1, 2);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.gana.IAX"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.empate"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar el jugador las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.mueve.JO"), 0));
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.gana.JO"), 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcIA1vsP2.mensaje.empate"), 0));
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
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.mueve.JX"), 0));
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.gana.JX"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.empate"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar la IA de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.mueve.IAO"), 0));
				System.out.println("└"+separadorInterfaz+"┘");
				in.nextLine();
				miJuego.setMueveOrdenador(2, 1);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria de la IA O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.gana.IAO"), 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsIA2.mensaje.empate"), 0));
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
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.mueve.JX"), 0));
			pos = recogerPosicion(in);
			miJuego.setMueveJugador(pos, 1);
			borrarConsola();
			miJuego.getDibujaTablero(separadorInterfaz);
			//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador X y si es asi mostramos un mensaje:
			if(miJuego.getGanaJugador(1)) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.gana.JX"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
			else if(!miJuego.getQuedanMovimientos()) {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.empate"), 0));
				System.out.println("├"+separadorInterfaz+"┤");
			}
			//Sino, pasara a jugar el jugador de las O:
			else {
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.mueve.JO"), 0));
				pos = recogerPosicion(in);
				miJuego.setMueveJugador(pos, 2);
				borrarConsola();
				miJuego.getDibujaTablero(separadorInterfaz);
				//Utilizamos el metodo "getGanaJugador" para comprobar si hay victoria del jugador O y si es asi mostramos un mensaje:
				if(miJuego.getGanaJugador(2)) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.gana.JO"), 0));
					System.out.println("├"+separadorInterfaz+"┤");
				}
				//Comprobamos con el metodo "getQuedanMovimientos" del objeto "mijuego" si quedan movimientos y si no quedan mostramos mensaje de empate:
				else if(!miJuego.getQuedanMovimientos()) {
					System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.opcP1vsP2.mensaje.empate"), 0));
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
					System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.recogerPosicion.mensaje.error.valorNoValido"), 0));
					System.out.print  ("├"+separadorInterfaz+"┤\n│ "+BLANCO);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				System.out.println(NEGRO_BRILLANTE+"├"+separadorInterfaz+"┤");
				System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.recogerPosicion.mensaje.error.valorNoValido"), 0));
				System.out.print  ("├"+separadorInterfaz+"┤\n│ "+BLANCO);
			}
		}while(pos < 1 || pos > 9 || !(miJuego.getMovimientoValido(pos)));
		System.out.println(NEGRO_BRILLANTE+"└"+separadorInterfaz+"┘");
		//Retornamos la posicion:
		return pos;
	}
	//Metodo estatico que mostrara un menu para recoger y devolver la opcion elegida por el usuario:
	public static int menu(Scanner in) {
		//Strings para guardar el texto procesado a mostrar e imprimirlo del tiron y asi que no se vea el cursor moverse como locooo:
		String cuerpo = "", cabeceraError = "";
		int opc = -1;		//Variable opcion que guardara el valor de la opcion elegida.
		String aux="";		//String auxiliar para leer los datos con el y luego castear a entero.
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc1.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc1.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc1.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc2.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc2.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc2.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc3.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc3.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc3.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc4.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc4.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc4.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc5.0")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc5.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc5.2"), 0)+"\r\n";
		cuerpo+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc0.0")+ROJO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc0.1")+BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeOpc0.2"), 0)+"\r\n";
		cuerpo+=  ("├"+separadorInterfaz+"┤\r\n│ "+BLANCO);
		cabeceraError+=(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐\r\n");
		cabeceraError+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.error.opcionNoValida"), 0)+"\r\n";
		cabeceraError+=("├"+separadorInterfaz+"┤\r\n");
		//Procesamos el texto a mostrar:
		System.out.println(NEGRO_BRILLANTE+"┌"+separadorInterfaz+"┐");
		System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.menu.mensajeIntroducirDatos"), 0));
		System.out.println("├"+separadorInterfaz+"┤");
		//Con un bucle 'do While' controlamos la opcion elegida por el usuario:
		do {
			System.out.print(cuerpo);
			aux=in.nextLine();	//La primera vez leemos en 'aux'.
			try {	//Intentamos castear a 'aux' a entero, y si no lo conseguimos controlamos la excepcion:
				opc=Integer.parseInt(aux.trim());
				if(opc<0||opc>5) {	//Si 'opc' no esta en el valor indicado mostramos mensaje de error:
					borrarConsola();
					System.out.print(cabeceraError);
				}
			}catch(NumberFormatException n) {	//Mostramos mensaje de error cuando se captura la excepcion:
				borrarConsola();
				System.out.print(cabeceraError);
			}
		}while(opc<0||opc>5);
		return opc;	//Devolvemos el valor de 'opc'.
	}
	//Metodo estatico encargado de imprimir una portada al iniciar el programa con una animacion:
	static void imprimirPortada() {
		//Strings para guardar el texto procesado a mostrar e imprimirlo del tiron y asi que no se vea el cursor moverse como locooo:
		String estado1 = "", estado2 = "", estado3 = "", estado4 = "", estado5 = "";
		//Calculamos las partes a imprimir:
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.2"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.1"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.2"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.3"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.4"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.5"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.6"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.7"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.8"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.9"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.10"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.11"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.12"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.13"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.14"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.15"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.16"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.17"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.18"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.1.19"), 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado1+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.3")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.4"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.2"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.1"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.2"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.3.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.3.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.3.3"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.4.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.4.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.4.3"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.5.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.5.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.5.3"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.6"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.7"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.8"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.9"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.10"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.11"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.12"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.13"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.14"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.15"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.16"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.17"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.18"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.2.19"), 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado2+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.3")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.4"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.2"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.1"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.2"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.3.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.3.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.3.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.4.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.4.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.4.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.5.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.5.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.5.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.6"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.7"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.8"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.9.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.9.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.9.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.10.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.10.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.10.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.11.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.11.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.11.3"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.12"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.13"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.14"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.15"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.16"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.17"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.18"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.3.19"), 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado3+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.3")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.4"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.2"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.1"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.2"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.6"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.7"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.8"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.12"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.13"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.14"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.1")+CYAN_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.3"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.18"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.19"), 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado4+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.3")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.4"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.version.2"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.1"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.2"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.3.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.4.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.5.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.6"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.7"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.8"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.9.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.10.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.11.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.12"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.13"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.14"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.15.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.16.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.1")+VERDE_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.17.3"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.18"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.dibujo.4.19"), 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, "", 1)+"\r\n";
		estado5+=LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.1")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.2")+BLANCO+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.3")+AMARILLO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.autor.4"), 1)+"\r\n";
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
			Thread.sleep(tiempoAnimacion*2);
		}catch (Exception e) {
			System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.portada.error"), 0));
		}
	}
	//Metodo estatico encargado de borrar la consola segun el sistema operativo en el que trabaja:
	static void borrarConsola() {
		//Segun el sistema operativo borramos la consola de una forma u otra:
		if(System.getProperty("os.name").toString().toUpperCase().contains("WINDOWS")) {
			//En el caso de 'Windows' creamos un proceso anonimo que lanza 'CMD', y el comando 'CLS', es inherente a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.borrarConsola.mensaje.error.Windows"), 0));}
		}
		else if(System.getProperty("os.name").toString().toUpperCase().contains("LINUX")){
			//En el caso de 'Linux' creamos un proceso anonimo que lanza 'bash', y el comando 'clear', es inherenete a la entrada y salida del proceso principal, y se espera a que se termine para seguir el flujo del programa:
			try {
				new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {System.out.println(LogicaTresEnRaya.calcularMensajesInterfaz(separadorInterfaz, ROJO_SUBRAYADO_BRILLANTE+Messages.getString(idioma+"UsoTresEnRaya.consola.borrarConsola.mensaje.error.Linux"), 0));}
		}
	}
}
