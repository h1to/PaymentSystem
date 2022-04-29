package kz.iitu.itse1908.murzaliev.repository;

import kz.iitu.itse1908.murzaliev.database.Payment;
import kz.iitu.itse1908.murzaliev.database.ServiceToPay;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED)
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Cacheable("pays")
    @CacheEvict("pays")
    List<Payment> findAll ();

    @Transactional(rollbackFor = Exception.class)
    @Query(value = "SELECT * FROM Payment WHERE account = ?0", nativeQuery = true)
    List<Payment> findByAccount (String account);

    @Transactional(rollbackFor = Exception.class)
    @Query("select p from #{#entityName} p where p.serviceToPayId = ?1")
    List<Payment> findByServiceToPayId (ServiceToPay service);

    @Transactional(rollbackFor = Exception.class)
    Payment findByPaymentId (Long id);

    List<Payment> findByStatus (Boolean status);
}
