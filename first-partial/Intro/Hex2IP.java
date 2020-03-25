import java.util.Arrays;
public class Hex2IP{
	public static void main(String[] args){
		for (String s : args){
			String hex = "-hex", ip = "-ip";
			if (s.equals(hex)){
				System.out.println("Convertir Hexadecimal a IP: ");
			}else if (s.equals(ip)){
				System.out.println("Convertir IP a Hexadecimal: ");
			}else{
				System.out.println("Los parámetros son incorrectos");
			}
		}
	}
}