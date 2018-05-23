package data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class DiscreteAttribute<T> extends Attribute implements Iterable<String> {
	private TreeSet<String> values;

	public DiscreteAttribute(String name, int index, TreeSet<String> values) {
		super(name, index);
		this.values = values;
	}

	public int GetNumberOfDistinctValues() {
		return values.size();
	}

	public Iterator<String> iterator() {
		Iterator<String> a = values.iterator();
		return a;
	}

	// verifica se la stringa appartiene al dominio di valori possibili
	public boolean appartiene(String v) {
		if (values.contains(v))
			return true;
		return false;
	}

	// restituisce il numero di volte che la stringa v si presenta in data nelle
	// righe stabilite da idList e nella colonna corrente
	public int frequency(Data data, Set<Integer> idList, String v) {
		int cont = 0;
		Object temp[] = idList.toArray();
		if (appartiene(v)) {
			for (int i = 0; i < temp.length; i++) {
				if (((String) (data.getAttributeValue((int) temp[i], getIndex()))).equals(v)) {
					cont++;
				}
			}
		}
		return cont;
	}
}
