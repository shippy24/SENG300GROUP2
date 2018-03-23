package assignment2;

import java.util.*;
import assignment2.typeobj;

public class typeObjQueue {
	
	public LinkedList<typeobj> types = new LinkedList<typeobj>();
	
	public void upDateListDec (String qns) {
		boolean flag = true;
		for(typeobj X: types) {
			if(X.name.contentEquals(qns)) {
				X.updateDeclarationCount();
				flag = false;
			}
			break;
		}
		if(flag) {
			typeobj Y = new typeobj(qns);
			Y.updateDeclarationCount();
			types.add(Y);
		}
	}
	
	public void upDateListRef (String qns) {
		boolean flag = true;
		for(typeobj X: types) {
			if(X.name.contentEquals(qns)) {
				X.updateReferenceCount();
				flag = false;
			}
			break;
		}
		if(flag) {
			typeobj Y = new typeobj(qns);
			Y.updateReferenceCount();
			types.add(Y);
		}
	}
}