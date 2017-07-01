
public class Matcher {

	public static boolean matches(Tuple t, String attributeName, String operator, Object constant){
		//System.out.println(t.getRelation().toString());
		if(operator.equals("<") || operator.equals(">") || operator.equals("!=") || operator.equals("=")){

			Integer tupleData = (Integer)t.getData(attributeName);
			Integer iConst = (Integer)constant;

			switch(operator){
			case "<":
				return tupleData < iConst;
			case ">":
				return tupleData > iConst;
			case "=":
				return tupleData == iConst;
			case "!=":
				return tupleData != iConst;
			default:
				return false;
			}
		}
		else{
			return false;
		}
	}
}
