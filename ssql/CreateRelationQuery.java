/**
 * This class implements Query to create a new relation schema 
 * based on a given CSV file.
 * 
 * @author
 *
 */
public class CreateRelationQuery implements Query{

	private String csvFilename;
	
	public CreateRelationQuery(String csvFilename) {
		this.csvFilename = csvFilename;
	}
	
	@Override
	public Relation execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
