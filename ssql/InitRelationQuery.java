import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements init Query to create a new relation schema 
 * based on a given CSV file.
 * 
 * @author
 *
 */
public class InitRelationQuery implements Query{

	private String csvFilename;
	
	public InitRelationQuery(String csvFilename) {
		this.csvFilename = csvFilename;
	}
	
	@Override
	public Relation execute() {
		// the relation to create and return the output relation
		Relation output = null;
		
		// get the name of the CSV file
		String schemaName = csvFilename.substring(csvFilename.lastIndexOf('/')+1);
		schemaName = schemaName.replace(".csv", "");
		schemaName = schemaName.replace(".CSV", "");
		
		// open CSV file for reading
		try {
			FileReader fin = new FileReader(new File(csvFilename));
			BufferedReader br = new BufferedReader(fin);
			// read header line for attributes
			String line = br.readLine();
			String[] colNames = line.split(",");
			List<String> attributes = new ArrayList<String>();
			for(String colName : colNames){
				attributes.add(colName.trim());
			}
			
			// create the output relation
			output = new Relation(schemaName, attributes);
			
			while((line = br.readLine()) != null){
				String[] strValues = line.split(",");
				List<Object> values = new ArrayList<Object>();
				for(String s : strValues){
					values.add(Integer.parseInt(s));
				}
				Tuple t = new Tuple(values, output);
				output.addTuple(t);
			}			
			fin.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: file '" + csvFilename + "' not found!");
			return null;
		} catch (IOException e) {
			System.out.println("error: file I/O while reading " + csvFilename + "!");
			return null;
		}
		
		
		return output;
	}

}
