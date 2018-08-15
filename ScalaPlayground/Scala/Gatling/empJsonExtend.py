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


def clean(file):
    tempString = file.read()
    tempString = tempString[:-4]
    file.write(tempString)

def extedEmploy(file,range_):
    clean(file)
    addString = ""
    for i in range(range_[0],range_[1]):
        addString = addString + ToAppend.replace("employee{x}".format(x=range_[0]), "employee" + str(i))

    addString = addString + " ]"
    file.write(addString)



#file = open("C:/PLIKI/Load_tests/users_with_traits2.txt",'a')
file = open(getRootDirectory()+"\\users_with_traits2.txt",'w+')

extedEmploy(file,(2499,3000))

#wymagane są dodatkowe zmiany ręczne (już raczje nie )
#- dodać }, po ostatnim employee
#- dodać } ] na końcu (usunać z miejsca gdzie wczesniej był koniec)
#- usunąć ostanie ),
