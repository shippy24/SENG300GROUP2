/**
 * Recursively seaches a directory and calls parse on files in it
 * Modified code from https://stackoverflow.com/questions/10780747/recursively-search-for-a-directory-in-java
 * @author Shayne Mujuru
 */ 
 
 public static final void parseFilesInDir(File rootDir) {
	//make array of files in the directory specified 
    File[] files = rootDir.listFiles();
	
	//make a list of the files which are folders
    List<File> directories = new ArrayList<File>(files.length);
	
    for (File file : files) {
        if ((f.isFile() && ((f.getName().endsWith(".java")) || (f.getName().endsWith(".jar"))))) {
            //THEN PARSE FILE
        } else if (file.isDirectory()) {
            directories.add(file);
        }
    }
	
	//for each folder in directories parse files in each directory
    for (File directory : directories) {
        parseFilesInDir(directory);
    }
}

