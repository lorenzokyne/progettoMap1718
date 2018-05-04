package data;

public class Tuple {
	
	private Item [] tuple;
	
	public Tuple(int size) {
		tuple=new Item[size];
	}
	
	public int getLength() {
		return tuple.length;
	}
	public Item get(int i) {
		return tuple[i];
	}
	
	public void add(Item c,int i) {
		tuple[i]=c;
	}
	
	public double getDistance(Tuple obj) {
		double temp=0;
		if(getLength()==obj.getLength()) {
			for(int i=0;i<getLength();i++) {
				temp=temp+get(i).distance(obj.get(i).getValue());
			}
		}
		//System.out.println("CIAO"+temp);
		return temp;
	}
	public double avgDistance(Data data,int clusteredData[]) {
		double p=0.0,sumD=0.0;
		for(int i=0;i<clusteredData.length;i++){
			double d= getDistance(data.getItemSet(clusteredData[i]));
			sumD+=d;
		}
		p=sumD/clusteredData.length;
		return p;
	}
}
