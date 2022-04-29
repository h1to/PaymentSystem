package kz.iitu.itse1908.murzaliev;

import kz.iitu.itse1908.murzaliev.database.Payment;
import kz.iitu.itse1908.murzaliev.database.Provider;
import kz.iitu.itse1908.murzaliev.database.ServiceToPay;
import kz.iitu.itse1908.murzaliev.repository.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void testFindByAccount () {
        entityManager.persist(new Payment(new ServiceToPay("Tele 2", 1.0, new Provider("RPS")), new Date(), 1500.0, 15.0, false, 2L, "77473245647"));

        List<Payment> payments = paymentRepository.findByAccount("77473245647");
        assertEquals(1, payments.size());

        assertThat(payments).extracting(Payment::getAccount).containsOnly("77473245647");
    }
}
