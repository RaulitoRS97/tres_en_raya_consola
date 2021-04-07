package tres_en_raya_consola;
//Importamos paquetes necesarios:
import java.util.MissingResourceException;
import java.util.ResourceBundle;
//Clase que contiene un Bundle que apunta a nuestro archivo 'messages.properties' el cual contiene los Strings externalizados de este proyecto:
public class Messages {
	//Definimos la ruta de nuestro archivo 'messages.properties':
	private static final String BUNDLE_NAME = "Principal.messages";
	//Creamos nuestro bundle de recursos apuntando a la ruta anteriormente indicada:
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	//Constructor:
	private Messages() {
	}
	//Metodo estatico por el cual obtendremos el String requerido pasando la clave que lo referencia.
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "La clave " + key + " no ha podido ser encontrada.";
		}
	}
}
