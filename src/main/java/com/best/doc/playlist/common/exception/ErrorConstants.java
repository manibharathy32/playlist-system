/**
 * 
 */
package com.best.doc.playlist.common.exception;

/**
 * @author mchellapandian
 *
 */
public interface ErrorConstants {

	String NOT_FOUND = "Request not found";
	String RESOURCE_EXITS = "Conflict";
	String INTERNAL_ERROR = "Internal Error";
	
	String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong Internal Error";
	String RESOURCE_NOT_FOUND_ERROR_MESSAGE = "Requested resource by reference is not found in the datasource";
	String RESOURCE_EXITS_ERROR_MESSAGE = "Provided data is being already stored or updated in the datasource";
}
