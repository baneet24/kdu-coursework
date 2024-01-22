package org.example.service;


import org.example.entities.Tyre;
import org.example.utility.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    @Bean("mrf")
    public Tyre tyre1(){
        return new Tyre("MRF", Constants.MRF_TYRE_PRICE);
    }
    @Bean("bridestone")
    public Tyre tyre2(){
        return new Tyre("Bridestone",Constants.BRIDESTONE_TYRE_PRICE);
    }
}


