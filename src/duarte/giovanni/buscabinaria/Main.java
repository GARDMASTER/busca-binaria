package duarte.giovanni.buscabinaria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	private static int contador = 0;
	private static int contadorConsultas = 0;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Scanner scanTxt = new Scanner(System.in);
		
		System.out.println("Bem vindo ao MasterCEP Searcher!!");
		System.out.println("\n");
		System.out.println("---------------------------------");
		System.out.println("| 1 - Realizar Busca Binária	|");
		System.out.println("| 0 - Sair do programa		|");
		System.out.println("---------------------------------");
		Integer opcao;
		System.out.println("Escolha a opção desejada: ");
		opcao = scan.nextInt();
		String sn = "";
		do {
			
			switch(opcao) {
			
			case 1:
				
				try {
					System.out.println("Informe o CEP (Somente números): ");
					String cep = scanTxt.nextLine().trim();
					RandomAccessFile f = buscaCepOrdenado();
					buscaBinaria(f, 0L, f.length(), cep);
				} catch (FileNotFoundException e) {
					System.err.println("Arquivo não encontrado.");
				} catch (IOException e) {
					System.err.println("Erro ao realizar busca binária.");
				} catch (CepNotFoundException e) {
					System.err.println(e.getMessage());
				}
				
				contador=0;
				System.out.println("Quantidade de consultas realizadas: "+(++contadorConsultas));
				
				System.out.println("Deseja realizar outra consulta? (s/n)");
				sn = scanTxt.nextLine();
				if(sn.equalsIgnoreCase("n")) {
					System.out.println("Bye bye!");
					opcao = 0;
				} else if (sn.equalsIgnoreCase("s")) {
					opcao = 1;
				} else {
					opcao = 3;
				}
				
				break;
				
			case 0:
				System.out.println("Bye bye!");
				System.exit(0);
			
			default:
				System.out.println("Comando não reconhecido.");
				System.out.println("Deseja realizar outra consulta? (s/n)");
				sn = scanTxt.nextLine();
				if(sn.equalsIgnoreCase("n")) {
					System.out.println("Bye bye!");
					opcao = 0;
				} else if (sn.equalsIgnoreCase("s")) {
					opcao = 1;
				} else {
					opcao = 3;
				}
				
				break;
			}
			
			
		}while (opcao != 0);

		scan.close();
		scanTxt.close();
	}
	
	public static void buscaBinaria(RandomAccessFile f, Long inicio, Long fim, String cep) throws IOException, CepNotFoundException{
		
		
		if(fim < inicio) {
			throw new CepNotFoundException("O CEP informado não existe!!!");
		}
		
		Endereco end = new Endereco();
		contador++;
		
		inicio = inicio / 300;
		
		fim = fim / 300;
		
		Long meio = (inicio + fim) / 2 ;
		
		f.seek(meio*300);
		end.leEndereco(f);
		
        if(end.getCep().equals(cep)) {
        	System.out.println(end.getLogradouro());
            System.out.println(end.getBairro());
            System.out.println(end.getCidade());
            System.out.println(end.getEstado());
            System.out.println(end.getSigla());
            System.out.println(end.getCep());
        	System.out.println("Quantidade de iterações: "+contador);
        } else if (end.getCep().compareToIgnoreCase(cep) > 0) {
        	buscaBinaria(f, inicio*300, f.getFilePointer()-1, cep);
        } else {
        	buscaBinaria(f, f.getFilePointer()+1, fim*300, cep);
        }
		
	}
	
	public static RandomAccessFile buscaCepOrdenado() throws FileNotFoundException{
		
		RandomAccessFile f = new RandomAccessFile("C:\\Users\\Sala\\workspace\\eclipse\\busca-binaria\\files\\cep_ordenado.dat", "r");
		
		return f;
	}
	
}
