package de.tr.dao;

import de.tr.model.Customer;




/**
 * {@link DuplicateEntryException} class which is thrown, when the specified user name duplicates
 * in {@link Customer} 
 * 
 * 
 * @author 
 * 
 */
public class DuplicateEntryException extends Exception {
	private static final long serialVersionUID = 6090554192367955585L;

	public DuplicateEntryException(String message) {
		super(message);
	}

}
