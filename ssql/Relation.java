import java.util.ArrayList;
import java.util.List;

public class Relation {
	//name of the relation schema
	private String tableName;

	// list of column names
	private List<String> attributes;

	//instance data as a list of Tuple objects
	private List<Tuple> instance;

	public Relation(String name, List<String> attributes){
		tableName = name;
		this.attributes = attributes;
		instance = new ArrayList<Tuple>();
	}

	public String getTableName() {
		return tableName;
	}

	public String toString(){
		String s = "Schema-Name: " + tableName + "\n";

		for(String colName : attributes){
			//System.out.println("colName: " + colName);
			s += String.format("%-15s", colName);
			//System.out.println("s: " + s);
		}
		s += "\n";

		for(int i = 0; i < getSize(); i++){
			Tuple t = getTuple(i);
			int numCols = attributes.size();
			for(int j = 0; j < numCols; j++){
				int data = (Integer)t.getData(j);
				s += String.format("%-15d", data);
			}
			s += "\n";
		}

		return s;
	}

	public int getIndexOfAttribute(String attributeName){
		for(int i = 0; i < attributes.size(); i++){
			if(attributes.get(i).equals(attributeName)){
				return i;
			}
		}
		//System.out.println();
		return 0;
	}

	public void addTuple(Tuple t){
		instance.add(t);
	}

	public List<String> getAttributes(){
		return attributes;
	}

	public int getSize(){
		return instance.size();
	}

	public Tuple getTuple(int index){
		return instance.get(index);
	}


}
