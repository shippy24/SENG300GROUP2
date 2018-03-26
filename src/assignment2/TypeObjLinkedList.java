package assignment2;

import java.util.*;
import assignment2.Typeobj;

public class TypeObjLinkedList {
	
	public LinkedList<Typeobj> types = new LinkedList<Typeobj>();
	
	public void upDateListDec (String qns) {
		boolean flag = true;				//flag set
		for(Typeobj X: types) {				//iterate through linkedlist
			if(X.name.equals(qns)) {		//check if any element's name equals qualifiedName
				X.updateDeclarationCount();	//if yes up date that element's referenceCount
				flag = false;				//set flag to false
				break;
			}
		}
		if(flag) {							//if element not present
			Typeobj Y = new Typeobj(qns);	//create element
			Y.updateDeclarationCount();		//increment referenceCount
			types.add(Y);					//add element to linkedlist
		}
	}
	
	public void upDateListRef (String qns) {
		boolean flag = true;				//flag set
		for(Typeobj X: types) {				//iterate through linkedlist
			if(X.name.equals(qns)) {		//check if any element's name equals qualifiedName
				X.updateReferenceCount();	//if yes up date that element's referenceCount
				flag = false;				//set flag to false
				break;
			}
		}
		if(flag) {							//if element not present
			Typeobj Y = new Typeobj(qns);	//create element
			Y.updateReferenceCount();		//increment referenceCount
			types.add(Y);					//add element to linkedlist
		}
	}
}