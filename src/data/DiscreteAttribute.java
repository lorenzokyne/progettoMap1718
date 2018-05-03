package data;
import utility.ArraySet;

public class DiscreteAttribute extends Attribute{
	private String values[];
	public DiscreteAttribute(String name,int index, String values[]){
		super(name,index);
		this.values=values;
	}
	public int GetNumberOfDistinctValues() {
		return values.length;
	}
	public String getValue(int i) {
		return values[i];
	}
	
	//verifica se la stringa appartiene al dominio di valori possibili
	public boolean appartiene(String v) { 
		for(int i=0;i<GetNumberOfDistinctValues();i++) {
			if(getValue(i).equals(v)) {
				return true;
			}
		}
		return false;
	}
	
	//restituisce il numero di volte che la stringa v si presenta in data nelle righe stabilite da idList e nella colonna corrente
	public int frequency(Data data,ArraySet idList,String v) {
		int cont=0;
		int temp[]=idList.toArray();
		if(appartiene(v)) {
			for(int i=0; i<temp.length;i++) {
				if(data.getAttributeValue(temp[i], getIndex()).equals(v)) {
					cont++;
				}
			}
		}
		return cont;
	}
}
