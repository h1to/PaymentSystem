package kz.iitu.itse1908.murzaliev.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@NamedQuery(name = "findByStatus", query = "select p from Payment p where p.status = ?1")
public class Payment {
    @Id
    @Column(name = "payment_id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @NonNull
    @JoinColumn(name = "service_id")
    @ManyToOne
    private ServiceToPay serviceToPayId;

    @NonNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NonNull
    @Column(name = "sum_in", nullable = false)
    private Double sum_in;

    @Column(name = "sum", nullable = false)
    private Double sum;

    @NonNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @NonNull
    @Column(name = "sub_status", nullable = false)
    private Long subStatus;

    @NonNull
    @Column(name = "account", nullable = false)
    private String account;

    public Payment(@NonNull ServiceToPay serviceToPayId, @NonNull Date date, @NonNull Double sum_in, Double sum, @NonNull Boolean status, @NonNull Long subStatus, @NonNull String account) {
        this.serviceToPayId = serviceToPayId;
        this.date = date;
        this.sum_in = sum_in;
        this.sum = sum;
        this.status = status;
        this.subStatus = subStatus;
        this.account = account;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getSum() {
        return sum;
    }

    public void calculateSumToSend () {
        Double sumSent = sum_in*(100 - serviceToPayId.getCommission())/100;
        setSum(sumSent);
    }

    @Autowired
    public void setServiceToPayId(ServiceToPay serviceToPayId) {
        this.serviceToPayId = serviceToPayId;
    }
}
