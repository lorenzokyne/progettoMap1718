import java.util.Arrays;

public class ArraySet {
	private boolean set[];
	private int size=0;
	public ArraySet (){
		set=new boolean[50];
		for(int i=0;i<set.length;i++)
			set[i]=false;
	}
	
	//return true if add is changing the arraySet
	public boolean add(int i){
		if(i>=set.length)
		{
			//enlarge the set
			boolean temp[]=new boolean[set.length*2];
			Arrays.fill(temp,false);
			System.arraycopy(set, 0, temp, 0, set.length);
			set=temp;
		}	
	boolean added=set[i];
		set[i]=true;
		if(i>=size)
			size=i+1;
		return !added;
	}
	
	public boolean delete(int i){
		if(i<size){
			boolean deleted=set[i];
			set[i]=false;
			if(i==size-1){
				//update size
				int j;
				for(j=size-1;j>=0 && !set[j];j--);
				size=j+1;
			}
			return deleted;
		}
		return false;
	}
	
	public boolean get(int i){
		return set[i];
	}
	
	
	public int[] toArray(){
		int a[]=new int[0];
		for(int i=0;i<size;i++){
			if(get(i)){
				int temp[]=new int[a.length+1];
				System.arraycopy(a, 0, temp, 0, a.length);
				a=temp;
				a[a.length-1]=i;
			}
		}
		return a;
	}
}
