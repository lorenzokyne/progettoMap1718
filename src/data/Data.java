package data;
import java.util.Random;

import utility.ArraySet;

public class Data {
// Le visibilità di classi , attributi e metodi devono essere decise dagli studenti	
	private Object data [][];
	private int numberOfExamples;
	private Attribute attributeSet[];
	private int distinctTuples;
	
	public Data(){
		
		//data
		data = new Object [14][5];
		// TO DO : memorizzare le transazioni secondo lo schema della tabella nelle specifiche
		
		// numberOfExamples
		
		 numberOfExamples=14;		 
		 
		
		//explanatory Set
		
		attributeSet = new Attribute[5];

		// TO DO : avvalorare ciascune elemento di attributeSet con un oggetto della classe DiscreteAttribute che modella il corrispondente attributo (e.g. outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		
		String outLookValues[]=new String[3];
		String temperatureValues[]=new String[3];
		String humidityValues[]=new String[2];
		String windValues[]=new String[2];
		String playTennisValues[]=new String[2];
		windValues[0]="Weak";
		windValues[1]="Strong";
		playTennisValues[0]="No";
		playTennisValues[1]="Yes";
		humidityValues[0]="Normal";
		humidityValues[1]="High";
		temperatureValues[0]="Cool";
		temperatureValues[1]="Mild";
		temperatureValues[2]="Hot";
		outLookValues[0]="Overcast";
		outLookValues[1]="Rain";
		outLookValues[2]="Sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook",0, outLookValues);
		attributeSet[1] = new DiscreteAttribute("Temperature",1, temperatureValues);
		attributeSet[2] = new DiscreteAttribute("Humidity",2, humidityValues);
		attributeSet[3] = new DiscreteAttribute("Wind",3, windValues);
		attributeSet[4] = new DiscreteAttribute("PlayTennis",4, playTennisValues);
		// similmente per gli altri attributi
		
		data[0][0]="Sunny";
		data[0][1]="Hot";
		data[0][2]="High";
		data[0][3]="Weak";
		data[0][4]="No";
		
		data[1][0]="Sunny";
		data[1][1]="Hot";
		data[1][2]="High";
		data[1][3]="Strong";
		data[1][4]="No";
		
		data[2][0]="Overcast";
		data[2][1]="Hot";
		data[2][2]="High";
		data[2][3]="Weak";
		data[2][4]="Yes";
		
		data[3][0]="Rain";
		data[3][1]="Mild";
		data[3][2]="High";
		data[3][3]="Weak";
		data[3][4]="Yes";
		
		data[4][0]="Rain";
		data[4][1]="Cool";
		data[4][2]="Normal";
		data[4][3]="Weak";
		data[4][4]="Yes";
		
		data[5][0]="Rain";
		data[5][1]="Cool";
		data[5][2]="Normal";
		data[5][3]="Strong";
		data[5][4]="No";
		
		data[6][0]="Overcast";
		data[6][1]="Cool";
		data[6][2]="Normal";
		data[6][3]="Strong";
		data[6][4]="Yes";
		
		data[7][0]="Sunny";
		data[7][1]="Mild";
		data[7][2]="High";
		data[7][3]="Weak";
		data[7][4]="No";
		
		data[8][0]="Sunny";
		data[8][1]="Cool";
		data[8][2]="Normal";
		data[8][3]="Weak";
		data[8][4]="Yes";
		
		data[9][0]="Rain";
		data[9][1]="Mild";
		data[9][2]="Normal";
		data[9][3]="Weak";
		data[9][4]="Yes";
		
		data[10][0]="Sunny";
		data[10][1]="Mild";
		data[10][2]="Normal";
		data[10][3]="Strong";
		data[10][4]="Yes";
		
		data[11][0]="Overcast";
		data[11][1]="Mild";
		data[11][2]="High";
		data[11][3]="Strong";
		data[11][4]="Yes";
		
		data[12][0]="Overcast";
		data[12][1]="Hot";
		data[12][2]="Normal";
		data[12][3]="Weak";
		data[12][4]="Yes";
		
		data[13][0]="Rain";
		data[13][1]="Mild";
		data[13][2]="High";
		data[13][3]="Strong";
		data[13][4]="No";
		
		distinctTuples=countDistinctTuples();
	}
	
	public int getNumberOfExamples(){
		return numberOfExamples;
	}
	
	public int getNumberOfAttributes(){
		return attributeSet.length;
	}	
	
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data[exampleIndex][attributeIndex];
	}
	
	public Attribute getAttribute(int index){
		return attributeSet[index];
	}
	
	public Attribute[] getAttributeSchema() {
		return attributeSet;
	}
	
	public Tuple getItemSet(int index) {
		Tuple tuple=new Tuple(attributeSet.length);
		for(int i=0;i<attributeSet.length;i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet[i],(String)data[index][i]),i);
		}
		return tuple;
	}
	
	public int[] sampling(int k){
		int centroidIndexes[]=new int[k];
		//choose k random different centroids in data.
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i=0;i<k;i++){
			boolean found=false;
			int c;
			do{
				found=false;
				c=rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroide
				//already stored in CentroidIndexes
				for(int j=0;j<i;j++)
					if(compare(centroidIndexes[j],c)){
						found=true;
						break;
					}
				}while(found);
			centroidIndexes[i]=c;
		}
		return centroidIndexes;
	}
	
	private boolean compare(int i,int j) {
		for(int k=0;k<getNumberOfAttributes();k++) {
			if(!getAttributeValue(i,k).equals(getAttributeValue(j,k))) {
				return false;
			}
		}
		return true;
	}
	
	public Object computePrototype(ArraySet idList,Attribute attribute) {
		return computePrototype(idList,(DiscreteAttribute)attribute);
	}
	
	public String computePrototype(ArraySet idList, DiscreteAttribute attribute) {
		String temp="";
		double max=0;
		double app=0;
			for(int i=0;i<attribute.GetNumberOfDistinctValues();i++) {
				app=attribute.frequency(this, idList, attribute.getValue(i));
				if(app>max) {
					max=app;
					temp= attribute.getValue(i);
				}
			}
		return temp;
	}
	
	public String toString(){		
		String appoggio=getAttribute(0)+","+getAttribute(1)+","+getAttribute(2)+","+getAttribute(3)+","+getAttribute(4)+"\n";
		for(int i=0;i<getNumberOfExamples();i++) {
			appoggio=appoggio+(i+1)+":";
			for(int j=0;j<getNumberOfAttributes();j++) {
				appoggio=appoggio+getAttributeValue(i,j)+",";
			}
			appoggio=appoggio+"\n";
		}
		return appoggio;
	}
	
	private int countDistinctTuples() {
		int cont=0;
		for(int i=0;i<getNumberOfExamples();i++) {
			if(!existTupla(i)) {
				cont++;
			}
		}
		return cont;
	}
	private boolean existTupla(int index) {
		for(int i=0;i<index;i++) {
			if(compare(i,index)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]){
		Data trainingSet=new Data();
		System.out.println(trainingSet);
	}
}