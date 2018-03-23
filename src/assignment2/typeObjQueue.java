package assignment2;

import java.util.*;
import assignment2.typeobj;

public class typeObjQueue {
	
	public LinkedList<typeobj> types = new LinkedList<typeobj>();
	
	public void upDateListDec (String qns) {
		boolean flag = true;				//flag set
		for(typeobj X: types) {				//iterate through linkedlist
			if(X.name.equals(qns)) {		//check if any element's name equals qualifiedName
				X.updateDeclarationCount();	//if yes up date that element's referenceCount
				flag = false;				//set flag to false
				break;
			}
		}
		if(flag) {							//if element not present
			typeobj Y = new typeobj(qns);	//create element
			Y.updateDeclarationCount();		//increment referenceCount
			types.add(Y);					//add element to linkedlist
		}
	}
	
	public void upDateListRef (String qns) {
		boolean flag = true;				//flag set
		for(typeobj X: types) {				//iterate through linkedlist
			if(X.name.equals(qns)) {		//check if any element's name equals qualifiedName
				X.updateReferenceCount();	//if yes up date that element's referenceCount
				flag = false;				//set flag to false
				break;
			}
		}
		if(flag) {							//if element not present
			typeobj Y = new typeobj(qns);	//create element
			Y.updateReferenceCount();		//increment referenceCount
			types.add(Y);					//add element to linkedlist
		}
	}
}