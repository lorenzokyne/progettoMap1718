
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Data data =new Data();
		System.out.println(data);
		int k=3;
		KMeansMiner kmeans=new KMeansMiner(k);
		int numIter=kmeans.kMeans(data);
		System.out.println("Numero di Iterazione:"+numIter);
		System.out.println(kmeans.getC().toString(data));

	}

}
