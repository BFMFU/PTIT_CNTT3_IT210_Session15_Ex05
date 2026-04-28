package app.product.session15.service;

import app.product.session15.model.entity.UserVoucher;
import app.product.session15.model.entity.Voucher;
import app.product.session15.repository.UserVoucherRepository;
import app.product.session15.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VoucherService {

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private UserVoucherRepository userVoucherRepository;

	@Transactional
	public void applyVoucher(Long userId, String code) {

		Voucher voucher = voucherRepository.findByCodeForUpdate(code)
				                  .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));

		if (!voucher.getStatus().equals("ACTIVE")) {
			throw new RuntimeException("Voucher đã bị vô hiệu hóa");
		}

		if (voucher.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Voucher đã hết hạn");
		}

		if (userVoucherRepository.existsByUserIdAndVoucherId(userId, voucher.getId())) {
			throw new RuntimeException("Bạn đã sử dụng voucher này");
		}

		if (voucher.getRemaining() <= 0) {
			throw new RuntimeException("Voucher đã hết lượt sử dụng");
		}

		voucher.setRemaining(voucher.getRemaining() - 1);

		UserVoucher uv = new UserVoucher();
		uv.setUserId(userId);
		uv.setVoucherId(voucher.getId());
		uv.setUsedAt(LocalDateTime.now());

		userVoucherRepository.save(uv);
	}
}
