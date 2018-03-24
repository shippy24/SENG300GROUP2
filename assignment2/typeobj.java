package assignment2;

public class typeobj {
	public int declarationCount = 0;
	public int referenceCount = 0;
	public String name;
	
	public typeobj(String startName) {
		name = startName;
	}
	
	public void updateDeclarationCount() {
		declarationCount++;
	}
	
	public void updateReferenceCount() {
		referenceCount++;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void print() {
		System.out.println(name + ": Declarations = " + declarationCount + " / ReferenceCount = " + referenceCount);
	}
}
