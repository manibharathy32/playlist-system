/**
 * 
 */
package com.best.doc.playlist.common.exception;

import java.util.ArrayList;
import java.util.List;

import com.best.doc.playlist.common.exception.model.ErrorCode;

/**
 * @author mchellapandian
 *
 */
public class ResourceExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1917082001162185982L;
	private List<ErrorCode> errorCode = new ArrayList<ErrorCode>();

	public ResourceExistException() {
		super();
	}

	public ResourceExistException(ErrorCode error) {
		super();
		this.errorCode.add(error);
	}
}
