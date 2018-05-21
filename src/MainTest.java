

import data.Data;
import data.OutOfRangeSampleSize;
import mining.KMeansMiner;
import keyboardinput.Keyboard;
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Data data = new Data("playtennis");
		System.out.println(data);
		int k;
		int numIter=0;
		char carattere = 0;
		do {
			try {
				System.out.println("Inserisci k:");
				k=Keyboard.readInt();
				KMeansMiner kmeans=new KMeansMiner(k);
				numIter = kmeans.kMeans(data);
				System.out.println("Numero di Iterazione:"+numIter);
				System.out.println(kmeans.getC().toString(data));
			} catch (OutOfRangeSampleSize e) {
				e.notValidNumber();
			}
			System.out.println("Vuoi ripetere l'esecuzione?(y/n)");
			carattere=Keyboard.readChar();
		}while(carattere!=('n'));
		

	}

}
