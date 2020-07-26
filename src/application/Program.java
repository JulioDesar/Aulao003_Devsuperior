package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;
import entities.IdHasExistsException;
import entities.IdNotExistException;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner cap = new Scanner(System.in);
		List<Employee> list = new ArrayList<>();

		System.out.print("How many employees will be registered? ");
		int n = cap.nextInt();

		try {

			for (int i = 0; i < n; i++) {

				System.out.println();
				System.out.println("Employee #" + (i + 1) + ":");

				System.out.print("Id: ");
				int id = cap.nextInt();

				hasId(list, id);

				cap.nextLine();

				System.out.print("Name: ");
				String name = cap.nextLine();

				System.out.print("Salary: ");
				double salary = cap.nextDouble();

				list.add(new Employee(id, name, salary));

			}

			System.out.println();
			System.out.print("Enter the employee id that will have salary increase : ");
			int newId = cap.nextInt();

			Integer pos = position(list, newId);

			if (pos != null) {

				System.out.print("Enter the percentage: ");
				double percent = cap.nextDouble();
				list.get(pos).increaseSalary(percent);
			}

			System.out.println();
			System.out.println("List of employees:");

			for (Employee e : list) {
				System.out.println(e);
			}

		} catch (IdHasExistsException e) {

			System.out.print(e.getMessage());

		} catch (IdNotExistException e) {

			System.out.println(e.getMessage());
			
			System.out.println();
			System.out.println("List of employees:");
			for (Employee p : list) {
				System.out.println(p);
			}

		} catch (RuntimeException e) {

			System.err.print("Unexpected error!");

		}

		cap.close();
	}

	public static Integer position(List<Employee> list, int id) throws IdNotExistException {

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getId() == id) {

				return i;

			}
		}

		throw new IdNotExistException("This Id does not Exist");
	}

	public static boolean hasId(List<Employee> list, int id) throws IdHasExistsException {

		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

		if (emp != null) {

			throw new IdHasExistsException("This Id already taken!");

		} else {

			return emp != null;

		}
	}

}
