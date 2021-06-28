/**
 * 
 */
package com.best.doc.playlist.common.exception;

import static com.best.doc.playlist.common.Constant.BASE_PACKAGE;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.best.doc.playlist.common.exception.model.ErrorCode;
import com.best.doc.playlist.common.exception.model.ErrorResponse;

/**
 * @author mchellapandian
 *
 */
@ControllerAdvice(basePackages = BASE_PACKAGE)
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/*
     * Whenever a "ResourseNotFound(404)" exception is thrown from service, the
     * error response is intercepted by the handler and a new ResponseEntity is
     * manipulated.
     */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<List<ErrorResponse>> handleRecordNotFoundException(ResourceNotFoundException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorType(ErrorConstants.NOT_FOUND);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.RESOURCE_NOT_FOUND.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorCode.RESOURCE_NOT_FOUND.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.NOT_FOUND);
	}

	/*
	 * Scenarios in which generic exceptions are thrown from Service layer are
	 * handled in this method. Corresponding ResponseEntity is created.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<List<ErrorResponse>> handleGenericException(Exception ex) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorType(ErrorConstants.INTERNAL_ERROR);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.INTERNAL_ERROR.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorCode.INTERNAL_ERROR.getCode());
		LOGGER.debug(ex.getMessage());
		return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * Scenarios in which resource exist exceptions are thrown from Service layer are
	 * handled in this method. Corresponding ResponseEntity is created.
	 */
	@ExceptionHandler(ResourceExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<List<ErrorResponse>> handleResourceExistException(Exception ex) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorType(ErrorConstants.RESOURCE_EXITS);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.RESOURCE_EXITS.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorCode.RESOURCE_EXITS.getCode());
		LOGGER.debug(ex.getMessage());
		return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.CONFLICT);
	}
}
