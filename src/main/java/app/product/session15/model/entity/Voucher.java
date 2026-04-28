package app.product.session15.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	private Double discountValue;

	private Integer quantity;
	private Integer remaining;

	@Enumerated(EnumType.STRING)
	private String status;

	private LocalDateTime expiryDate;

	@Version
	private Long version;
}
