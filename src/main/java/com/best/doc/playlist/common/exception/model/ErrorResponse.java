/**
 * 
 */
package com.best.doc.playlist.common.exception.model;

import lombok.Data;

/**
 * @author mchellapandian
 *
 */
@Data
public class ErrorResponse {

    private String errorType;
    private String errorMessage;
    private long errorCode;
    private Throwable errorCause;
}
