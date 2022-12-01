package com.DATN.Error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Errror404 implements ErrorController {

	@RequestMapping("/error")
	  public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    if (status != null) {
	      Integer statusCode = Integer.valueOf(status.toString());
	      if (statusCode == HttpStatus.NOT_FOUND.value()) {
	        return "Error404/index";
	      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        return "Error404/index";
	      }
	    }
	    return "Error404/index";
	  }
	  public String getErrorPath() {
	    return "Error404/index";
	  }
}
