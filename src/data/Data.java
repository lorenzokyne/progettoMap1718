package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.TableData;

public class Data {
	/*
	 * public class Example implements Comparable<Example> { private List<Object>
	 * example = new ArrayList<Object>();
	 * 
	 * public void add(Object o) { example.add(o); }
	 * 
	 * public Object get(int i) { return example.get(i); }
	 * 
	 * @Override public int compareTo(Example ex) { for (int i = 0; i <
	 * example.size(); i++) { if (!get(i).equals(ex.get(i))) { return ((Comparable)
	 * example.get(i)).compareTo(ex.get(i)); } } return 0; }
	 * 
	 * public String toString() { String temp = new String(); for (Object s :
	 * example) temp += s.toString(); return temp; }
	 * 
	 * }
	 */

	// Le visibilità di classi , attributi e metodi devono essere decise dagli
	// studenti
	private List<database.Example> data;
	private int numberOfExamples;
	private List<Attribute> explanatorySet;

	public Data(String table) {
		/*// data
		TreeSet<Example> tempData = new TreeSet<Example>();
		explanatorySet = new LinkedList<Attribute>();
		// TO DO : memorizzare le transazioni secondo lo schema della tabella nelle
		// specifiche
		// numberOfExamples

		// explanatory Set
		TreeSet<String> outLookValues = new TreeSet<String>();
		TreeSet<String> temperatureValues = new TreeSet<String>();
		TreeSet<String> humidityValues = new TreeSet<String>();
		TreeSet<String> windValues = new TreeSet<String>();
		TreeSet<String> playTennisValues = new TreeSet<String>();
		windValues.add("Weak");
		windValues.add("Strong");
		playTennisValues.add("No");
		playTennisValues.add("Yes");
		humidityValues.add("Normal");
		humidityValues.add("High");
		temperatureValues.add("Cool");
		temperatureValues.add("Mild");
		temperatureValues.add("Hot");
		outLookValues.add("Overcast");
		outLookValues.add("Rain");
		outLookValues.add("Sunny");

		// TO DO : avvalorare ciascune elemento di explanatorySet con un oggetto della
		// classe DiscreteAttribute che modella il corrispondente attributo (e.g.
		// outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook

		explanatorySet.add(new DiscreteAttribute<String>("Outlook", 0, outLookValues));
		explanatorySet.add(new ContinuousAttribute("Temperature", 1, 3.2, 38.7));
		explanatorySet.add(new DiscreteAttribute<String>("Humidity", 2, humidityValues));
		explanatorySet.add(new DiscreteAttribute<String>("Wind", 3, windValues));
		explanatorySet.add(new DiscreteAttribute<String>("PlayTennis", 4, playTennisValues));

		// similmente per gli altri attributi

		Example ex0 = new Example();
		Example ex1 = new Example();
		Example ex2 = new Example();
		Example ex3 = new Example();
		Example ex4 = new Example();
		Example ex5 = new Example();
		Example ex6 = new Example();
		Example ex7 = new Example();
		Example ex8 = new Example();
		Example ex9 = new Example();
		Example ex10 = new Example();
		Example ex11 = new Example();
		Example ex12 = new Example();
		Example ex13 = new Example();

		ex0.add(new String("Sunny"));
		ex0.add(new Double(37.5));
		ex0.add(new String("High"));
		ex0.add(new String("Weak"));
		ex0.add(new String("No"));

		ex1.add(new String("Sunny"));
		ex1.add(new Double(38.7));
		ex1.add(new String("High"));
		ex1.add(new String("Strong"));
		ex1.add(new String("No"));

		ex2.add(new String("Overcast"));
		ex2.add(new Double(37.5));
		ex2.add(new String("High"));
		ex2.add(new String("Weak"));
		ex2.add(new String("Yes"));

		ex3.add(new String("Rain"));
		ex3.add(new Double(20.5));
		ex3.add(new String("High"));
		ex3.add(new String("Weak"));
		ex3.add(new String("Yes"));

		ex4.add(new String("Rain"));
		ex4.add(new Double(20.7));
		ex4.add(new String("Normal"));
		ex4.add(new String("Weak"));
		ex4.add(new String("Yes"));

		ex5.add(new String("Rain"));
		ex5.add(new Double(21.2));
		ex5.add(new String("Normal"));
		ex5.add(new String("Strong"));
		ex5.add(new String("No"));

		ex6.add(new String("Overcast"));
		ex6.add(new Double(20.5));
		ex6.add(new String("Normal"));
		ex6.add(new String("Strong"));
		ex6.add(new String("Yes"));

		ex7.add(new String("Sunny"));
		ex7.add(new Double(21.2));
		ex7.add(new String("High"));
		ex7.add(new String("Weak"));
		ex7.add(new String("No"));

		ex8.add(new String("Sunny"));
		ex8.add(new Double(21.2));
		ex8.add(new String("Normal"));
		ex8.add(new String("Weak"));
		ex8.add(new String("Yes"));

		ex9.add(new String("Rain"));
		ex9.add(new Double(19.8));
		ex9.add(new String("Normal"));
		ex9.add(new String("Weak"));
		ex9.add(new String("Yes"));

		ex10.add(new String("Sunny"));
		ex10.add(new Double(3.5));
		ex10.add(new String("Normal"));
		ex10.add(new String("Strong"));
		ex10.add(new String("Yes"));

		ex11.add(new String("Overcast"));
		ex11.add(new Double(3.6));
		ex11.add(new String("High"));
		ex11.add(new String("Strong"));
		ex11.add(new String("Yes"));

		ex12.add(new String("Overcast"));
		ex12.add(new Double(3.5));
		ex12.add(new String("Normal"));
		ex12.add(new String("Weak"));
		ex12.add(new String("Yes"));

		ex13.add(new String("Rain"));
		ex13.add(new Double(3.2));
		ex13.add(new String("High"));
		ex13.add(new String("Strong"));
		ex13.add(new String("No"));

		tempData.add(ex0);
		tempData.add(ex1);
		tempData.add(ex2);
		tempData.add(ex3);
		tempData.add(ex4);
		tempData.add(ex5);
		tempData.add(ex6);
		tempData.add(ex7);
		tempData.add(ex8);
		tempData.add(ex9);
		tempData.add(ex10);
		tempData.add(ex11);
		tempData.add(ex12);
		tempData.add(ex13);
		data = new ArrayList<Example>(tempData);
		numberOfExamples = data.size();*/
		DbAccess db=new DbAccess();
		TableData t= new TableData(db);
		try {
			data=t.getDistinctTransazioni(table);
		} catch (SQLException | EmptySetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return explanatorySet.size();
	}

	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return data.get(exampleIndex).get(attributeIndex);
	}

