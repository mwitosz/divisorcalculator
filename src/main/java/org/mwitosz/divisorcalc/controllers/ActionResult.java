/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.controllers;

/**
 *
 * @author mwito
 */
public class ActionResult<T> {
    
    public enum Status {
        SUCCESS("SUCCESS"),
        FAILURE("FAILURE");

        private String resultString;
        
        Status(String resultString) {
            this.resultString = resultString;
        }        
    }
    
    private T object = null;
    
    private Status status = null;
    
    private String failureInfo = null;
    
    public Status getStatus() {
        return status;
    }
    
    public T getObject() {
        return object;
    }
    
    public String getFailureInfo() {
        return failureInfo;
    }
    
    private ActionResult(Status status, String failureInfo, T object) {
        this.status = status;
        this.object = object;
        this.failureInfo = failureInfo;
    }
    
    public static<T> ActionResult<T> Failure(String failureInfo) {
        return new ActionResult<>(Status.FAILURE, failureInfo, null);
    }
    
    public static<T> ActionResult<T> Success(T object) {
        return new ActionResult<>(Status.SUCCESS, null, object);
    }
}
