import java.util.*;

class SomarDoisNumeros {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		//declara��o de variaveis
		int num1, num2, soma;
		
		//leituras
		System.out.println("Digite um n�mero");
		num1 = sc.nextInt();
		System.out.println("Digite outro n�mero");
		num2 = sc.nextInt();
		
		//Somar
		soma = num1+num2;
		
		//Mostrar na tela
		System.out.println(soma);
	}
}
