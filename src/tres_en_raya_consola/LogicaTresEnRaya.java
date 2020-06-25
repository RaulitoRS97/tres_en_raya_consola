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
	//Metodo get que devuelve un booleano para controlar si quedan espacios libres, es decir, si se puede realizar un movimiento:
	//[TRUE = QUEDAN ESPACIOS, FALSE = NO QUEDAN ESPACIOS]
	public boolean getQuedanMovimientos() {
		boolean prueba = false;
		for(int i = 0 ; i < 9 && !prueba ; i++) {
			prueba = tablero[i] == 0;
		}
		return prueba;
	}
	//Metodo privado que segun los parametros pasados, hace el ataque final, o la defensa final:
	//Si le pasas valor casilla '1' y jugador '1' intentara hacer el movimiento final para ganar:
	//Si le pasas valor casilla '2' y jugador '1' intentara hacer la defensa final para no perder:
	//Devuelve un booleano para indicar si se pudo realizar un movimiento o no:
	private boolean realizaMovimientosDefensaAtaque(int valorCasilla, int jugador) {
		boolean hecho = false;	//Variable booleana para comprobar si se ha realizado el movimiento o no.
		//Se comprueba si es posible hacer 3 en raya en cualquier parte del tablero y si es asi realiza el movimiento de ataque o defensa segun los parametros pasados:
		if     (tablero[0] == valorCasilla && tablero[1] == valorCasilla && tablero[2] == 0) {tablero[2] = jugador; hecho = true;}
		else if(tablero[0] == valorCasilla && tablero[1] == 0 && tablero[2] == valorCasilla) {tablero[1] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[1] == valorCasilla && tablero[2] == valorCasilla) {tablero[0] = jugador; hecho = true;}
		else if(tablero[3] == valorCasilla && tablero[4] == valorCasilla && tablero[5] == 0) {tablero[5] = jugador; hecho = true;}
		else if(tablero[3] == valorCasilla && tablero[4] == 0 && tablero[5] == valorCasilla) {tablero[4] = jugador; hecho = true;}
		else if(tablero[3] == 0 && tablero[4] == valorCasilla && tablero[5] == valorCasilla) {tablero[3] = jugador; hecho = true;}
		else if(tablero[6] == valorCasilla && tablero[7] == valorCasilla && tablero[8] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[6] == valorCasilla && tablero[7] == 0 && tablero[8] == valorCasilla) {tablero[7] = jugador; hecho = true;}
		else if(tablero[6] == 0 && tablero[7] == valorCasilla && tablero[8] == valorCasilla) {tablero[6] = jugador; hecho = true;}
		else if(tablero[0] == valorCasilla && tablero[3] == valorCasilla && tablero[6] == 0) {tablero[6] = jugador; hecho = true;}
		else if(tablero[0] == valorCasilla && tablero[3] == 0 && tablero[6] == valorCasilla) {tablero[3] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[3] == valorCasilla && tablero[6] == valorCasilla) {tablero[0] = jugador; hecho = true;}
		else if(tablero[1] == valorCasilla && tablero[4] == valorCasilla && tablero[7] == 0) {tablero[7] = jugador; hecho = true;}
		else if(tablero[1] == valorCasilla && tablero[4] == 0 && tablero[7] == valorCasilla) {tablero[4] = jugador; hecho = true;}
		else if(tablero[1] == 0 && tablero[4] == valorCasilla && tablero[7] == valorCasilla) {tablero[1] = jugador; hecho = true;}
		else if(tablero[2] == valorCasilla && tablero[5] == valorCasilla && tablero[8] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[2] == valorCasilla && tablero[5] == 0 && tablero[8] == valorCasilla) {tablero[5] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[5] == valorCasilla && tablero[8] == valorCasilla) {tablero[2] = jugador; hecho = true;}
		else if(tablero[0] == valorCasilla && tablero[4] == valorCasilla && tablero[8] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[0] == valorCasilla && tablero[4] == 0 && tablero[8] == valorCasilla) {tablero[4] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[4] == valorCasilla && tablero[8] == valorCasilla) {tablero[0] = jugador; hecho = true;}
		else if(tablero[2] == valorCasilla && tablero[4] == valorCasilla && tablero[6] == 0) {tablero[6] = jugador; hecho = true;}
		else if(tablero[2] == valorCasilla && tablero[4] == 0 && tablero[6] == valorCasilla) {tablero[4] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[4] == valorCasilla && tablero[6] == valorCasilla) {tablero[2] = jugador; hecho = true;}
		return hecho;
	}
	//Metodo set que realiza los pasos de la IA especificada en los parametros:
	public void setMueveOrdenador(int jugador, int jugadorContrario) {
		boolean hecho = false;	//Variable booleana para comprobar si se ha realizado el movimiento o no.
		int pos = 0;			//Variable que alberga la posicion para cuando la posicion se elige de forma aleatoria.
		boolean primeroGenerico;//Variable que segun el jugador pasado obtendra el valor del atributo privado de la clase y al final del metodo se lo asignara de nuevo.
		double numPasoGenerico;	//Variable que segun el jugador pasado obtendra el valor del atributo privado de la clase y al final del metodo se lo asignara de nuevo.
		//Segun el jugador recibido como parametro, asignamos los valores de los atributos privados a los valores genericos:
		if(jugador == 1) {
			primeroGenerico = primeroX; numPasoGenerico = numPasoX;
		}else {
			primeroGenerico = primeroO; numPasoGenerico = numPasoO;
		}
		//Ataque final, llamamos al metodo siguiente, y le decimos que busque 2 casillas seguidas con nuestro valor y otro vacio, para poder ganar el juego:
		hecho = realizaMovimientosDefensaAtaque(jugador, jugador);
		//Defensa final, en caso de que no se haya ya realizado un movimiento, llamamos al metodo siguiente y le decimos que busque 2 casillas seguidas con
		//el valor del otro jugador y una vacia, para defender el juego o al menos una jugada:
		if(!hecho) {
			hecho = realizaMovimientosDefensaAtaque(jugadorContrario, jugador);
			//Si se sigue sin realizar un movimiento, pasamos a ejecutar la estrategia segun el turno que la IA tenga:
			if(!hecho) {
				//Comprueba si es el primer turno o no:
				for(int i = 0 ; i < 9 && primeroGenerico && numPasoGenerico <= 0 ; i++) {
					if(tablero[i] != 0) {primeroGenerico = false;}
				}
				//Si no es el primero, hace las siguientes estrategias, dependiendo de si es el primer movimiento o no:
				if(!primeroGenerico) {
					hecho = true;
					if((tablero[4] == 0 && numPasoGenerico == 0)&&(tablero[1] == 0 && tablero[3] == 0 && tablero[5] == 0 &&tablero[7] == 0)) {
						tablero[4] = jugador;
						numPasoGenerico ++;
					}
					else if(tablero[4] == jugadorContrario && numPasoGenerico == 0) {
						if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
						numPasoGenerico += 2;
					}
					else if((tablero[4] == 0 && numPasoGenerico == 0)&&(tablero[0] == 0 && tablero[2] == 0 && tablero[6] == 0 && tablero[8] == 0)) {
						tablero[4] = jugador;
						numPasoGenerico += 3;
					}
					else if(numPasoGenerico == 1) {
						if(tablero[1] == 0) {tablero[1] = jugador;}
						else if(tablero[3] == 0) {tablero[3] = jugador;}
						else if(tablero[5] == 0) {tablero[5] = jugador;}
						else if(tablero[7] == 0) {tablero[7] = jugador;}
						else if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
					}
					else if(numPasoGenerico == 2) {
						if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
						else if(tablero[1] == 0) {tablero[1] = jugador;}
						else if(tablero[3] == 0) {tablero[3] = jugador;}
						else if(tablero[5] == 0) {tablero[5] = jugador;}
						else if(tablero[7] == 0) {tablero[7] = jugador;}
					}
					else if(numPasoGenerico == 3) {
						if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
						else if(tablero[1] == 0) {tablero[1] = jugador;}
						else if(tablero[3] == 0) {tablero[3] = jugador;}
						else if(tablero[5] == 0) {tablero[5] = jugador;}
						else if(tablero[7] == 0) {tablero[7] = jugador;}
					}
				}
				//Si es el primero turno hace las siguientes estrategias:
				else {
					if(numPasoGenerico == 0) {
						//Mediante este bucle controlamos que el primer movimiento se produzca en una de las esquinas del tablero de manera aleatoria:
						do {
							pos = (int)(9*Math.random());
						}while(pos != 6 && pos != 0 && pos != 2 && pos != 8);
						tablero[pos] = jugador;
						hecho = true;
						numPasoGenerico++;
					}
					else if(numPasoGenerico == 1 && (tablero[1] == jugadorContrario || tablero[3] == jugadorContrario || tablero[5] == jugadorContrario || tablero[7] == jugadorContrario)){
						numPasoGenerico += 0.1;
						hecho = true;
						if(tablero[1] == jugadorContrario) {
							if(tablero[0] == jugador) {tablero[6] = jugador;}
							else if(tablero[2] == jugador) {tablero[8] = jugador;}
							else if(tablero[6] == jugador) {tablero[8] = jugador;}
							else if(tablero[8] == jugador) {tablero[6] = jugador;}
						}
						else if(tablero[3] == jugadorContrario) {
							if(tablero[0] == jugador) {tablero[2] = jugador;}
							else if(tablero[2] == jugador) {tablero[0] = jugador;}
							else if(tablero[6] == jugador) {tablero[8] = jugador;}
							else if(tablero[8] == jugador) {tablero[6] = jugador;}
						}
						else if(tablero[5] == jugadorContrario) {
							if(tablero[0] == jugador) {tablero[2] = jugador;}
							else if(tablero[2] == jugador) {tablero[0] = jugador;}
							else if(tablero[6] == jugador) {tablero[8] = jugador;}
							else if(tablero[8] == jugador) {tablero[6] = jugador;}
						}
						else if(tablero[7] == jugadorContrario) {
							if(tablero[0] == jugador) {tablero[2] = jugador;}
							else if(tablero[2] == jugador) {tablero[0] = jugador;}
							else if(tablero[6] == jugador) {tablero[0] = jugador;}
							else if(tablero[8] == jugador) {tablero[2] = jugador;}
						}
					}
					else if(numPasoGenerico == 1.1 && tablero[4] == 0) {
						tablero[4] = jugador;	
						hecho = true;
					}
					if(numPasoGenerico == 1 && !hecho) {numPasoGenerico++;}
					if(numPasoGenerico == 2 && tablero[4] == 0 && !hecho) {
						do {
							pos = (int)(9*Math.random());
						}while(pos != 0 && pos !=2 && pos !=6 && pos != 8 || tablero[pos] != 0);
						tablero[pos] = jugador;
						hecho = true;
						numPasoGenerico += 0.1;
					}
					else if(numPasoGenerico == 2.1 && !hecho) {
						if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
						hecho = true;
					}
					if(numPasoGenerico == 2 && !hecho) {numPasoGenerico++;}
					if(numPasoGenerico == 3 && tablero[4] == jugadorContrario && !hecho) {
						if(tablero[0] == jugador) {tablero[8] = jugador;}
						else if(tablero[2] == jugador) {tablero[6] = jugador;}
						else if(tablero[6] == jugador) {tablero[2] = jugador;}
						else if(tablero[8] == jugador) {tablero[0] = jugador;}
						numPasoGenerico += 0.1;
						hecho = true;
					}
					else if(numPasoGenerico == 3.1 && !hecho) {
						do {
							pos = (int)(9*Math.random());
						}while(!(getMovimientoValido(pos)));
						tablero[pos] = jugador;
					}
				}
				//Casos sin control:
				if(!hecho) {
					for(int i = 0 ; i < 9 && hecho == false ; i++) {
						if(tablero[i] == 0) {
							tablero[i] = jugador;
							hecho = true;
						}
					}
				}
			}
		}
	}
}
