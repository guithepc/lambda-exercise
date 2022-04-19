package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Application {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the file path: ");

		String path = sc.nextLine();
		

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
			System.out.println("Enter salary: ");
			double salary =sc.nextDouble(); 
			List<Employee> list = new ArrayList<>();

			String line = bf.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = bf.readLine();
			}
			System.out.println("Email of people whose salary is more than " + salary);
			
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			System.out.println(emails);
			
			double sum = list.stream().filter(e -> e.getName().startsWith("M"))
					.map(e -> e.getSalary())
					.reduce(0.0, (x, y ) -> x+y);
			
			System.out.println("Salary of people whom starts with 'M'" + sum);
			
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

}
