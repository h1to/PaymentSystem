package kz.iitu.itse1908.murzaliev;

import kz.iitu.itse1908.murzaliev.database.Payment;
import kz.iitu.itse1908.murzaliev.database.Provider;
import kz.iitu.itse1908.murzaliev.database.ServiceToPay;
import kz.iitu.itse1908.murzaliev.repository.PaymentRepository;
import kz.iitu.itse1908.murzaliev.repository.ProviderRepository;
import kz.iitu.itse1908.murzaliev.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class SpringLab6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringLab6Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (ServiceRepository serviceRepository,
                                         ProviderRepository providerRepository, PaymentRepository paymentRepository) {
        return args -> {
            Provider provider = new Provider("Kassa24");
            providerRepository.save(provider);
            Provider providerWithId = providerRepository.findByProviderName(provider.getProviderName());

            ServiceToPay service = new ServiceToPay("Altel 4G", 2.0, providerWithId);
            serviceRepository.save(service);
            ServiceToPay serviceWithId = serviceRepository.findByServiceName(service.getServiceName());

            Payment payment = new Payment(serviceWithId,
                    new Date(), 1000.0, 20.0,
                    false, 0L, "77082553797");
            paymentRepository.save(payment);

            List<Payment> payments = paymentRepository.findAll();
            payments.forEach(p -> {
                System.out.println(p);
            });
        };
    }
}