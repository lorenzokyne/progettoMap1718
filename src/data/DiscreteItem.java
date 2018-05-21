package data;

public class DiscreteItem extends Item {
	
	public DiscreteItem(DiscreteAttribute<String> attribute,String value) {
		super(attribute,value);
	}
	
	public double distance(Object a) {
		if(getValue().equals( ((DiscreteItem)a).getValue())) {
			return 0;
		}else {
			return 1;
		}
	}
}
