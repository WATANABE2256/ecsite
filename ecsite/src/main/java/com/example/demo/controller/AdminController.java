package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.domain.MstGoods;
import com.example.demo.model.domain.MstUser;
import com.example.demo.model.form.GoodsForm;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.mapper.MstGoodMapper;
import com.example.demo.model.mapper.MstUserMapper;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	@Autowired
	private MstUserMapper userMapper;

	@Autowired
	private MstGoodMapper goodsMapper;

	@RequestMapping("/")
	public String index() {
		return "admintop";
	}

	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model model) {
		MstUser user = userMapper.findByUserNameAndPassword(form);

		if (user == null) {
			model.addAttribute("errMessage", "ユーザー名またはパスワードが違います。");
			return "forward:/ecsite/admin/";
		}

		if (user.getlsAdmin() == 0) {
			model.addAttribute("errMessage","管理者ではありません。");
			return "forward:/ecsite/admin/";
		}

		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("goods", goods);

		return "welcome";
	}

	@PostMapping("/goodsMst")
	public String goodsMst(LoginForm f, Model m) {
		m.addAttribute("userName", f.getUserNane());
		m.addAttribute("password", f.getPassword());

		return "goodsmst";
	}

	@PostMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserNane());
		m.addAttribute("password", loginForm.getPassword());

		MstGoods goods = new MstGoods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());

		goodsMapper.insert(goods);

		return "forward:/ecsite/admin/welcome";
	}

	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsMapper.deleteByld(f.getid());
		} catch (IllegalArgumentException e) {
			return "-1";
		}

		return "1";
	}

}
