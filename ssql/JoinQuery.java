import java.util.ArrayList;
import java.util.List;

public class JoinQuery implements Query{

	private Relation relation1;
	private Relation relation2;
	private String attr1;
	private String attr2;

	public JoinQuery(Relation relation1, Relation relation2, String attr1, String attr2) {
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.attr1 = attr1;
		this.attr2 = attr2;
	}

	@Override
	public Relation execute() {
		// the output relation
		Relation output = null;

		// make a temporary name of the output relation
		String schema = "JOIN-" + relation1.getTableName() + "," + relation2.getTableName() + "-ON-" + attr1+","+attr2;

		// create the list of attributes for the JOIN result schema
		List<String> attributes = new ArrayList<String>();
		for(String colName : relation1.getAttributes()){
			attributes.add(relation1.getTableName()+"."+colName);
		}
		for(String colName : relation2.getAttributes()){
			attributes.add(relation2.getTableName()+"."+colName);
		}

		output = new Relation(schema, attributes);

		int relation1NumAttr = relation1.getAttributes().size();
		int relation2NumAttr = relation2.getAttributes().size();

		for (int i = 0; i < relation1.getSize(); i++) {
			Tuple t1 = relation1.getTuple(i);
			Object data1 = t1.getData(attr1);
			int i1 = (Integer)data1;
			for(int j = 0; j < relation2.getSize(); j++){
				Tuple t2 = relation2.getTuple(j);
				Object data2 = t2.getData(attr2);
				int i2 = (Integer)data2;
				if(i1 == i2){
					List<Object> values = new ArrayList<Object>();
					for(int k = 0; k < relation1NumAttr; k++){
						values.add(t1.getData(k));
					}
					for(int k = 0; k < relation2NumAttr; k++){
						values.add(t2.getData(k));
					}
					Tuple t = new Tuple(values, output);
					output.addTuple(t);
				}
			}
		}

		return output;
	}

}
