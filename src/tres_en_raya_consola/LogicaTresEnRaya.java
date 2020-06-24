//Importamos los siguientes paquetes:
package tres_en_raya_consola;
//Clase que contiene toda la logica del juego, como las comprobaciones, los pasos a realizar por la IA ...:
public class LogicaTresEnRaya {
	//Atributos privados para la clase:
	private int [] tablero;
	//Atributos privados que funcionan de manera interna para la clase:
	private double numPasoX, numPasoO;	//Numero de paso de estrategia para la IA.
	private boolean primeroX, primeroO;	//Comprobador de turno inicial.
	//Constructor de la clase:
	public LogicaTresEnRaya() {
		//Creamos nuestro tablero de juego:
		tablero = new int [9];
		//Utilizamos estos atributos para controlar las estrategias a seguir segun la evolucion de la partida:
		numPasoX = 0;
		numPasoO = 0;
		primeroX = true;
		primeroO = true;
	}
	//Metodo set que realiza el movimiento del jugador que se le pase, en la posicion pasada:
	//[JUGADOR 1 = 1, JUGADOR 2 = 2]
	public void setMueveJugador(int posicion, int jugador) {
		//Antes de tapar esa casilla con su ficha, se comprueba que este vacia:
		if(tablero[posicion] == 0) {tablero[posicion] = jugador;}
	}
	//Metodo get que devuelve un booleano en funcion de si se puede realizar el movimiento o no en la posicion pasada por parametro:
	//[TRUE = MOVIMIENTO VALIDO, FALSE = MOVIMIENTO INVALIDO]
	public boolean getMovimientoValido(int posicion) {
		return tablero[posicion] == 0;
	}
	//Metodo set para reiniciar el tablero para otra nueva partida:
	public void setIniciar() {
		//Limpiamos el tablero:
		for(int i = 0 ; i < 9 ; i++) {
			tablero[i]=0;
		}
		//Reiniciamos los atributos para controlar las estatregias a seguir segun la evolucion de la partida:
		numPasoX = 0;
		numPasoO = 0;
		primeroX = true;
		primeroO = true;
	}
	//Metodo get que devuelve un booleano utilizado para comprobar si gana el jugador pasado por parametro gana o pierde:
	//[TRUE = JUGADOR GANA, FALSE = JUGADOR NO GANA]
	public boolean getGanaJugador(int jugador) {
		//Variables:
		int i;					//Contador para recorrer el tablero.
		int contFichas;			//Contador de fichas encontradas del jugador pasado.
		boolean gana = false;	//Bandera booleana para saber si ha ganado o no el jugador.
		//Bucle para buscar combinación ganadora de manera horizontal por cada fila:
		int lim; 	//Variable para emular las limitaciones verticales del tablero 3x3 en nuestro array lineal de 9x1, y asi representar las lineas.
		i = 0; lim = 3; contFichas = 0;
		while(i < lim && !gana) {
			if(tablero[i] == jugador) {
				contFichas++;
				gana = contFichas == 3;
			}
			i++;
			if(i == 3 || i == 6) {
				lim += 3;
				contFichas = 0;
			}
		}
		//Bucle para buscar combinación ganadora de manera vertical por cada columna:
		i = 0; contFichas = 0;
		while(i < 9 && !gana) {
			if(tablero[i] == jugador) {
				contFichas++;
				gana = contFichas == 3;
			}
			if(i == 6 || i == 7) {
				i -= 5;
				contFichas = 0;
			}
			else {i += 3;}
		}
		//Bucle para buscar combinación ganadora en las diagonales:
		i = 0; contFichas = 0;
		boolean diagonalUlt = false;
		while(i != -1 && !gana) {
			if(tablero[i] == jugador) {
				contFichas++;
				gana = contFichas == 3;
			}
			if(i == 0 || i == 4 && !diagonalUlt) {i += 4;}
			else if(i == 8){
				i = 2;
				contFichas = 0;
				diagonalUlt = true;
			}
			else if(i == 2 || i == 4 && diagonalUlt) {i += 2;}
			else if(i == 6) {i =- 1;}
		}
		return gana;
	}
}
