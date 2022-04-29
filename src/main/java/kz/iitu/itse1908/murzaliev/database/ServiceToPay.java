package kz.iitu.itse1908.murzaliev.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ServiceToPay {
    @Id
    @Column(name = "service_id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;

    @Column(name = "service_name")
    @NonNull
    private String serviceName;

    @NonNull
    @Column(name = "commission", nullable = false)
    private Double commission;

    @NonNull
    @JoinColumn(name = "provider_id")
    @ManyToOne
    private Provider provider;

    public ServiceToPay( @NonNull String serviceName, @NonNull Double commission, @NonNull Provider provider) {
        this.serviceName = serviceName;
        this.commission = commission;
        this.provider = provider;
    }

    public ServiceToPay(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void init() {
        System.out.println("Initializing ServiceBean");
        if(serviceName.equals(null)) {
            System.err.println("Error initializing ServiceBean!!!");
        }
        System.out.println(serviceName + " ,commission = " + commission);
        System.out.println("Bean initialized successfully!");
    }

    public void destroy() {
        System.out.println("Destroying ServiceBean");
        if(!serviceName.equals("Destroy bean")) {
            System.err.println("This is not destroy bean!!!");
        }
        System.out.println("Bean destroyed!");
    }

    @Autowired
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
