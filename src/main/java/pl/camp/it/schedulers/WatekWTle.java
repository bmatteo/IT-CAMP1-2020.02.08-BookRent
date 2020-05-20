package pl.camp.it.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WatekWTle {

    @Scheduled(fixedRate = 1000*60*5, initialDelay = 1000)
    public void wypisywacz() {
        System.out.println("Działam !!");
    }
}
