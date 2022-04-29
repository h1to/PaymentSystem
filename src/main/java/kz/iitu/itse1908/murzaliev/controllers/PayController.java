package kz.iitu.itse1908.murzaliev.controllers;

import kz.iitu.itse1908.murzaliev.repository.PaymentRepository;
import kz.iitu.itse1908.murzaliev.repository.ProviderRepository;
import kz.iitu.itse1908.murzaliev.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PayController {

    private PaymentRepository paymentRepository;
    private ProviderRepository providerRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public PayController(PaymentRepository paymentRepository, ProviderRepository providerRepository, ServiceRepository serviceRepository) {
        this.paymentRepository = paymentRepository;
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
    }
}
