package org.example.service;

import org.example.entities.Speaker;
import org.example.utility.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    @Bean("sony")
    public Speaker speaker1() {
        return new Speaker("Sony", Constants.SONY_SPEAKER_PRICE);
    }

    @Bean("bose")
    public Speaker speaker2() {
        return new Speaker("Bose", Constants.BOSE_SPEAKER_PRICE);
    }
}

