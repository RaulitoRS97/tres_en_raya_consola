package tres_en_raya_consola;
//Importamos paquetes necesarios:
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//Clase encargada de iniciar el programa principal, encarga de lanzar el programa en otra consola independientemente del sistema operativo en el que se encuentre.
//Testeado con 'Windows 10', y 'LinuxMint 19', puede que la version de linux respecto a la consola influya y si no es 'gnome-terminal' no funcione correctamente la lanzadera:
//Al crear el JAR, se le tiene que poner un nombre en especifico para que se pueda encontrar el proceso:
public class Lanzadera {
	//Metodo principal:
	public static void main(String[] args) {
		//Si es el Sistema operativo 'Windows 10':
		if(System.getProperty("os.name").toString().toUpperCase().contains("WINDOWS 10")) {
			try {
				//Para poder usar los codigos de escape ANSI, y asi poder imprimir colores en la CMD de 'Windows 10', debe existir una clave en el registro de Windows,
				//primero comprobamos que exista la clave en el registro, tanto como si no existe, como si tiene valor '0' la sobreescribiremos con valor '1':
				ProcessBuilder pbQueryREG = new ProcessBuilder("REG", "QUERY", "HKEY_CURRENT_USER\\Console", "/v", "VirtualTerminalLevel", "/t", "REG_DWORD");	//Creamos el proceso.
				Process pQuery = pbQueryREG.start();			//Lanzamos el proceso anterior, y lo guardamos en un objeto de clase 'Process' para poder trabajar con el proceso.
				InputStream isQuery = pQuery.getInputStream();	//Creamos un flujo de entrada de la salida de informacion que tendria lugar en la consola.
   				BufferedReader br = new BufferedReader(new InputStreamReader(isQuery));		//Creamos un 'BufferedReader' para poder leer el Stream anterior, linea a linea.
   				//Con un bucle 'While' obtenemos la salida obtenida, y si encontramos en alguna linea la clave, obtenemos el valor asociado a ella:
				String filaQuery = "";char valorQuery='\0';boolean pruebaQuery=false;	//Variables necesarias para poder realizar el proceso.
				while ((filaQuery = br.readLine()) != null && !pruebaQuery) {
					if(filaQuery.toUpperCase().contains("VirtualTerminalLevel")) {
						valorQuery=filaQuery.charAt(filaQuery.length()-1);
						pruebaQuery=true;
					}
				}
				br.close();isQuery.close();	//Cerramos flujos.
				//Si la clave no existe, o su valor es '0', pasamos a crearla o sobreescribirla:
				if(valorQuery=='0'||!pruebaQuery){
					//Lanzando este proceso anonimo conseguimos la tarea propuesta, importante que antes de seguir la ejecucion del flujo de este programa se termine el proceso lanzado:
					new ProcessBuilder("REG", "ADD", "HKEY_CURRENT_USER\\Console", "/v", "VirtualTerminalLevel", "/t", "REG_DWORD", "/d", "1", "/f").start().waitFor();
				}
				//Guardamos la localizacion del JAR que se esta ejecutando:
				String localizacionJAR = Lanzadera.class.getProtectionDomain().getCodeSource().getLocation().toString();
				String auxLocalizacion;
				//Tratamos la ruta obtenida para dejarla en formato de ruta Windows:
				auxLocalizacion=localizacionJAR.substring(6).replace('/','\\');
				localizacionJAR="\""+auxLocalizacion.replace("%20"," ")+"\"";
				//Creamos una nueva CMD, ya con el cambio de la clave del registro en uso, y lanzamos el programa principal dentro de nuestro jar (ademas ya sabemos la ubicacion absoluta de el):
				new ProcessBuilder("CMD","/C", "START", "Tres En Raya - Version 1.1", "/MAX", "java","-cp", localizacionJAR, "UsoTresEnRaya").start();
				//Buscamos el proceso de la CMD en la que se ha ejecutado la lanzadera, para matar su proceso y que desaparezca la ventana de la consola y solo quede la anterior CMD con el programa lanzado.
				//Para eso mostramos las tareas de CMD cuyo Titulo de ventana sea 'java lanzadera', y luego extraemos su PID para poder acabar el proceso:
				ProcessBuilder pbObtenerPID = new ProcessBuilder("TASKLIST", "/V", "/FI", "IMAGENAME EQ CMD.EXE", "/FO", "CSV", "/NH");	//Creamos el proceso.
				Process pObtenerPID = pbObtenerPID.start();			//Lanzamos el proceso anterior, y lo guardamos en un objeto de clase 'Process' para poder trabajar con el proceso.
				InputStream isPID = pObtenerPID.getInputStream();	//Creamos un flujo de entrada de la salida de informacion que tendria lugar en la consola.
				BufferedReader brPID = new BufferedReader(new InputStreamReader(isPID));	//Creamos un 'BufferedReader' para poder leer el Stream anterior, linea a linea.
				//Con un bucle 'While' obtenemos la salida obtenida, y si encontramos en alguna linea el proceso, obtenemos la fila donde se encuentran los datos de el:
				String filaPID="";String valorPID="";boolean pruebaPID=true;	//Variables necesarias para poder realizar el proceso.
				while((filaPID = brPID.readLine()) != null && pruebaPID){
					if(filaPID.toUpperCase().contains("JAVA")&&filaPID.toUpperCase().contains("TRESENRAYA.JAR")){
						valorPID=filaPID;
						pruebaPID=false;
					}
				}
				brPID.close();isPID.close();	//Cerramos flujos.
				//Con un bucle 'For' obtenemos el PID de la linea obtenida anteriormente, recorriendola caracter a caracter, como esta en formato CSV, solo tenemos que saber despues de que
				//numero de coma esta, lo parseamos a entero y si no da error guardamos el valor:
				int pid=-1;String aux="";pruebaPID=true;		//Variables necesarias para poder realizar el proceso.
				for(int i = 0, j = 0; i < valorPID.length() && pruebaPID; i++){
					if(valorPID.charAt(i)==','||valorPID.charAt(i)==10){
						if(j==1){
							try{
								pid=Integer.parseInt(aux);
								pruebaPID=false;
							}catch(NumberFormatException n){System.out.println(Messages.getString("Lanzadera.consola.error.busquedaPidWindows"));}
						}
						j++;
						aux="";
					}else if(valorPID.charAt(i)!='"'&&valorPID.charAt(i)!=13){
						aux+=Character.toString(valorPID.charAt(i));
					}
				}
				//Si se ha encontrado el PID del proceso requerido, pasamos a destruirlo.
				if(!pruebaPID) {
					//Finalizamos el proceso donde se encuentra la consola de lanzadera lanzando el siguiente proceso anonimo, ademas esperamos a que termine para seguir la ejecucion.
					new ProcessBuilder("TASKKILL", "/PID", pid+"").start().waitFor();
				}
			} catch (InterruptedException | IOException e) {	//Control de excepciones:
				System.out.println(Messages.getString("Lanzadera.consola.error.excepcionWindows"));
			}
		}
		//Si el sistema operativo es 'Linux':
		else if(System.getProperty("os.name").toString().toUpperCase().contains("LINUX")) {
            try{
            	//Guardamos la localizacion del JAR que se esta ejecutando:
				String localizacionJAR = Lanzadera.class.getProtectionDomain().getCodeSource().getLocation().toString();
				String auxLocalizacion;
				//Tratamos la ruta obtenida para dejarla en formato de ruta Linux:
				auxLocalizacion=localizacionJAR.substring(5);
				localizacionJAR="\""+auxLocalizacion.replace("%20"," ")+"\"";
                //Creamos una nueva bash, donde lanzamos el programa principal dentro de nuestro jar, en 'Linux' los codigos de escape ANSI estan habilitados en su consola por defecto:
			    new ProcessBuilder("gnome-terminal", "--maximize", "-t", "Tres En Raya - Version 1.1", "--", "/bin/bash", "-c", "java -cp "+localizacionJAR+" UsoTresEnRaya").start().waitFor();
			    //Buscamos el proceso de la bash que ha ejecutado la lanzadera, para matar su proceso y que desaparezca y solo quede la anterior bash con el programa lanzado.
			    //Para eso mostramos los procesos haciendo un filtrado con Grep y solo aparezca el que contenga 'java lanzadera', y luego extraemos su PID para poder acabar el proceso:
			    ProcessBuilder pbObtenerPID = new ProcessBuilder("/bin/bash", "-c", "ps -o pid,cmd | grep \'bash\' | grep -v \'grep\'");	//Creamos el proceso.
			    Process pObtenerPID = pbObtenerPID.start();			//Lanzamos el proceso anterior, y lo guardamos en un objeto de clase 'Process' para poder trabajar con el proceso.
			    InputStream isPID = pObtenerPID.getInputStream();	//Creamos un flujo de entrada de la salida de informacion que tendria lugar en la consola.
			    BufferedReader brPID = new BufferedReader(new InputStreamReader(isPID));	//Creamos un 'BufferedReader' para poder leer el Stream anterior, linea a linea.
                String valorPID=brPID.readLine();	//Leemos la primera coincidencia despues de haber filtrado con Grep.
			    brPID.close();isPID.close();		//Cerramos flujos.
			    //Si no esta vacio, pasamos a recorrer la linea obtenida con un bucle 'For', y buscamos el PID, como sabemos el formato y los espacios que hay entre las columnas de la linea, sabemos donde esta el PID:
		        int pid=0;String aux="";boolean pruebaPID=true;	//Variables necesarias para poder realizar el proceso.
                if(valorPID!=null){
			        for(int i = 0, j = 0; i < valorPID.length() && pruebaPID; i++){
				        if(valorPID.charAt(i)==' '){
					        if(j==1){
						        try{
							        pid=Integer.parseInt(aux);
							        pruebaPID=false;
						        }catch(NumberFormatException n){System.out.println(Messages.getString("Lanzadera.consola.error.busquedaPidLinux"));}
					        }
					        j++;
					        aux="";
				        }else {
					        aux+=Character.toString(valorPID.charAt(i));
				        }
			        }
                }
                //Si se ha encontrado el PID del proceso requerido, pasamos a destruirlo.
				if(!pruebaPID) {
					//Finalizamos el proceso donde se encuentra la consola de la lanzadera:
					new ProcessBuilder("/bin/bash", "-c", "kill -9 "+pid).start().waitFor();
				}
            } catch (InterruptedException | IOException e) {	//Control de excepciones:
				System.out.println(Messages.getString("Lanzadera.consola.error.excepcionLinux"));
            }
        }
	}
}
