package kz.iitu.itse1908.murzaliev.repository;

import kz.iitu.itse1908.murzaliev.database.ServiceToPay;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED)
public interface ServiceRepository extends JpaRepository<ServiceToPay, Long> {

    @Cacheable("services")
    @CacheEvict("services")
    List<ServiceToPay> findAll ();

    @Transactional(rollbackFor = Exception.class)
    ServiceToPay findByServiceName (String name);

    @Transactional(rollbackFor = Exception.class)
    @Query("select s from ServiceToPay s where s.serviceId = ?1")
    ServiceToPay findByServiceId (Long id);
}
