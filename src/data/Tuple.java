package data;

import java.io.Serializable;

public class Tuple implements Serializable {

	private Item[] tuple;

	public Tuple(int size) {
		tuple = new Item[size];
	}

	public int getLength() {
		return tuple.length;
	}

	public Item get(int i) {
		return tuple[i];
	}

	public void add(Item c, int i) {
		tuple[i] = c;
	}

	public double getDistance(Tuple obj) {
		double temp = 0;
		if (getLength() == obj.getLength()) {
			for (int i = 0; i < getLength(); i++) {
				temp = temp + (get(i).distance(obj.get(i)));
			}
		}
		return temp;
	}

	public double avgDistance(Data data, Object clusteredData[]) {
		double p = 0.0, sumD = 0.0;
		for (int i = 0; i < clusteredData.length; i++) {
			double d = getDistance(data.getItemSet((Integer)clusteredData[i]));
			sumD += d;
		}
		p = sumD / clusteredData.length;
		return p;
	}

	public String toString() {
		String temp = new String();
		for (int i = 0; i < tuple.length; i++) {
			temp += get(i).toString() + " ";
		}
		return temp;
	}
}
