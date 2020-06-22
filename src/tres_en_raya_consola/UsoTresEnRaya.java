package tres_en_raya_consola;
//Importamos los siguientes paquetes:
import java.io.IOException;
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
