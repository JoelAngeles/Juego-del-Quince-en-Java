package quince;

import java.util.Random;
import java.util.Scanner;

public class Inicio {
	public static void main(String[] args) {
		
		int [][] quince = new int[6][6];
		
		saludo();
		llenaQuince(quince);
		juegaQuince(quince);
		ganaste();
	}
	
	public static void saludo(){
		System.out.println("-------------------------------");
		System.out.println("\tJUEGO DEL 15");
		System.out.println("-------------------------------");
	}
	
	public static void llenaQuince(int[][] quince) {
		
		Random r = new Random();
		int[] nums = new int[16];
		int z;
		
		for(int i=0;i<16;i++)
			nums[i] = i;
		
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {				
				if(i==0 || i==5 || j==0 || j==5)
					quince[i][j] = -1;
				else {
					do {
						z = r.nextInt(16);
					}while(nums[z]==-1);
					
					quince[i][j] = z;
					nums[z] = -1;
				}
			}
		}
	}
	
	private static void arreglaQuince(int[][] quince) {
	int num = 1;
	
	for(int i=0;i<6;i++) {
		for(int j=0;j<6;j++) {
			
			if(i==0 || i==5 || j==0 || j==5) 
				quince[i][j] = -1;
			else {	
				if(num==16)
					num=0;
				quince[i][j] = num;
			num++;
			}
		}
	}
}

	private static void muestraQuince(int [][] quince) {
	for(int i=0;i<6;i++) {
		for(int j=0;j<6;j++) {
			
			if(i==0 || i==5 || j==0 || j==5);
			else {
				if(quince[i][j]==0)
					System.out.printf("|    |  ");
				else
				System.out.printf("| %2d |  ",quince[i][j]);
			}	
			}
		System.out.println("\n");
	}
}

	private static boolean sonIguales(int[][] quince, int[][] buena) {
		boolean ganas = true;
		
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				if(quince[i][j]!=buena[i][j])
					ganas = false;
			}
		}
		return ganas;
	}
	
	private static int leerMovimiento(Scanner leer) {
		String r;
		int i=0;
		do {
			r = leer.nextLine();
			try{
				  i= Integer.parseInt(r);
				}catch(Exception e) {}
			if(i<1 || i>15)
				System.out.println("Elige una casilla correcta!");
		}while(i<1 || i>15);
		
		return i;
	}
	
	private static boolean puedoMover(int[][] quince,int r) {
		boolean puedo = false;
		int[] coo_casilla = new int[2];
		coo_casilla = encuentraCoordenada(quince,r);
		
		if(quince[coo_casilla[0]][coo_casilla[1]-1]==0)
			puedo = true;
		if(quince[coo_casilla[0]][coo_casilla[1]+1]==0)
			puedo = true;
		if(quince[coo_casilla[0]-1][coo_casilla[1]]==0)
			puedo = true;
		if(quince[coo_casilla[0]+1][coo_casilla[1]]==0)
			puedo = true;
		
		return puedo;
	}
	
	private static int[] encuentraCoordenada(int[][] quince, int n) {
		int[] coordenada = new int[2];
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				if(quince[i][j]==n) {
					coordenada[0] = i;
					coordenada[1] = j;
					i=6;
					j=6;
				}
			}
		}
		return coordenada;
	}
	
	private static void mueveQuince(int[][] quince, int r) {
		int[] coo_casilla = new int[2];
		int[] coo_cero = new int[2];
		coo_casilla = encuentraCoordenada(quince,r);
		coo_cero = encuentraCoordenada(quince,0);
		quince[coo_casilla[0]][coo_casilla[1]] = 0;
		quince[coo_cero[0]][coo_cero[1]] = r;
	}
	
	private static void ganaste() {
		System.out.println("FELICIDADES, GANASTE");
	}
	
	private static void juegaQuince(int[][] quince) {
		int[][] buena = new int[6][6];
		Scanner leer = new Scanner(System.in);
		int r;
		int movimiento = 1;
		
		arreglaQuince(buena);
		
		muestraQuince(quince);
		
		do{
			System.out.printf("MOVIMIENTO %d \n",movimiento);
			do {
				System.out.println("Elige la casilla para mover:");
				r = leerMovimiento(leer);
				
				if(puedoMover(quince,r))
					mueveQuince(quince,r);
				else
					System.out.printf("\nNo puedes mover a la casilla %d\n",r);
				
			}while(!puedoMover(quince,r));
			System.out.println("\n-------------------------------");
			muestraQuince(quince);
			movimiento++;
		}while(sonIguales(quince,buena)==false);
			
	}	
}