	public Attribute getAttribute(int index) {
		return explanatorySet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return explanatorySet;
	}

	public int[] sampling(int k) throws OutOfRangeSampleSize {
		if (k <= 0 || k > getNumberOfExamples()) {
			throw new OutOfRangeSampleSize();
		} else {
			int centroidIndexes[] = new int[k];
			// choose k random different centroids in data.
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			for (int i = 0; i < k; i++) {
				boolean found = false;
				int c;
				do {
					found = false;
					c = rand.nextInt(getNumberOfExamples());
					// verify that centroid[c] is not equal to a centroide
					// already stored in CentroidIndexes
					for (int j = 0; j < i; j++)
						if (compare(centroidIndexes[j], c)) {
							found = true;
							break;
						}
				} while (found);
				centroidIndexes[i] = c;
			}
			return centroidIndexes;
		}
	}

	private boolean compare(int i, int j) {
		for (int k = 0; k < getNumberOfAttributes(); k++) {
			if (!getAttributeValue(i, k).equals(getAttributeValue(j, k))) {
				return false;
			}
		}
		return true;
	}

	public Tuple getItemSet(int index) {
		Tuple temp = new Tuple(getNumberOfAttributes());
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			Object item = explanatorySet.get(i);
			if (item.getClass().getName().equals("data.ContinuousAttribute")) {
				temp.add(new ContinuousItem((ContinuousAttribute) explanatorySet.get(i),
						(Double) data.get(index).get(i)), i);
			} else {
				temp.add(new DiscreteItem((DiscreteAttribute) explanatorySet.get(i), (String) data.get(index).get(i)),
						i);
			}
		}
		return temp;
	}

	public Object computePrototype(Set<Integer> idList, Attribute attribute) {
		if (attribute.getClass().getName().equals("data.ContinuousAttribute")) {
			return computePrototype(idList, (ContinuousAttribute) attribute);
		} else if (attribute.getClass().getName().equals("data.DiscreteAttribute")) {
			return computePrototype(idList, (DiscreteAttribute<String>) attribute);
		}
		return null;
	}

	public String computePrototype(Set<Integer> idList, DiscreteAttribute<String> attribute) {
		String temp = "";
		double max = 0;
		double app = 0;
		for (Object s : attribute) {
			app = attribute.frequency(this, idList, (String) s);
			if (app > max) {
				max = app;
				temp = (String) s;
			}
		}
		System.out.println(temp);
		return temp;
	}

	public Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		double somma = 0.0;
		int nValues = 0;
		Iterator<Integer> i = idList.iterator();
		while (i.hasNext()) {
			somma = somma + (double) data.get(i.next()).get(attribute.getIndex());
			nValues++;
		}
		return somma / nValues;
	}

	public String toString() {
		String appoggio = getAttribute(0) + "," + getAttribute(1) + "," + getAttribute(2) + "," + getAttribute(3) + ","
				+ getAttribute(4) + "\n";
		for (int i = 0; i < getNumberOfExamples(); i++) {
			appoggio = appoggio + (i + 1) + ":";
			for (int j = 0; j < getNumberOfAttributes(); j++) {
				appoggio = appoggio + getAttributeValue(i, j) + ",";
			}
			appoggio = appoggio + "\n";
		}
		return appoggio;
	}

	public static void main(String args[]) {
		Data trainingSet = new Data("playtennis");
		System.out.println(trainingSet);
	}
}