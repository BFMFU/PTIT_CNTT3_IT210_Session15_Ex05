package app.product.session15.repository;

import app.product.session15.model.entity.UserVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoucherRepository extends JpaRepository<UserVoucher, Long> {

	boolean existsByUserIdAndVoucherId(Long userId, Long voucherId);
}