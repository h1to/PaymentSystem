package kz.iitu.itse1908.murzaliev.controllers;

import kz.iitu.itse1908.murzaliev.repository.ProviderRepository;
import kz.iitu.itse1908.murzaliev.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private ProviderRepository providerRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceController(ProviderRepository providerRepository, ServiceRepository serviceRepository) {
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
    }
}
