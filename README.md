# Java-utils
## &emsp;1. SearchFileUtils
&emsp;&nbsp;&nbsp;Given folder, search file by file name recursively(Depth-First Search).
## &emsp;2. AnnotationUtils
&emsp;&nbsp;&nbsp;Given class name and annotation name, return correspoding arguments in a map.  
&emsp;&nbsp;&nbsp;Throw exception if annotation not exist or multi annotations in this class.  
&emsp;&nbsp;&nbsp;ex: An annotation @Document(language = "java", collection = "ConnectionInf"),  
return {language=java, collection=DView8_Alarm} in a map
## &emsp;3. FieldNamesUtils
&emsp;&nbsp;&nbsp;Given class and annotation name, return all fields which contain this annotation.  
&emsp;&nbsp;&nbsp;ex: find which field contain annotation @Id
