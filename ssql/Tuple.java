import java.util.List;

public class Tuple {

	// row values as a list of object's
	private List<Object> value;

	// relation to which this tuple belongs
	private Relation relation;

	public Tuple(List<Object> value, Relation relation){
		this.value = value;
		this.relation = relation;
	}

	public Object getData(int columnIndex){
		return value.get(columnIndex);
	}


	public Relation getRelation(){
		return relation;
	}


	public Object getData(String attributeName){
		int columnIndex = relation.getIndexOfAttribute(attributeName);
		return getData(columnIndex);
	}


}
