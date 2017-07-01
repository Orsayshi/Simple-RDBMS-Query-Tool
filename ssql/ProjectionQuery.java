import java.util.ArrayList;
import java.util.List;

public class ProjectionQuery implements Query{

	// relation table to perform the SELECTION
	private Relation relation;

	//list of projected attributes
	private List<String> attributes;

	public ProjectionQuery(Relation relation, List<String> attributes) {
		this.relation = relation;
		this.attributes = attributes;
	}

	@Override
	public Relation execute() {
		// the output relation
		Relation output = null;

		// make a temporary name of the output relation
		String schema = "PROJECTION-" + relation.getTableName() + "-TMP";

		output = new Relation(schema, attributes);

		for(int i = 0; i < relation.getSize(); i++){
			Tuple t = relation.getTuple(i);
			List<Object> values = new ArrayList<Object>();
			for(String colName : attributes){
				Object data = t.getData(colName);
				values.add(data);
			}
			Tuple tnew = new Tuple(values, output);
			output.addTuple(tnew);
		}

		return output;
	}

}
