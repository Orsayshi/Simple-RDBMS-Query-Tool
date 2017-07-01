import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandParser {

	public static Query parseCommand(String cmd, Map<String, Relation> rdms) throws IllegalCommandException{
		if(!cmd.startsWith("ssql"))
			throw new IllegalCommandException("command should begin with ssql");

		cmd = cmd.substring(4).trim();
		String[] cmdArgs;

		if(cmd.startsWith("init")){
			// parse "ssql init" command
			cmdArgs = cmd.split(" ");
			if(cmdArgs.length != 2)
				throw new IllegalCommandException("Invalid init command! Must be like 'ssql init FILE'");
			String csvFilename = cmdArgs[1];
			return new InitRelationQuery(csvFilename);
		}
		else if(cmd.startsWith("SELECTION")){
			// parse "ssql SELECTION" command
			cmd = cmd.substring("SELECTION".length()).trim();
			cmdArgs = cmd.split(",");
			if(cmdArgs.length != 4)
				throw new IllegalCommandException("Invalid SELECTION command! Must be like 'ssql SELECTION relation, attribute-name, operator, constant'");
			String schema = cmdArgs[0].trim();
			String attributeName = cmdArgs[1].trim();
			String operator = cmdArgs[2].trim();
			Object constant = Integer.parseInt(cmdArgs[3].trim());
			Relation relation = rdms.get(schema);
			return new SelectionQuery(relation, attributeName, operator, constant);
		}
		else if(cmd.startsWith("PROJECTION")){
			// parse "ssql PROJECTION" command
			cmd = cmd.substring("PROJECTION".length()).trim();
			cmdArgs = cmd.split(",");
			if(cmdArgs.length < 2)
				throw new IllegalCommandException("Invalid PROJECTION command! Must be like 'ssql PROJECTION relation, [attr1,attr2,...attrn]'");
			String schema = cmdArgs[0].trim();
			List<String> attributes = new ArrayList<String>();
			for(int i = 1; i < cmdArgs.length; i++){
				attributes.add(cmdArgs[i].trim());
			}
			Relation relation = rdms.get(schema);
			return new ProjectionQuery(relation, attributes);
		}
		else if(cmd.startsWith("JOIN")){
			// parse "ssql JOIN" command
			cmd = cmd.substring("JOIN".length()).trim();
			cmdArgs = cmd.split("ON");
			String[] relations = cmdArgs[0].trim().split(",");
			String[] attrs = cmdArgs[1].trim().split(",");

			String schema1 = relations[0].trim();
			String schema2 = relations[1].trim();
			List<String> attributes = new ArrayList<String>();
			for(int i = 1; i < cmdArgs.length; i++){
				attributes.add(cmdArgs[i]);
			}
			Relation relation1 = rdms.get(schema1);
			Relation relation2 = rdms.get(schema2);
			String attr1table = attrs[0].substring(0, attrs[0].indexOf('.'));
			String attr1attr = attrs[0].substring(attrs[0].indexOf('.')+1);
			String attr2table = attrs[1].substring(0, attrs[1].indexOf('.'));
			String attr2attr = attrs[1].substring(attrs[1].indexOf('.')+1);
			String attr1=null,attr2=null;
			if(relation1.getTableName().equals(attr1table)){
				attr1 = attr1attr;
				attr2 = attr2attr;
			}else{
				attr1 = attr2attr;
				attr2 = attr1attr;
			}
			return new JoinQuery(relation1, relation2, attr1, attr2);
		}
		return null;
	}
}
