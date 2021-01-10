package de.hsrm.mi.swtpro.pflamoehus.exceptions.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusServiceException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StatusServiceException(String msg){
        super(msg);
    }
    
}
