
public class SelectionQuery implements Query{

	// relation table to perform the SELECTION
	private Relation relation;

	// attribute name involved in the condition
	private String attribute;

	// operator involved in the condition
	private String op;

	// constant involved in the condition
	private Object constant;

	public SelectionQuery(Relation relation, String attribute, String op, Object constant) {
		this.relation = relation;
		this.attribute = attribute;
		this.op = op;
		this.constant = constant;

	}

	@Override
	public Relation execute() {
		// the output relation
		Relation output = null;

		// make a temporary name of the output relation
		String schema = "SELECTION-" + relation.getTableName() + "-TMP";

		output = new Relation(schema, relation.getAttributes());

		for(int i = 0; i < relation.getSize(); i++){
			Tuple t = relation.getTuple(i);
			if(Matcher.matches(t, attribute, op, constant)){
				output.addTuple(t);
			}
		}
		return output;
	}

}
