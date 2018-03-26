package assignment2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.junit.Test;


public class TestIteration2 {

	private static String BASEDIR = "C:\\Users\\amyyu\\eclipse-workspace\\seng3\\src\\seng3\\testfiles";
	
	@Test
    public void testCreateParserForJLS8() {
    assertNotNull(ASTParser.newParser(AST.JLS8));
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testCreateParserFor0() {
    assertNotNull(ASTParser.newParser(0));
    }
    
    @Test
    public void testIteration2Constructor() {
    	Iteration2 test = new Iteration2(BASEDIR);
    }
    
    @Test(expected = NullPointerException.class)
    public void testNullArguments() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	Iteration2 temp = new Iteration2(null);
    }
    
    @Test
    public void testNotEnoughArguments() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	try {
    	Iteration2.main(new String[] {});
    	}
    	catch (Exception e){
    	}
    	assertEquals("Error: Not enough arguments passed.\nExpected: <directory-pathname> <qualified-name-java-type>\r"+
    			"\n", outContent.toString());
    }
   
    @Test
    public void testTooManyArguments() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	try {
    		Iteration2.main(new String[]{BASEDIR, "String","Bleh"});
    	}
    	catch(Exception e) {
    	}
    	assertEquals("Error: Too many arguments passed.\nExpected: <directory-pathname> <qualified-name-java-type>\r"+
    			"\n", outContent.toString());
    }
   
    @Test
    public void testInvalidDirectory(){
    	File notDir = new File("C:\\NotADirectory");
    	try {
    		Iteration2.parseFilesInDir(notDir);
    	}
    	catch(Exception e) {
    	}
    }
    
    @Test
    public void testDeclarationReferenceCount() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	
    	String code= "public class aTest{ \n" 
    			+ "public class bTest{} \n"
    			+ "String e;";
    	Iteration2 temp = new Iteration2(BASEDIR);
    	
    	temp.parse(code);
    	temp.print2();
    	assertEquals("aTest: Declarations = 1 / ReferenceCount = 0\r\n" + 
    			"bTest: Declarations = 1 / ReferenceCount = 0\r\n" + 
    			"String: Declarations = 0 / ReferenceCount = 1\r\n" + 
    			"3\r" + 
    			"\n", outContent.toString());

    }

    @Test
    public void testAnnotationDeclaration() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	
    	String code= "public class T3 {@interface T3{}}";
    	Iteration2 temp = new Iteration2(BASEDIR);
    	
    	temp.parse(code);
    	temp.print2();
    	assertEquals("T3: Declarations = 1 / ReferenceCount = 0\r\n" + 
    			"1\r\n", outContent.toString());

    }
    
    @Test
    public void testEnumDeclaration() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	
    	String code= "public class T3 {public enum T3{}}";
    	Iteration2 temp = new Iteration2(BASEDIR);
    	
    	temp.parse(code);
    	temp.print2();
    	assertEquals("T3: Declarations = 1 / ReferenceCount = 0\r\n" + 
    			"enum: Declarations = 0 / ReferenceCount = 1\r\n" +
    			"2\r\n", outContent.toString());

    }
    
    @Test
    public void test() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
		String[] args = new String[1];
		args[0] = BASEDIR;
		try {
			Iteration2.main(args);
		}
		catch (Exception e) {	
		}

    }
    
    @Test
    public void testUnzipJarFail() {
    	try {
    		Iteration2.unzipJar(BASEDIR, "");
    	}
    	catch (Exception e) {
    	}
    }
    
    
}
