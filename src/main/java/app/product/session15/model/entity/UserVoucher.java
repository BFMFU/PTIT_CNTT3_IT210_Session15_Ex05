package app.product.session15.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "voucher_id"})
)
@Getter
@Setter
public class UserVoucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long voucherId;

	private LocalDateTime usedAt;
}
