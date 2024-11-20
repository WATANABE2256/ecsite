package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.domain.MstGoods;
import com.example.demo.model.domain.MstUser;
import com.example.demo.model.dto.HistoryDto;
import com.example.demo.model.form.CartForm;
import com.example.demo.model.form.HistoryForm;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.mapper.MstGoodMapper;
import com.example.demo.model.mapper.MstUserMapper;
import com.example.demo.model.mapper.TblPurchaseMapper;
import com.google.gson.Gson;

@Controller
@RequestMapping("/ecsite")
public class IndexController {

	@Autowired
	private MstGoodMapper goodsMapper;
	@Autowired
	private MstUserMapper userMapper;
	@Autowired
	private TblPurchaseMapper purchaseMapper;

	private Gson gson = new Gson();

	@GetMapping("/")

	public String index(Model model) {
		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("goods", goods);

		return "index";
	}

	@ResponseBody
	@PostMapping("/api/login")
	public String loginApi(@RequestBody LoginForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f);

		if (user == null) {
			user = new MstUser();
			user.setFullName("ゲスト");
		}

		return gson.toJson(user);
	}

	@ResponseBody
	@PostMapping("/api/purchase")

	public int purchaseApi(@RequestBody CartForm f) {
		f.getCartList().forEach((c) -> {
			int total = c.getPrice() * c.getCount();
			purchaseMapper.insert(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total);

		});

		return f.getCartList().size();

	}

	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm f) {
		int userId = f.getUserId();
		List<HistoryDto> history = purchaseMapper.findHistory(userId);
		return gson.toJson(history);
	}

}
