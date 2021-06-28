/**
 * 
 */
package com.best.doc.playlist.common.exception.model;

import com.best.doc.playlist.common.exception.ErrorConstants;

/**
 * @author mchellapandian
 *
 */
public enum ErrorCode {

	INTERNAL_ERROR(500, ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE),
	RESOURCE_EXITS(409, ErrorConstants.RESOURCE_EXITS_ERROR_MESSAGE),
	RESOURCE_NOT_FOUND(404, ErrorConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE);
	
	private long code;
	private String message;

	ErrorCode(long errorCode, String errorMessage) {
		this.code = errorCode;
		this.message = errorMessage;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long errorCode) {
		this.code = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}

	@Override
	public String toString() {
		return String.format("%o : %s", this.code, this.message);
	}

}
