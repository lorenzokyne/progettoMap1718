package data;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class DiscreteAttribute<T> extends Attribute implements Iterable<String>{
	private TreeSet<String> values;
	
	public DiscreteAttribute(String name,int index, TreeSet<String> values){
		super(name,index);
		this.values=values;
	}
	public int GetNumberOfDistinctValues() {
		return values.size();
	}
	public Iterator<String> iterator(){
		Iterator<String> a = values.iterator();
		return a;
	}
	//verifica se la stringa appartiene al dominio di valori possibili
	public boolean appartiene(String v) { 
		if(values.contains(v))
			return true;
		return false;
	}
	
	//restituisce il numero di volte che la stringa v si presenta in data nelle righe stabilite da idList e nella colonna corrente
	public int frequency(Data data,Set<Integer> idList,String v) {
		int cont=0;
		Object temp[]=idList.toArray();
		if(appartiene(v)) {
			for(int i=0; i<temp.length;i++) {
				if(data.getAttributeValue((int)temp[i], getIndex()).equals(v)) {
					cont++;
				}
			}
		}
		return cont;
	}
	public static void main(String args[]) {
		TreeSet<String> values=new TreeSet<String>();
		for(int i=0;i<10;i++) {
			values.add("Ciao!!");
		}
		DiscreteAttribute<String> a=new DiscreteAttribute<String>("Lorenzo", 1, values);
		Iterator<String> z = a.iterator();
		while(z.hasNext()) {
			System.out.println(z.next());
		}
	}
}
