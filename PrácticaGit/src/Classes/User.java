package Classes;

public class User {

	private String username;
	private String name;
	private String nif;
	private String email;
	private String address;
	private String birthdate;
	private String role;

	public User(String username, String name, String nif, String email, String address, String birthdate, String role) {
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = role;

	}


	public void userInfo() {
		System.out.print("\n---USUARIO ACTUAL---\n" + "Usuario: " + this.username + "\nNombre completo: " + this.name
				+ "\nNif/DNI: " + this.nif + "\nCorreo electrónico: " + this.email + "\nDireccion: " + this.address
				+ "\nFecha de nacimiento: " + this.birthdate + "\nRol de la cuenta: " + this.role + "\n----------------------\n");
	}

}
