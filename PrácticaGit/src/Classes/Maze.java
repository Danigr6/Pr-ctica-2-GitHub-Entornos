package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	public static Scanner teclado = new Scanner(System.in);
	private int lab;
	public char[][] map;
	private String filename;
	private boolean loaded = false;
	private int rows, cols, startI, startJ, endI, endJ;

	public Maze() {
		
	}

	public void loadMaze() {
		filename = selectLab();
		readFile(filename);
		loaded = true;
		System.out.print("\nMapa cargado correctamente!!\n");

	}
	public void clearMaze() {
		map = null;
		loaded = false;
		filename = null;
	}

	private String selectLab() {
		do {
			lab = Input.getInt("\n--Seleccione laberinto--\n1º Laberinto 1\n2º Laberinto 2\n3º Laberinto 3\n4º Laberinto 4\n\nOpción seleccionada: ");
		} while (lab < 1 || lab > 4);

		return "./assets/mazes/laberinto" + lab + ".txt";
	}

	private void readFile(String filename) {
		ArrayList<String> lines = new ArrayList<>();
		File myObj = new File(filename);

		try (Scanner myReader = new Scanner(myObj)) {
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				lines.add(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha podido cargar el mapa");
		}

		buildMap(lines);
	}

	private void buildMap(ArrayList<String> lines) {
		rows = lines.size();
		cols = lines.get(0).length();
		map = new char[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = lines.get(i).charAt(j);
			}
		}
	}

	public void showMaze() {
		if(loaded != true) {
			System.out.print("\nNo se ha cargado ningún laberinto");
			return;
		}
	
		System.out.print("    ");
		for(int j = 0; j < cols; j++) {
			if (j >= 10) {
                System.out.print(j / 10 + " ");
            } else {
                System.out.print("  "); 
            }
		}
		System.out.println();
		
		System.out.print("    ");
		for(int j = 0; j < cols; j++) {
			System.out.print(j % 10 + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < rows; i++) {
            if(i < 10) {
            	System.out.print(" " + i + "  ");
            } else {
            	System.out.print(i + "  ");
            }
			
			for (int j = 0; j < cols; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
	}

	public void setStartEnd() {
		if(loaded != true) {
			System.out.print("\nNo se ha cargado ningún laberinto");
			return;
		}
		for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[i].length; j++) {

	            if (map[i][j] == 'I' || map[i][j] == 'F') {
	            	map[i][j] = ' '; 
	            }
	        }
	    }
	
		System.out.print("\nPara establecer la entrada indica las coordenadas\n");
		do {
			startI = Input.getXY("Valor de I: ");
			startJ = Input.getXY("Valor de J: ");
			if(startI >= map.length || startJ >= map[0].length|| startI < 0 || startJ < 0){
				 System.out.print("\nValor no válido!!!\n");
			} else if(map[startI][startJ] == '#') {
				System.out.print("\nLas coordenadas coinciden con un muro\n");	
			} else {
				map[startI][startJ] = 'I';
				break;
			}
		} while (true);
		
		System.out.print("\nPara establecer la salida indica las coordenadas\n");
		do {
			endI = Input.getXY("Valor de I: ");
			endJ = Input.getXY("Valor de J: ");
			if(endI >= map.length || endJ >= map[0].length || endI < 0 || endJ < 0 || map[endI][endJ] == 'I'){
				 System.out.print("\nValor no válido!!!\n");
			} else if(map[endI][endJ] == '#') {
				System.out.print("Las coordenadas coinciden con un muro\n");
			
			} else {
				map[endI][endJ] = 'F';
				break;	
			}
		} while (true);
		
		System.out.print("\nEntrada y salida integrada correctamente!!!\n");
		
	}
}
