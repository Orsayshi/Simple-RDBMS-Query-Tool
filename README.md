Simple RDBMS Query Tool<br />
Author: Or Khiyaev <br />

About: <br />
The Simple RDBMS Query Tool reads csv files and converts them into a relational schema. <br />



How to Run in Terminal: 
$ javac ssql.java <br />
$ java ssql <br />

Then you should see: 

***** A simple RDBMS query tool***** <br />
type quit at prompt to exit the program<br />

>><br />

To Open file <br />
>> ssql init filename.csv <br />

Example: <br />
>> ssql init table1.csv <br />

To use SELECTION:<br />
>> ssql SELECTION relationName, attributeName, Operator, constant. <br />

Example:<br />
>> ssql SELECTION table1,A,<,2<br />

To use PROJECTION:<br />
>> ssql PROJECTION relationName, [attr1, attr2, ….. attr(i)]<br />

Example:<br />
>> ssql PROJECTION table1,A <br />

To use JOIN:<br />
>> ssql JOIN relationName1, relationName2, ON relation1.attr, relation2.attr<br />

Example: <br />
>> ssql JOIN table1,table2 ON table1.A,table2.B <br />
