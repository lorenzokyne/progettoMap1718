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
import database.NoValueException;
import database.QUERY_TYPE;
import database.TableData;
import database.TableSchema;

public class Data {

	// Le visibilità di classi , attributi e metodi devono essere decise dagli
	// studenti
	private List<database.Example> data;
	private int numberOfExamples;
	private List<Attribute> explanatorySet;

	public Data(String table) {
		// data
		// TreeSet<Example> tempData = new TreeSet<Example>();
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
		windValues.add("weak");
		windValues.add("strong");
		playTennisValues.add("no");
		playTennisValues.add("yes");
		humidityValues.add("normal");
		humidityValues.add("high");
		temperatureValues.add("cool");
		temperatureValues.add("mild");
		temperatureValues.add("hot");
		outLookValues.add("overcast");
		outLookValues.add("rain");
		outLookValues.add("sunny");

		// TO DO : avvalorare ciascune elemento di explanatorySet con un oggetto della
		// classe DiscreteAttribute che modella il corrispondente attributo (e.g.
		// outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		/*
		 * explanatorySet.add(new DiscreteAttribute<String>("Outlook", 0,
		 * outLookValues)); explanatorySet.add(new ContinuousAttribute("Temperature", 1,
		 * 3.2, 38.7)); explanatorySet.add(new DiscreteAttribute<String>("Humidity", 2,
		 * humidityValues)); explanatorySet.add(new DiscreteAttribute<String>("Wind", 3,
		 * windValues)); explanatorySet.add(new DiscreteAttribute<String>("PlayTennis",
		 * 4, playTennisValues));
		 */
		// similmente per gli altri attributi
		DbAccess db = new DbAccess();
		try {
			TableSchema tb = new TableSchema(db, table);
			TableData t = new TableData(db);
			for (int i = 0; i < tb.getNumberOfAttributes(); i++) {
				String nomecol = tb.getColumn(i).getColumnName().toLowerCase();
				if (tb.getColumn(i).isNumber()) {
					QUERY_TYPE min, max;
					min = QUERY_TYPE.MIN;
					max = QUERY_TYPE.MAX;
					double valmin = (double) t.getAggregateColumnValue(table, tb.getColumn(i), min);
					double valmax = (double) t.getAggregateColumnValue(table, tb.getColumn(i), max);
					explanatorySet.add(new ContinuousAttribute(nomecol, i, valmin, valmax));
				} else {
					explanatorySet.add(new DiscreteAttribute<String>(nomecol, i,
							(TreeSet) t.getDistinctColumnValues(table, tb.getColumn(i))));
				}

				/*
				 * Attribute a = explanatorySet.get(i); if
				 * (a.getClass().getName().equals("data.DiscreteAttribute")) { Iterator iter =
				 * ((DiscreteAttribute) a).iterator(); while (iter.hasNext()) {
				 * System.out.println(iter.next()); } } else { ContinuousAttribute prova =
				 * (ContinuousAttribute) a; prova.getminmax(); }
				 */

			}
			data = t.getDistinctTransazioni(table);
			numberOfExamples = data.size();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptySetException e) {
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
		String appoggio = "";
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			appoggio += getAttribute(i) + ",";
		}
		appoggio = appoggio + "\n";
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
		Data trainingSet = new Data("prova");
		System.out.println(trainingSet);
	}
}