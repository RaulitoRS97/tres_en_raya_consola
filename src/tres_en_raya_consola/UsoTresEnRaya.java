package tres_en_raya_consola;
//Importamos los siguientes paquetes:
import java.io.IOException;
import java.util.Scanner;
//Clase principal:
public class UsoTresEnRaya {
	//Codigos de escape ANSI que representan diferentes colores, destacar que cada codigo tiene delante el codigo de reinicio '\u0001B[0m':
	private static final String ROJO_BRILLANTE="\u001B[0m\033[0;91m", ROJO_SUBRAYADO_BRILLANTE="\u001B[0m\033[4;91m", VERDE_BRILLANTE="\u001B[0m\033[1;32m", 
								AMARILLO_SUBRAYADO_BRILLANTE="\u001B[0m\033[0;93m", CYAN_BRILLANTE="\u001B[0m\033[0;96m", BLANCO="\u001B[0m\033[37m", 
								BLANCO_BRILLANTE="\u001B[0m\033[1;37m", NEGRO_BRILLANTE="\u001B[0m\033[1;30m", AMARILLO_BRILLANTE="\u001B[0m\033[0;93m";
	//Figuras X y O y VACIO para el tablero:
	private static final String[] JUGADOR_X = {"  ██  ██  ","    ██    ","  ██  ██  "}, JUGADOR_O = {"  ██████  ","  ██  ██  ","  ██████  "}, VACIO = {"          ","          ","          "};
	//Metodo principal:
	public static void main(String[] args) {
		//Imprimimos la portada llamando al siguiente metodo:
		imprimirPortada();
		borrarConsola();
		Scanner in = new Scanner(System.in);	//Creamos nuestro objeto scanner para poder leer por teclado.
		int opc;	//Variable que contendra la opcion elegida por el usuario referente al menu.
		//Bucle 'do While' que se mantendra iterando hasta que el usuario elija la opcion '0'.
		do {
			opc=menu(in);	//Llamamos al menu para recoger la opcion elegida por el usuario.
			borrarConsola();
		}while(opc!=0);
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
