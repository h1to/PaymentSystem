package kz.iitu.itse1908.murzaliev.schedule;

import kz.iitu.itse1908.murzaliev.database.Payment;
import kz.iitu.itse1908.murzaliev.database.ServiceToPay;
import kz.iitu.itse1908.murzaliev.repository.PaymentRepository;
import kz.iitu.itse1908.murzaliev.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PayScheduler {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ServiceRepository serviceRepository;

//    @Async("threadPoolTaskScheduler")
//    @Scheduled(fixedDelayString = "${fixedDelay}", initialDelayString = "${initialDelay}")
//    public void scheduledPay () {
//        ServiceToPay service = serviceRepository.findByServiceId(2L);
//        Payment payment = new Payment(service,
//                new Date(), 1000.0, 20.0,
//                false, 0L, "77082553797");
//        paymentRepository.save(payment);
//        System.out.println("New payment" + payment);
//    }
//
//    @Async("threadPoolTaskScheduler")
//    @Scheduled(cron = "${cronExpression}")
//    public void scheduledStatusUpdate () {
//        List<Payment> payments = paymentRepository.findByStatus(false);
//        payments.forEach(p -> {
//            p.setStatus(true);
//            paymentRepository.save(p);
//        });
//    }
}