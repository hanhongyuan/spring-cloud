package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by João on 24/06/2016.
 */

@RestController
@RequestMapping("/user")
public class PrincipalRestController {

    @RequestMapping
    Principal principal(Principal principal) {
        return principal;
    }

}
