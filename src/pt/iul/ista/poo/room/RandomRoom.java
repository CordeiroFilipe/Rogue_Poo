package pt.iul.ista.poo.room;

import java.io.*;
import java.util.Random;

public class RandomRoom {

	public void generateRoom(){
		String filename = "rooms\\room7.txt";
		File file = new File(filename);
		Random r = new Random();
		int rand = 50;
		boolean G = false;
		boolean B = false;
		boolean T = false;
		boolean S = false;
		boolean h = false;
		boolean s = false;
		boolean V = false;
		boolean F = false;
		try {
			if(!file.exists()){
				System.out.println("Sala aleatoria criada!");
			}
			file.createNewFile();
		
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
			
			pw.println("#");
			pw.println("# 0 D room0.txt 1");
			pw.println("#");
			
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++){
					if(i == 0 || i == 9){
						pw.print("W");
					}
					else if(j == 0 || j == 9){
						if(i == 5 && j == 9){
							pw.print("0");
						}
						else{
							pw.print("W");
						}
					}
					else if(r.nextInt(5) == 1 && j != 8){
						pw.print("W");
					}
					else if(r.nextInt(rand) == 1){
						if(!B){
							pw.print("B");
							B = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(rand) == 1){
						if(!G){
							pw.print("G");
							G = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(rand) == 1){
						if(!S){
							pw.print("S");
							S = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(rand) == 1){
						if(!T){
							pw.print("T");
							T = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(rand) == 1){
						if(!s){
							pw.print("s");
							s = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(rand) == 1){
						if(!h){
							pw.print("h");
							h = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(100) == 1){
						if(!V){
							pw.print("V");
							V = true;
						}
						else{
							pw.print(" ");
						}
					}
					else if(r.nextInt(100) == 1){
						if(!F){
							pw.print("F");
							F = true;
						}
						else{
							pw.print(" ");
						}
					}
					else{
						pw.print(" ");
					}
				}
				pw.println("");
			}
			
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}