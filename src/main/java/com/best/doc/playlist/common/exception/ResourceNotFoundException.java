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
public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4098148535450021416L;
	private List<ErrorCode> errorCode = new ArrayList<ErrorCode>();

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(ErrorCode error) {
        super();
        this.errorCode.add(error);
    }
}