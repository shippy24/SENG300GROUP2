package assignment2;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import assignment2.typeObjQueue;

import org.eclipse.jdt.core.dom.*;

public class Iteration1 {
	public static File rootDir;
	public typeObjQueue types = new typeObjQueue();

	public static void main(String[] args) {
		int argsLength = args.length;
		
		// Throw error if invalid amount of arguments and terminate
		if (argsLength < 1) {
			System.out.println("Error: Not enough arguments passed.\nExpected: <directory-pathname> <qualified-name-java-type>");
			return;
		} else if (argsLength > 1) {
			System.out.println("Error: Too many arguments passed.\nExpected: <directory-pathname> <qualified-name-java-type>");
			return;
		}
		
		// if correct amount of arguments then create new instance and start work
		Iteration1 it1 = new Iteration1(args[0]);
		
		List<File> javaFiles = parseFilesInDir(rootDir);
		
		for(File file: javaFiles) {
			String sourceCode;
			// Try to read the contents of file, if error occurs, skip and go to next file.
			try {
				sourceCode = new String(Files.readAllBytes(Paths.get(file.toURI())));
			} catch (IOException e) {
				continue;
			}
				
			// if contents successfully read then parse the contents
			it1.parse(sourceCode);
		}
		
		// print result
		it1.print2();
		
		return;
	}	
	
	/**
	 * Iteration1 constructor
	 * Takes given pathName and typeName and initializes global variables
	 */
	public Iteration1(String pathName) {
		rootDir = new File(pathName);
	}
	
	/**
	 * Recursively searches a directory and builds an array of .java and .jar files
	 * returns array
	 * Modified code from https://stackoverflow.com/questions/10780747/recursively-search-for-a-directory-in-java
	 * @author Shayne Mujuru
	 */ 
	
	public static List<File> parseFilesInDir(File rootDir) {
		//make array of files in the directory specified 
	    File[] files = rootDir.listFiles();
		
		//make a list of the files which are folders
	    List<File> directories = new ArrayList<File>(files.length);
	    List<File> filesList = new ArrayList<File>();
		
	    for (File file : files) {
	        if ((file.isFile() && ((file.getName().endsWith(".java")) || (file.getName().endsWith(".jar"))))) {
	            filesList.add(file);
	        } else if (file.isDirectory()) {
	            directories.add(file);
	        }
	    }
		
		//for each folder in directories parse files in each directory
	    for (File directory : directories) {
	        parseFilesInDir(directory);
	    }
	    
	    return filesList;
	}
	
	/**
	 * Parses given source code and increments count_dec and count_ref if necessary.
	 */
	@SuppressWarnings("deprecation")
	public void parse(String sourceCode) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);

		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(sourceCode.toCharArray());
		parser.setBindingsRecovery(true);

		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {
			/**
			public boolean visit(SimpleName node) {
				String qualifiedName = node.getFullyQualifiedName();
				if(node.isDeclaration()){
					String ty = node.getNodeType();
					types.upDateListDec(qualifiedName);
				}
				
				return true;
			}
			**/
			// Count Declarations
			public boolean visit(TypeDeclaration node) {
				String qualifiedName = node.getName().getFullyQualifiedName();
				//System.out.println("dec: " + qualifiedName);
				//if (typeSimple.equals(qualifiedName)) {
				types.upDateListDec(qualifiedName);
				//}
				return true;
			}
			
			public boolean visit(AnnotationTypeDeclaration node) {
				String qualifiedName = node.getName().getFullyQualifiedName();
				types.upDateListDec(qualifiedName);
				return true;
			}
			
			public boolean visit(EnumDeclaration node) {
				String qualifiedName = node.getName().getFullyQualifiedName();
				types.upDateListDec(qualifiedName);
				return true; 
			}
			// Count References
			public boolean 	visit(FieldDeclaration node) {
				String qualifiedName = node.getType().toString();
				//System.out.println("ref: " + qualifiedName);
				//if (typeSimple.equals(qualifiedName)) {
				types.upDateListRef(qualifiedName);
				//}
				return true;
			}
		});
	}
	
	/**
	 * print:
	 * Prints the output string
	 */
	public void print() {
		System.out.println(type + "; Declarations found: " + count_dec + "; References found: " + count_ref + ".");
	}
	
	public void print2() {
		for(typeobj X: types.types) {
			X.print();
		}
		int siz = types.types.size();
		System.out.println(siz);
	}
	
	
}

