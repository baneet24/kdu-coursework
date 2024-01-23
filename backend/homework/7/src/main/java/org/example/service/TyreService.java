package org.example.service;

import org.example.entities.Tyre;
import org.example.utility.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TyreService {

    @Bean("mrf")
    public Tyre tyre1() {
        return new Tyre("MRF Tyre", Constants.MRF_TYRE_PRICE);
    }

    @Bean("bridgestone")
    public Tyre tyre2() {
        return new Tyre("Bridgestone Tyre", Constants.BRIDGESTONE_TYRE_PRICE);
    }
}
