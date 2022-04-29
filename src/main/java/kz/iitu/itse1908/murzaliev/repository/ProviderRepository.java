package kz.iitu.itse1908.murzaliev.repository;

import kz.iitu.itse1908.murzaliev.database.Provider;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED)
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Cacheable("providers")
    @CacheEvict("providers")
    List<Provider> findAll ();

    @Transactional(rollbackFor = Exception.class)
    Provider findByProviderName (String name);

    @Transactional(rollbackFor = Exception.class)
    Provider findByProviderId (Long id);
}
