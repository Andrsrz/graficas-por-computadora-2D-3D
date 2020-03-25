import java.util.*;
public class CadenaOrden{
	public static void main(String[] args) {
		for(String s : args){
			for (int i=0; i<s.length(); i++) {
				System.out.println(s.substring(i));
			}
			String cadena = new StringBuilder(s).reverse().toString();
			for (int a=0; a<cadena.length(); a++) {
				String cadena_2 = (cadena.substring(a+1));
				String cadena_3 = new StringBuilder(cadena_2).reverse().toString();
				System.out.println(cadena_3);
			}
		}
	}
}