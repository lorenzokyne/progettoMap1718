package mining;
import data.Data;
import data.Tuple;

public class ClusterSet {
	private Cluster C[];
	private int i=0;
	
	public ClusterSet(int k) {
		C = new Cluster[k];
	}
	
	public void add(Cluster c) {
		C[i]=c;
		i++;
	}
	
	public Cluster get(int i) {
		return C[i];
	}
	
	public void initializeCentroids(Data data){
		int centroidIndexes[]=data.sampling(C.length);
		for(int i=0;i<centroidIndexes.length;i++)
			{
			Tuple centroidI=data.getItemSet(centroidIndexes[i]);
			add(new Cluster(centroidI));
		}
	}
	
	public Cluster nearestCluster(Tuple tuple) {
		double min=tuple.getDistance(get(0).getCentroid());
		Cluster app=get(0);
		for(int i=1;i<C.length;i++) {
			double temp= tuple.getDistance(get(i).getCentroid());
			if(temp<min) {
				min=temp;
				app=get(i);
			}
		}
		return app;
	}
	
	public Cluster currentCluster(int id) {
		for(int i=0;i<C.length;i++) {
			if(C[i].contain(id)) {
				return C[i];
			}
		}
		return null;
	}
	
	public void updateCentroids(Data data) {
		for(int i=0; i<C.length;i++) {
			C[i].computeCentroid(data);
		}
	}
	
	public String toString() {
		String temp="";
		for(int i=0;i<C.length;i++) {
			if(C[i]!=null)
				temp+=C[i].getCentroid()+"\n";
		}
		return temp;
	}
	
	public String toString(Data data ){
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
			str+=i+":"+C[i].toString(data)+"\n";
			}
		}
		return str;
	}
}
