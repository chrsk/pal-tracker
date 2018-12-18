package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class WelcomeController {

    private String message;


    public WelcomeController(@Value("${WELCOME_MESSAGE}") String message)
    {
        this.message = message;
    }

    @GetMapping("/")
    public String sayHello() {
        BigInteger factValue = BigInteger.ONE;
        long t1=System.nanoTime();
        for ( int i = 2; i <= 1000000/8; i++){
            factValue = factValue.multiply(BigInteger.valueOf(i));
        }
        return message;
    }
}