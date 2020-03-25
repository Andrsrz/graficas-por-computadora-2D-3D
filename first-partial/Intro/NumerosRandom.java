import java.util.Random;
public class NumerosRandom{
	public static void main(String[] args) {
		Random md = new Random();
		int num1, num2;
		num1 = (int)(md.nextDouble() * 100);
		num2 = (int)(md.nextDouble() * 100);

		System.out.println("Primer número generado: " + num1);
		System.out.println("Segundo número generado: " + num2);
		
		if(num1 > num2){
			System.out.println("El primer número es mayor");
		}else{
			System.out.println("El segundo número es mayor");
		}
	}
}