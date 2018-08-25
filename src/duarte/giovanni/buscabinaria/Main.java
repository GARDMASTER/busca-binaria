package duarte.giovanni.buscabinaria;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Bem vindo ao MasterCEP Searcher");
		System.out.println("\n");
		System.out.println("---------------------------------");
		System.out.println("| 1 - Realizar Busca Binária	|");
		System.out.println("| 0 - Sair do programa		|");
		System.out.println("---------------------------------");
		Integer opcao;
		
		do {
			System.out.println("Escolha a opção desejada: ");
			opcao = scan.nextInt();
			
			switch(opcao) {
			
			case 1:
				//chamar metodo
			
				break;
				
			case 0:
				System.out.println("Bye bye!");
				System.exit(0);
			
			default:
				System.out.println("Comando não reconhecido.");
				break;
			}
			
			
		}while (opcao != 0);

		
	}
	
}
