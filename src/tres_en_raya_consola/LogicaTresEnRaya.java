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
		if(tablero[posicion-1] == 0) {tablero[posicion-1] = jugador;}
	}
	//Metodo get que devuelve un booleano en funcion de si se puede realizar el movimiento o no en la posicion pasada por parametro:
	//[TRUE = MOVIMIENTO VALIDO, FALSE = MOVIMIENTO INVALIDO]
	public boolean getMovimientoValido(int posicion) {
		return tablero[posicion-1] == 0;
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
	//Metodo privado que segun los parametros pasados, hace la defensa especial:
	//Si le pasas valor casilla '1' el jugador '2' intentara hacer la defensa final para no perder:
	//Si le pasas valor casilla '2' el jugador '1' intentara hacer la defensa final para no perder:
	//Devuelve un booleano para indicar si se pudo realizar un movimiento o no:
	private boolean realizaMovimientoDefensaEspecial(int valorCasilla) {
		int jugador;	//Variable que hace referencia al jugador, su valor se obtendra en funcion de el valor casilla pasado.
		if(valorCasilla == 1) {jugador = 2;}
		else {jugador = 1;}
		boolean hecho = false;	//Variable booleana para comprobar si se ha realizado el movimiento o no.
		//Se comprueba si hay una esquina disponible que creara dos caminos en caso de ser rellenada, si la hay se tapa:
		if(tablero[0] == 0 && tablero[1] == valorCasilla && tablero[3] == valorCasilla && tablero[2] == 0 && tablero[6] == 0) {tablero[0] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[1] == valorCasilla && tablero[6] == valorCasilla && tablero[2] == 0 && tablero[3] == 0) {tablero[0] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[2] == valorCasilla && tablero[3] == valorCasilla && tablero[1] == 0 && tablero[6] == 0) {tablero[0] = jugador; hecho = true;}
		else if(tablero[0] == 0 && tablero[2] == valorCasilla && tablero[6] == valorCasilla && tablero[1] == 0 && tablero[3] == 0
				&& !(tablero[8] == 0 && tablero[2] == valorCasilla && tablero[6] == valorCasilla && tablero[5] == 0 && tablero[7] == 0)) {tablero[0] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[1] == valorCasilla && tablero[5] == valorCasilla && tablero[0] == 0 && tablero[8] == 0) {tablero[2] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[0] == valorCasilla && tablero[5] == valorCasilla && tablero[1] == 0 && tablero[8] == 0) {tablero[2] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[1] == valorCasilla && tablero[8] == valorCasilla && tablero[0] == 0 && tablero[5] == 0) {tablero[2] = jugador; hecho = true;}
		else if(tablero[2] == 0 && tablero[0] == valorCasilla && tablero[8] == valorCasilla && tablero[1] == 0 && tablero[5] == 0
				&& !(tablero[6] == 0 && tablero[0] == valorCasilla && tablero[8] == valorCasilla && tablero[3] == 0 && tablero[7] == 0)) {tablero[2] = jugador; hecho = true;}
		else if(tablero[6] == 0 && tablero[3] == valorCasilla && tablero[7] == valorCasilla && tablero[0] == 0 && tablero[8] == 0) {tablero[6] = jugador; hecho = true;}
		else if(tablero[6] == 0 && tablero[3] == valorCasilla && tablero[8] == valorCasilla && tablero[0] == 0 && tablero[7] == 0) {tablero[6] = jugador; hecho = true;}
		else if(tablero[6] == 0 && tablero[0] == valorCasilla && tablero[7] == valorCasilla && tablero[3] == 0 && tablero[8] == 0) {tablero[6] = jugador; hecho = true;}
		else if(tablero[6] == 0 && tablero[0] == valorCasilla && tablero[8] == valorCasilla && tablero[3] == 0 && tablero[7] == 0
				&& !(tablero[2] == 0 && tablero[0] == valorCasilla && tablero[8] == valorCasilla && tablero[1] == 0 && tablero[5] == 0)) {tablero[6] = jugador; hecho = true;}
		else if(tablero[8] == 0 && tablero[7] == valorCasilla && tablero[5] == valorCasilla && tablero[2] == 0 && tablero[6] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[8] == 0 && tablero[2] == valorCasilla && tablero[7] == valorCasilla && tablero[5] == 0 && tablero[6] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[8] == 0 && tablero[5] == valorCasilla && tablero[6] == valorCasilla && tablero[2] == 0 && tablero[7] == 0) {tablero[8] = jugador; hecho = true;}
		else if(tablero[8] == 0 && tablero[2] == valorCasilla && tablero[6] == valorCasilla && tablero[5] == 0 && tablero[7] == 0
				&& !(tablero[0] == 0 && tablero[2] == valorCasilla && tablero[6] == valorCasilla && tablero[1] == 0 && tablero[3] == 0)) {tablero[8] = jugador; hecho = true;}
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
				//Si NO es o NO ha sido el primero, hace las siguientes estrategias, dependiendo del numero de paso en el que este su logica:
				if(!primeroGenerico) {
					hecho = true;	//Lo hacemos true ya que se va a realizar si o si un movimiento.
					//Comprobamos estar en el Primer paso, y si el oponente ha puesto su primera pieza en una esquina, se pondra una en el centro:
					if((tablero[4] == 0 && numPasoGenerico == 0)&&(tablero[1] == 0 && tablero[3] == 0 && tablero[5] == 0 &&tablero[7] == 0)) {
						tablero[4] = jugador;
						numPasoGenerico ++;
					}
					//Comprobamos estar en el Primer paso, y si el oponente ha puesto su primera pieza en el centro, se pondra una en una esquina:
					else if(tablero[4] == jugadorContrario && numPasoGenerico == 0) {
						//Mediante este bucle controlamos que el primer movimiento se produzca en una de las esquinas del tablero de manera aleatoria:
						do {
							pos = (int)(9*Math.random());
						}while(pos != 6 && pos != 0 && pos != 2 && pos != 8);
						tablero[pos] = jugador;
						numPasoGenerico += 2;
					}
					//Comprobamos estar en el Primer paso, y si el oponente ha puesto su primera pieza en un borde o arista, se pondra una en el centro:
					else if((tablero[4] == 0 && numPasoGenerico == 0)&&(tablero[0] == 0 && tablero[2] == 0 && tablero[6] == 0 && tablero[8] == 0)) {
						tablero[4] = jugador;
						numPasoGenerico += 2;
					}
					//Si el paso es Uno, es decir el oponente empezo en una esquina y le pusimos pieza en el centro, pasaremos a poner en un borde con posibilidad de ganar,
					//y si no quedan pues buscaremos una esquina con posibilidad de ganar, y sino, pues simplemente bordes o esquinas:
					else if(numPasoGenerico == 1) {
						if(!realizaMovimientoDefensaEspecial(jugadorContrario)) {
							if(tablero[1] == 0 && tablero[7] == 0) {tablero[1] = jugador;}
							else if(tablero[3] == 0 && tablero[5] == 0) {tablero[3] = jugador;}
							else if(tablero[5] == 0 && tablero[3] == 0) {tablero[5] = jugador;}
							else if(tablero[7] == 0 && tablero[1] == 0) {tablero[7] = jugador;}
							else if(tablero[0] == 0 && tablero[8] == 0) {tablero[0] = jugador;}
							else if(tablero[2] == 0 && tablero[6] == 0) {tablero[2] = jugador;}
							else if(tablero[6] == 0 && tablero[2] == 0) {tablero[6] = jugador;}
							else if(tablero[8] == 0 && tablero[0] == 0) {tablero[8] = jugador;}
							else if(tablero[0] == 0) {tablero[0] = jugador;}
							else if(tablero[2] == 0) {tablero[2] = jugador;}
							else if(tablero[6] == 0) {tablero[6] = jugador;}
							else if(tablero[8] == 0) {tablero[8] = jugador;}
							else if(tablero[1] == 0) {tablero[1] = jugador;}
							else if(tablero[3] == 0) {tablero[3] = jugador;}
							else if(tablero[5] == 0) {tablero[5] = jugador;}
							else if(tablero[7] == 0) {tablero[7] = jugador;}
						}
					}
					//Si el paso es Dos, es decir el oponente empezo en el centro o en una arista, y nosotros le pusimos en una esquina, pasaremos a poner en una esquina, o a un borde:
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
				}
				//Si es el que empieza o empezo primero, hara las siguientes estrategias en funcion del numero de paso en el que este:
				else {
					//Si es el primer movimiento, colocamos en una esquina:
					if(numPasoGenerico == 0) {
						//Mediante este bucle controlamos que el primer movimiento se produzca en una de las esquinas del tablero de manera aleatoria:
						do {
							pos = (int)(9*Math.random());
						}while(pos != 6 && pos != 0 && pos != 2 && pos != 8);
						tablero[pos] = jugador;
						hecho = true;
						numPasoGenerico++;
					}
					//Si es el paso Uno, y el jugador contrario ha colocado en alguna arista entramos:
					else if(numPasoGenerico == 1 && (tablero[1] == jugadorContrario || tablero[3] == jugadorContrario || tablero[5] == jugadorContrario || tablero[7] == jugadorContrario)){
						numPasoGenerico += 0.1;
						hecho = true;
						//Segun en la arista en la que haya colocado, y la esquina que nos otros hayamos colocado, se busca una esquina que sea contigua,
						//y que no tenga una arista del jugador contrario entre las dos esquinas:
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
					//Si es el paso Uno.Uno, se coloca en el centro:
					else if(numPasoGenerico == 1.1 && tablero[4] == 0) {
						tablero[4] = jugador;	
						hecho = true;
					}
					//Si el paso Uno y todavia no se ha realizado nada, aumentamos para pasar al paso Dos:
					if(numPasoGenerico == 1 && !hecho) {numPasoGenerico++;}
					//Si es el paso Dos, y el centro esta vacio, es decir el jugador ha movido a esquinas, nosotros colocamos en una esquina:
					if(numPasoGenerico == 2 && tablero[4] == 0 && !hecho) {
						do {
							pos = (int)(9*Math.random());
						}while(pos != 0 && pos !=2 && pos !=6 && pos != 8 || tablero[pos] != 0);
						tablero[pos] = jugador;
						hecho = true;
						numPasoGenerico += 0.1;
					}
					//Si es el paso Dos.Uno, se coloca en la ultima esquina libre:
					else if(numPasoGenerico == 2.1 && !hecho) {
						if(tablero[0] == 0) {tablero[0] = jugador;}
						else if(tablero[2] == 0) {tablero[2] = jugador;}
						else if(tablero[6] == 0) {tablero[6] = jugador;}
						else if(tablero[8] == 0) {tablero[8] = jugador;}
						hecho = true;
					}
					//Si es el paso Dos y todavia no se ha realizado nada, aumentamos para pasar al paso Tres:
					if(numPasoGenerico == 2 && !hecho) {numPasoGenerico++;}
					//Si es el paso Tres, y el centro esta ocupado, pasamos a mover a la esquina diagonal, segun la esquina que se haya seleccionado:
					if(numPasoGenerico == 3 && tablero[4] == jugadorContrario && !hecho) {
						if(tablero[0] == jugador) {tablero[8] = jugador;}
						else if(tablero[2] == jugador) {tablero[6] = jugador;}
						else if(tablero[6] == jugador) {tablero[2] = jugador;}
						else if(tablero[8] == jugador) {tablero[0] = jugador;}
						hecho = true;
					}
				}
				//Casos sin control o por finalizar:
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
		//Segun el jugador recibido como parametro, asignamos los valores de los valores genericos a los atributos privados para persistir la nueva informacion:
		if(jugador == 1) {
			primeroX = primeroGenerico; numPasoX = numPasoGenerico;
		}else {
			primeroO = primeroGenerico; numPasoO = numPasoGenerico;
		}
	}
	//Metodo get para dibujar el tablero de las 3 en raya, con la informacion actual:
	public void getDibujaTablero() {
		//Codigos de escape ANSI que representan diferentes colores, destacar que cada codigo tiene delante el codigo de reinicio '\u0001B[0m':
		final String ROJO_BRILLANTE="\u001B[0m\033[0;91m", VERDE_BRILLANTE="\u001B[0m\033[1;32m", CYAN_BRILLANTE="\u001B[0m\033[0;96m", BLANCO="\u001B[0m\033[37m", NEGRO_BRILLANTE="\u001B[0m\033[1;30m", AMARILLO_BRILLANTE="\u001B[0m\033[0;93m";
		//Variables contadores:
		int i, j;
		//Variables que contendran las posiciones de las fichas del jugador que gana la partida:
		int pos1 = 0, pos2 = 0, pos3 = 0;
		//Definimos diferentes Arrays de String para dar forma a las figuras del juego:
		String[] jugadorXGanador = {VERDE_BRILLANTE+"  ██  ██  ",VERDE_BRILLANTE+"    ██    ",VERDE_BRILLANTE+"  ██  ██  "};
		String[] jugadorOGanador = {VERDE_BRILLANTE+"  ██████  ",VERDE_BRILLANTE+"  ██  ██  ",VERDE_BRILLANTE+"  ██████  "};
		String[] jugadorX = {CYAN_BRILLANTE+"  ██  ██  ",CYAN_BRILLANTE+"    ██    ",CYAN_BRILLANTE+"  ██  ██  "};
		String[] jugadorO = {ROJO_BRILLANTE+"  ██████  ",ROJO_BRILLANTE+"  ██  ██  ",ROJO_BRILLANTE+"  ██████  "};
		String[] vacio = {"          ","          ","          "};
		String aux = "";
		//Almacenamos las posiciones de las fichas del jugador que gana la partida para poder imprimir la combinacion ganadora:
		if(getGanaJugador(1)) {
			if(tablero[0] == 1 && tablero[1] == 1 && tablero[2] == 1) {pos1 = 0; pos2 = 1; pos3 = 2;}
			else if(tablero[3] == 1 && tablero[4] == 1 && tablero[5] == 1) {pos1 = 3; pos2 = 4; pos3 = 5;}
			else if(tablero[6] == 1 && tablero[7] == 1 && tablero[8] == 1) {pos1 = 6; pos2 = 7; pos3 = 8;}
			else if(tablero[0] == 1 && tablero[3] == 1 && tablero[6] == 1) {pos1 = 0; pos2 = 3; pos3 = 6;}
			else if(tablero[1] == 1 && tablero[4] == 1 && tablero[7] == 1) {pos1 = 1; pos2 = 4; pos3 = 7;}
			else if(tablero[2] == 1 && tablero[5] == 1 && tablero[8] == 1) {pos1 = 2; pos2 = 5; pos3 = 8;}
			else if(tablero[0] == 1 && tablero[4] == 1 && tablero[8] == 1) {pos1 = 0; pos2 = 4; pos3 = 8;}
			else if(tablero[2] == 1 && tablero[4] == 1 && tablero[6] == 1) {pos1 = 2; pos2 = 4; pos3 = 6;}
		}
		else if(getGanaJugador(2)) {
			if(tablero[0] == 2 && tablero[1] == 2 && tablero[2] == 2) {pos1 = 0; pos2 = 1; pos3 = 2;}
			else if(tablero[3] == 2 && tablero[4] == 2 && tablero[5] == 2) {pos1 = 3; pos2 = 4; pos3 = 5;}
			else if(tablero[6] == 2 && tablero[7] == 2 && tablero[8] == 2) {pos1 = 6; pos2 = 7; pos3 = 8;}
			else if(tablero[0] == 2 && tablero[3] == 2 && tablero[6] == 2) {pos1 = 0; pos2 = 3; pos3 = 6;}
			else if(tablero[1] == 2 && tablero[4] == 2 && tablero[7] == 2) {pos1 = 1; pos2 = 4; pos3 = 7;}
			else if(tablero[2] == 2 && tablero[5] == 2 && tablero[8] == 2) {pos1 = 2; pos2 = 5; pos3 = 8;}
			else if(tablero[0] == 2 && tablero[4] == 2 && tablero[8] == 2) {pos1 = 0; pos2 = 4; pos3 = 8;}
			else if(tablero[2] == 2 && tablero[4] == 2 && tablero[6] == 2) {pos1 = 2; pos2 = 4; pos3 = 6;}
		}
		//Mostramos el tablero por pantalla:
		System.out.println(NEGRO_BRILLANTE+"┌──────────────────────────────────────────────────────────┐");
		System.out.println("│                          "+AMARILLO_BRILLANTE+"TABLERO                         "+NEGRO_BRILLANTE+"│");
		System.out.println("│         "+BLANCO+"┌────────────┬────────────┬────────────┐         "+NEGRO_BRILLANTE+"│");
		System.out.println("│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
		//Bucle for encargado de imprimir parte del tablero, segun la informacion contenida actual:
		for(i = 0, j = 0 ; i < 9 ; i++) {
			if(i == 0 || i == 3 || i == 6) {
				aux += BLANCO+"│ ";			//Impresion de los separadores laterales izquierdos de las casillas.
			}
			if(tablero[i] == 0) {
				aux += vacio[j];			//Si la casilla esta vacia, se imprime la figura vacia.
				if(i == 2 || i == 5 || i == 8) {
					aux += BLANCO+" │";		//Impresion de los separadores laterales derechos de las casillas.
				}
				else {
					aux += BLANCO+" │ ";	//Impresion de los separadores centrales de las casillas.
				}
			}
			else if(tablero[i] == 1) {		//Si la casilla contiene una X, se imprime su figura, dependiendo de si ha ganado o no se utiliza una u otra.
				if(getGanaJugador(1) && (pos1 == i || pos2 == i || pos3 == i)) {
					aux += jugadorXGanador[j];
				}
				else{
					aux += jugadorX[j];
				}
				if(i == 2 || i == 5 || i == 8) {
					aux += BLANCO+" │";		//Impresion de los separadores laterales derechos de las casillas.
				}
				else {
					aux += BLANCO+" │ ";	//Impresion de los separadores centrales de las casillas.
				}
			}
			else if(tablero[i] == 2){		//Si la casilla contiene una O, se imprime su figura, dependiendo de si ha ganado o no se utiliza una u otra.
				if(getGanaJugador(2) && (pos1 == i || pos2 == i || pos3 == i)) {
					aux += jugadorOGanador[j];
				}
				else {
					aux += jugadorO[j];
				}
				if(i == 2 || i == 5 || i == 8) {
					aux += BLANCO+" │";		//Impresion de los separadores laterales derechos de las casillas.
				}
				else {
					aux += BLANCO+" │ ";	//Impresion de los separadores centrales de las casillas.
				}
			}
			if(i == 2 || i == 5 || i == 8) {	//Cuando se tenga esa linea concatenada la pasamos a imprimir:
				System.out.println(NEGRO_BRILLANTE+"│         " + aux + "         "+NEGRO_BRILLANTE+"│");
				//Reiniciamos valores para la siguiente linea, siempre que no sea la ultima linea antes de la siguiente casilla:
				if(j < 2) {
					if(i == 2) {i = -1;}
					else if(i == 5) {i = 2;}
					else {i = 5;}
					j++;
					aux = "";
				}
				//Si es la ultima linea antes de la siguiente casilla, se muestra en pequeño los numeros que hacen referencia a esa casilla:
				else {
					System.out.println(NEGRO_BRILLANTE+"│         "+BLANCO+"│          " +AMARILLO_BRILLANTE+(i-1) + " "+BLANCO+"│          " +AMARILLO_BRILLANTE+ (i) + " "+BLANCO+"│          " +AMARILLO_BRILLANTE+(i+1) + " "+BLANCO+"│         "+NEGRO_BRILLANTE+"│");
					//Si no es la ultima linea divisoria se imprime la siguiente estructura:
					if(i < 8) {
						System.out.println(NEGRO_BRILLANTE+"│         "+BLANCO+"├────────────┼────────────┼────────────┤         "+NEGRO_BRILLANTE+"│");
						System.out.println(NEGRO_BRILLANTE+"│         "+BLANCO+"│            │            │            │         "+NEGRO_BRILLANTE+"│");
					}
					//Si es la ultima linea divisoria se imprime la siguiente estructura:
					else {
						System.out.println(NEGRO_BRILLANTE+"│         "+BLANCO+"└────────────┴────────────┴────────────┘         "+NEGRO_BRILLANTE+"│");
						System.out.println("│                                                          │");
					}
					//Reiniciamos valores:
					j = 0;
					aux = "";
				}
			}
		}
	}
}
