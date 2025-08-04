package com.javier.productsapi.product.application.scheduling;

import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j //logs
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 6000) // we can use cronjobs  "0 0 0 * * *" for daily at midnight
    public void fixProductsPriceSchedule() {
        log.info("Fixing products price schedule");

        productRepository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * 1.1);
            productRepository.upsert(product);
        });

        log.info("Finished products price schedule");

    }


}
