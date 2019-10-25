import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	public static char[][] plateau = new char[8][8];
	public static int[] position = new int[2];
	public static char[] dirStock = { 'E', 'S', 'O', 'N' };
	public static int countDir = 0;
	public static char direction = dirStock[countDir];
	public static int countPos = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialisation();
		afficherPlateau();
		while (true) {
			ArrayDeque<String> instruct = creationFile();
			deplacement(instruct);
		}
	}

	public static void initialisation() {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				plateau[i][j] = ' ';
			}
		}

		position[0] = 7;
		position[1] = 0;
	}

	public static void afficherPlateau() {
		// TODO: Implémenter cette fonction.

		plateau[position[0]][position[1]] = 'X';

		// première ligne
		System.out.print(" ");
		System.out.print(" ");
		for (int k = 0; k <= 7; k++) {
			System.out.print(k);
		}
		System.out.println();

		// deuxième ligne
		System.out.print(" ");
		System.out.print("+");
		for (int k = 0; k <= 7; k++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println();

		// tableau
		for (int i = 0; i <= 7; i++) {
			System.out.print(i + "|");
			for (int j = 0; j <= 7; j++) {
				System.out.print(plateau[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}

		// dernière ligne
		// deuxième ligne
		System.out.print(" ");
		System.out.print("+");
		for (int k = 0; k <= 7; k++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println();

		plateau[position[0]][position[1]] = ' ';
	}

	public static ArrayDeque<String> creationFile() {
		ArrayDeque<String> file = new ArrayDeque<String>();
		int count = 0;
		int rest = 5 - count;
		while (count != 5) {
			boolean verif = true;
			String choix = "";
			do {
				rest = 5 - count;
				System.out.println(
						"Instruction: A = avancer; G = se tourne vers la gauche; D = se tourne vers la droite, encore "
								+ rest + " instruction à choisir");
				choix = scanner.nextLine();
				if (choix.equals("A") || choix.equals("G") || choix.equals("D")) {
					verif = false;
				} else {
					System.out.println("Saisie invalide");
				}
			} while (verif);
			file.add(choix);
			count = count + 1;
		}
		System.out.println("Saisie des 5 instructions terminé");
		return file;
	}

	public static void deplacement(ArrayDeque<String> instructions) {
		for (int i = 0; i <= 4; i++) {
			String instructEnCour = instructions.poll();
			System.out.println("Instruction en cours : " + instructEnCour);
			if (instructEnCour.equals("D")) {
				countDir += 1;
				if (countDir > 3) {
					countDir -= 4;
				}
				direction = dirStock[countDir];
			} else if (instructEnCour.equals("G")) {
				countDir -= 1;
				if (countDir < 0) {
					countDir += 4;
				}
				direction = dirStock[countDir];
			} else if (instructEnCour.equals("A")) {
				if (direction == 'E') {
					position[1] = position[1] + 1;
					if (position[1] > 7) {
						position[1] = position[1] - 1;
						System.out.println("Instruction invalide, donc non executé");
					}
				} else if (direction == 'S') {
					position[0] = position[0] + 1;
					if (position[0] > 7) {
						position[0] = position[0] - 1;
						System.out.println("Instruction invalide, donc non executé");
					}
				} else if (direction == 'O') {
					position[1] = position[1] - 1;
					if (position[1] < 0) {
						position[1] = position[1] + 1;
						System.out.println("Instruction invalide, donc non executé");
					}
				} else if (direction == 'N') {
					position[0] = position[0] - 1;
					if (position[0] < 0) {
						position[0] = position[0] + 1;
						System.out.println("Instruction invalide, donc non executé");
					}
				}
			}
			afficherPlateau();
		}
	}
}
