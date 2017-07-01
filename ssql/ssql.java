import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ssql {

	public static void main(String[] args) {

		// keep a map of all Relation objects in the RDBMS
		Map<String, Relation> rdbms = new HashMap<String, Relation>();

		// scanner to read commands from console
		Scanner input = new Scanner(System.in);

		// user ssql command
		String cmd;

		System.out.println("***** A simple RDBMS query tool***** ");
		System.out.println("type quit at prompt to exit the program\n");

		// loop to read and process user ssql commands
		while(true){
			// display query prompt
			System.out.print(">> ");

			// read the next command
			cmd = input.nextLine();

			// exit the program if user typed 'quit'
			if(cmd.equals("quit"))
				break;

			try{
				// parse the command to a Query instance
				Query query = CommandParser.parseCommand(cmd, rdbms);

				if(query != null){
					// execute the query and get the output Relation
					Relation output = query.execute();

					// print the output relation to console
					System.out.println(output.toString());

					// save the relation if its created from CSV
					if(query instanceof InitRelationQuery){
						rdbms.put(output.getTableName(), output);
					}
				}

			}catch(Exception ex){
				// handle exception and display error message to console
				System.out.println(ex.toString());
			}
		}

		// close the input scanner
		input.close();
	}

}
