package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Session {

	public static boolean Program = true;
	private static User user;
	private boolean logged;

	private static final String FILE_PATH = "./assets/files/";
	private static final String USERS_FILE = "users.txt";

	public Session() {
		logged = false;
	}

	public boolean isLogged() {
		return logged;
	}

	public boolean login() throws FileNotFoundException {
		String username = Input.getString("Nombre de usuario: ");
		String password = Input.getString("Contraseña: ");

		if (checkUser(username, password)) {
			logged = true;
			return true;
		}

		System.out.print("El usuario y/o contraseña son incorrectos");
		return false;
	}

	public void logout() {
		logged = false;
		System.out.print("\n---Sesión cerrada---\n");
	}

	public void showUserInfo() {
		if (user != null) {
			user.userInfo();
		}
	}

	public static boolean signup() throws FileNotFoundException {

		String username = Input.getString("Introduce nombre de usuario: ");
		if (userExists(username)) {
			System.out.print("El nombre de usuario ya está en uso");
			return false;
		}
		
		String password = Input.getString("Contraseña: ");
		int checkPass = password.length();
		
		String comfirmPass = Input.getString("Confirma la contraseña: ");
		if(!password.equals(comfirmPass)) {
			System.out.print("La contraseña no coincide!!");
			return false;
		}
		
		String name = Input.getString("Introduce tu nombre completo: ");
		int checkName = name.length();
		
		String nif = Input.getString("Introduce tu DNI/NIF: ");
		int checkNif = nif.length();
		
		String email = Input.getString("Introduce tu correo electrónico: ");
		int checkEmail = email.length();
		
		String address = Input.getString("Introduce tu dirección: ");
		int checkAddress = address.length();
		
		String birthdate = Input.getString("Introduce tu fecha de nacimiento: ");
		int checkBirth = birthdate.length();
		
		if (checkPass 	 == 0 || checkName 	    == 0 || 
			checkNif 	 == 0 || checkEmail 	== 0 || 
			checkAddress == 0 || checkBirth 	== 0) {
			
			System.out.print("\nFallo de registro: Algun registro no ha sido completado!!\n");
			
			return false;
			
	
		} else {
			String line = "\n" + username + "#" + password + "#" + 
		name + "#" + nif + "#" + email + "#" + address + "#" + birthdate + "#user";
			
			try (FileWriter writer = new FileWriter(FILE_PATH + USERS_FILE, true)) {
				writer.write(line);
				System.out.println("---Usuario creado con éxito!!!---");
			} catch (IOException e) {
				System.out.println("Error al crear el usuario");
			}
			return true;
		}
	}

	private static boolean userExists(String username) {
		File file = new File(FILE_PATH + USERS_FILE);

		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				if (reader.nextLine().split("#")[0].equalsIgnoreCase(username)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Usuario no encontrado BBDD");
		}

		return false;
	}

	public static boolean checkUser(String username, String password) {
		File file = new File(FILE_PATH + USERS_FILE);

		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				String[] w = reader.nextLine().split("#");
				if (w[0].equalsIgnoreCase(username) && w[1].equals(password)) {
					user = new User(w[0], w[2], w[3], w[4], w[5], w[6], w[7]);
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Usuario no encontrado BBDD");
		}

		return false;
	}
	
	

	public void exitProgram() {
		Program = false;
	}
}