import os
def getRootDirectory():
    return  os.path.dirname(os.path.abspath(__file__))


ToAppend = """[{
  "firstName" : "firstName",
  "lastName" : "lastName",
  "email" : "employee2499@cream.local",
  "login" : "employee2499",
  "password" : "test10",
  "auth" : "LOCAL",
  "roles" : [ "employee", "seller", "casemanager", "businessadmin", "appadmin", "activitymanager", "campadmin", "productmanager", "servicemanager", "campviewer", "salesmanager", "sysadmin", "productowner" ],
  "lang": "en",
		"dictionaryCodes": {
			"groups": [
				{
					"dictionaryCode": "FuncArea",
					"itemCodes": ["1st_Line", "2nd_Line", "IT", "Training","Planning_&_Management_Information","Processes_and_Information","Project_&_Portfolio_Management","Quality_Management","Risk_Management","External"]
				}, {
					"dictionaryCode": "Lang",
					"itemCodes": ["BG","CA","HR","CS","DA","NL","EN","ET", "FI", "FR", "DE","EL","HU","IT","JP","LV","LT","NO","PL","PT","RO","RU","SK","SL","ES","SV","TR"]
				}, {
					"dictionaryCode": "Service",
					"itemCodes": ["audit","complaints","content","conversion","support","verification","incident","campaigns","police", "testing","promotion","risk","vip"]
				},
				{
				  "dictionaryCode": "Shop",
				  "itemCodes": ["ac","bc","bj","bb","bz","be","bf","ds"	,"ep","gb","gm","gd","ii","pp","dk","pe","fr","pi","pf","pj","pm","pr","sh","wt","wf","zy"]
				}
				]
		},},
"""



def extedEmploy(file,range_):
    file.write("[ ")
    addString = ""
    for i in range(range_[0],range_[1]):
        addString = addString + ToAppend.replace("employee{x}".format(x=range_[0]), "employee" + str(i))

    addString = addString + " ]"
    file.write(addString)



file = open(getRootDirectory()+"\\users_with_traits2.json",'w+')
extedEmploy(file,(2499,3000))
