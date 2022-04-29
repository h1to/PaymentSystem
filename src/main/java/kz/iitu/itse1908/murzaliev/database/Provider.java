package kz.iitu.itse1908.murzaliev.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@NamedQuery(name = "findById", query = "select p from Provider p where p.providerId = ?1")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "provider_id", nullable = false, insertable = false, updatable = false)
    private Long providerId;

    @NonNull
    @Column(name = "provider_name", nullable = false)
    private String providerName;

    public Provider(@NonNull String providerName) {
        this.providerName = providerName;
    }

    public Provider(Long providerId) {
        this.providerId = providerId;
    }
}
