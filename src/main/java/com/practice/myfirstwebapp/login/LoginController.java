package com.practice.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String gotoLoginPage() {
        return "login";
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name,
                                  @RequestParam String password, ModelMap model) {
        if(authenticationService.authenticate(name, password)) {
            model.put("name", name);
            return "welcome";
        }
        model.put("errorMessage", "Invalid Credentials! Please try again.");
        return "login";
    }
}


/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    // for logging
    private Logger logger = LoggerFactory.getLogger(getClass());

    // /login?name=name
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        //System.out.println("Request param is " + name); // not a good practice
        logger.debug("Request param is {}", name);
        logger.info("I want this printed at info level");
        logger.warn("I want this printed at warn level");
        return "login";
    }
}*/
