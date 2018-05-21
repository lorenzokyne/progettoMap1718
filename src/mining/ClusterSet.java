package mining;

import java.io.Serializable;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

public class ClusterSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cluster C[];
	private int i = 0;

	public ClusterSet(int k) {
		C = new Cluster[k];
	}

	public void add(Cluster c) {
		C[i] = c;
		i++;
	}

	public Cluster get(int i) {
		return C[i];
	}

	public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
		int centroidIndexes[] = data.sampling(C.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroidI = data.getItemSet(centroidIndexes[i]);
			add(new Cluster(centroidI));
		}
	}

	public Cluster nearestCluster(Tuple tuple) {
		double min = get(0).getCentroid().getDistance(tuple);
		Cluster app = get(0);
		for (int j = 1; j < i; j++) {
			double temp = get(j).getCentroid().getDistance(tuple);
			if (temp <= min) {
				min = temp;
				app = get(j);
			}
		}
		return app;
	}

	public Cluster currentCluster(int id) {
		for (int i = 0; i < C.length; i++) {
			if (C[i].contain(id)) {
				return C[i];
			}
		}
		return null;
	}

	public void updateCentroids(Data data) {
		for (int i = 0; i < C.length; i++) {
			C[i].computeCentroid(data);
		}
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null)
				temp += C[i].getCentroid() + "\n";
		}
		return temp;
	}

	public String toString(Data data) {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].toString(data) + "\n";
			}
		}
		return str;
	}
}
