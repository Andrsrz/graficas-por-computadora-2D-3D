import java.util.Arrays;
import java.util.Collections;
public class NumerosOrden{
	public static void main(String[] args) {
		Arrays.sort(args, Collections.reverseOrder());
		for(String s : args){
			System.out.println(s);
		}
	}
}