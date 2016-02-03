package de.tr.dao;

import de.tr.model.Customer;
import de.tr.model.Trailer;




/**
 * {@link DeleteException} class which is thrown, when the specified
 * {@link Trailer}, {@link Customer}  object is not allowed to be
 * deleted
 * 
 * @author 
 * 
 */
public class DeleteException extends Exception {
	private static final long serialVersionUID = 6090554192367955585L;

	public DeleteException(String message) {
		super(message);
	}

}
