package app.product.session15.controller;

import app.product.session15.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoucherController {

	@Autowired
	private VoucherService voucherService;

	@PostMapping("/apply-voucher")
	public String applyVoucher(
			@RequestParam String code,
			Model model
	) {

		Long userId = getCurrentUserId();

		try {
			voucherService.applyVoucher(userId, code);
			model.addAttribute("success", "Áp dụng voucher thành công!");

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "cart";
	}

	private Long getCurrentUserId() {
		return 1L;
	}
}