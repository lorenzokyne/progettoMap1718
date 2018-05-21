package data;

public class ContinuousItem extends Item {

	public ContinuousItem(Attribute attribute, Double value) {
		super(attribute, value);
	}

	@Override // Item
	double distance(Object a) {
		ContinuousAttribute app = ((ContinuousAttribute) getAttribute());
		double temp = Math.abs(
				app.getScaledValue((double) getValue()) - app.getScaledValue((double) ((ContinuousItem) a).getValue()));
		return temp;
	}

	public static void main(String[] args) {
		Attribute att = new ContinuousAttribute("Ciao", 0, 0, 10);
		ContinuousItem temp = new ContinuousItem(att, 1.32);
		System.out.println(temp.distance(0.4));
	}
}
