package pt.iul.ista.poo.jogo;

import java.io.*;
import java.util.*;

import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.utils.*;
import pt.iul.ista.poo.status.*;

public class GameOver {
	
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private boolean alive = true;
	private boolean animating = false;
	private boolean win = false;
	private String filename = "Scores\\scores.txt";
	
	public GameOver(){
		File file = new File(filename);
		try {
			if(!file.exists()){
				System.out.println("Criei o ficheiro: " + file.getName());
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gameOver(int npassos, int nkills, int nMortes){
		Scanner sc = new Scanner(System.in);
		System.out.println("---!---GAME OVER---!---");
		alive = false;
		System.out.println("Diga o seu nome: ");
		String p = sc.next();
		System.out.println(p + ", a tua pontuação foi: " + npassos + " conseguiste matar " + nkills + " inimigos e morreste " + nMortes + " vezes!");
		sc.close();
		if(win){
			addScore(p, npassos, nkills, nMortes, "Won");
		}
		else{
			addScore(p, npassos, nkills, nMortes, "Lost");
		}
		try {
			mostraScore();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addScore(String n, int npassos, int nkills, int nMortes, String w){
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
			pw.println(n + " : " + npassos + " : " + nkills + " : " + nMortes + " : " + w);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraScore() throws FileNotFoundException{
		System.out.println("-------------------------Score List-------------------");
		System.out.println(" Nome  :  Npassos  :  Nkills  :  Nmortes  :  Win/Lose ");
		System.out.println("------------------------------------------------------");
		Scanner sc = new Scanner(new File(filename));
		while(sc.hasNextLine()){
			String p = sc.nextLine();
			System.out.println(p);
		}
		sc.close();
	}

	public void animation(){
		animating = true;
		int start = 0; 
		int end = 9; 
		for(int i = 0; i < 10; i++){ 
			for(int j = 0; j < 10; j++){ 
				gui.addImage(new Black(new Position(i,j)));
				if(start == end && j == i){
					gui.addImage(new Red(new Position(i,j)));
				}
				else if(start == j || end == j) {
					gui.addImage(new Red(new Position(i,j)));
				}
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		start++; 
		end--; 
		}
	}
	
	public void Win(int allEnemies, int npassos, int nkills, int nMortes){
		if(allEnemies == 0){
			win = true;
			System.out.println("**************PARABÉNS!****************");
			System.out.println("**********CONCLUISTE O JOGO!!!*********");
			winAnimation();
			gameOver(npassos, nkills, nMortes);
		}
	}

	public void winAnimation() {
		animating = true;
		Random r = new Random();
		for(int i = 0; i < 100; i++){ 
			int y = r.nextInt(10);
			int x = r.nextInt(10);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.addImage(new Win(new Position(x,y)));
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isAnimating() {
		return animating;
	}
}