package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		//Leitura do arquivo e Instanciação da classe Sale
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Sale> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), 
						Integer.parseInt(fields[1]), fields[2], 
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
				}
				
			//Uso de Lambda para resolver o problema
			Set<String> names = new HashSet<>();
					names = list.stream()
					.map(l -> l.getSeller())
                    .collect(Collectors.toSet());
				System.out.println("Total de vendas por vendedor: " );
				for (String seller: names) {
					double sum = list.stream()
							.filter(s -> s.getSeller().equals(seller))
							.map(s -> s.getTotal())
							.reduce(0.0, (x, y) -> x + y);
					
				 System.out.printf("%s - R$ %.2f\n", seller, sum);
				}
			    
			   
		} catch (IOException e) {
			System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado)");
		}
		
		sc.close();
	}

}
