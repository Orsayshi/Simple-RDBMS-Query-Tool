Simple RDBMS Query Tool
Author: Or Khiyaev 

About: 
The Simple RDBMS Query Tool reads csv files and converts them into a relational schema. 



How to Run in Terminal: 
$ javac ssql.java <\b>
$ java ssql 

Then you should see: 

***** A simple RDBMS query tool*****
type quit at prompt to exit the program

>>

To Open file 
>> ssql init filename.csv 

Example: 
>> ssql init table1.csv

To use SELECTION:
>> ssql SELECTION relationName, attributeName, Operator, constant. 

Example:
>> ssql SELECTION table1,A,<,2

To use PROJECTION:
>> ssql PROJECTION relationName, [attr1, attr2, ….. attr(i)]
Example:
>> ssql PROJECTION table1,A 

To use JOIN:
>> ssql JOIN relationName1, relationName2, ON relation1.attr, relation2.attr
Example: 
>> ssql JOIN table1,table2 ON table1.A,table2.B. 
