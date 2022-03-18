package com.tanunwai.springboot.exam.demo.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.github.javafaker.Faker;
import com.tanunwai.springboot.exam.demo.entity.User;
import com.tanunwai.springboot.exam.demo.repository.UserRepository;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(path= {"/user"})
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping(path={"/querybetweenbirth"})
	public String queryBetweenBirth(@Nullable @RequestParam(value = "startBirth") String startBirth,
								  @Nullable @RequestParam(value = "endBirth") String endBirth,
								  Model model) throws ParseException
	{		
		if(startBirth == "" || endBirth == "") {
			model.addAttribute("_method","POST");
			model.addAttribute("users", userRepository.findAll());
			return "redirect:./";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sBirth=sdf.parse(startBirth);
		Date eBirth=sdf.parse(endBirth);
		model.addAttribute("_method","POST");
		model.addAttribute("users",userRepository.getByBirthBetween(sBirth, eBirth));
		return "userlist";
	}
	
	@GetMapping(path= {"/choosebirth2"})
	public String chooseBirth2(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "choosebirth2";
	}
	
	@GetMapping(path={"/querystartandendbirth"})
	public String queryStartAndEndBirth(@Nullable @RequestParam(value = "startBirth") String startBirth,
								  @Nullable @RequestParam(value = "endBirth") String endBirth,
								  Model model) throws ParseException
	{		
		if(startBirth == "" || endBirth == "") {
			model.addAttribute("_method","POST");
			model.addAttribute("users", userRepository.findAll());
			return "redirect:./";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sBirth=sdf.parse(startBirth);
		Date eBirth=sdf.parse(endBirth);
		model.addAttribute("_method","POST");
		model.addAttribute("users",userRepository.getByBirthGreaterThanEqualAndBirthLessThanEqual(sBirth, eBirth));
		return "userlist";
	}
	
	@GetMapping(path= {"/choosebirth"})
	public String chooseBirth(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "choosebirth";
	}
	
	@GetMapping(path={"/querymultipleids"})
	public String queryMultipleId(@Nullable @RequestParam(value = "id") List<Long> id,
								  @Nullable @RequestParam(value = "birth") String birthStr,
								  Model model) throws ParseException
	{		
		if(id == null || id.isEmpty() || id.size()==0 || birthStr == "") {
			model.addAttribute("_method","POST");
			model.addAttribute("users", userRepository.findAll());
			return "redirect:./";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date birth=sdf.parse(birthStr);		
		model.addAttribute("_method","POST");
		model.addAttribute("users",userRepository.getByIdInOrBirthLessThan(id, birth));
		return "userlist";
	}
	
	@GetMapping(path= {"/checkboxid"})
	public String chooseMultipleId(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "checkboxid";
	}
	
	@GetMapping(path={"/querylikeless"})
	public String queryLike(@RequestParam(value = "likename") String likename, @RequestParam(value = "id") Long id, Model model){
		model.addAttribute("_method","POST");
		model.addAttribute("users",userRepository.getByNameStartingWithAndIdLessThan(likename, id));
		return "userlist";
	}

	@GetMapping(path={"/querylikethan"})
	public String queryLikeThan(@RequestParam(value = "likename") String likename, @RequestParam(value = "id") Long id, Model model){
		model.addAttribute("_method","POST");
		model.addAttribute("users",userRepository.getByNameStartingWithAndIdGreaterThanEqual(likename, id));
		return "userlist";
	}
	
	@GetMapping(path={"/querybyage/{age}"})
	public String queryByAge(@PathVariable(value = "age") String age, @ModelAttribute User user, Model model){
		model.addAttribute("_method","POST");
		model.addAttribute("users", userRepository.getUserByAge(Integer.valueOf(age)));		
		return "userlist";
	}
	
	@GetMapping(path={"/querybymaxid"})
	public String queryByMaxId(@ModelAttribute User user, Model model){
		model.addAttribute("_method","POST");
		model.addAttribute("users", userRepository.getMaxIdUser());		
		return "userlist";
	}

	@GetMapping(path={"/querybyname/{username}"})
	public String queryByName(@PathVariable(value = "username") String username, @ModelAttribute User user, Model model){
		model.addAttribute("_method","POST");
		model.addAttribute("users", userRepository.getByName(username));		
		return "userlist";
	}
	
	@GetMapping(path= {"/countid"})
	public String countId(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("countId", userRepository.getTotalCount());
		return "userlist";
	}

	@GetMapping(path= {"/list"})
	public String index(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "userlist";
	}
	
	@GetMapping(path= {"/"})
	public String toForm(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "userform";
	}

	@GetMapping(path={"/{id}"})
	public String get(@PathVariable(value = "id") Long id, Model model){
		model.addAttribute("_method", "PUT");
		model.addAttribute("user", userRepository.getById(id));
		model.addAttribute("users", userRepository.findAll());
		return "userform";
	}

	//刪除
	@GetMapping(path={"/delete/{id}"})
	public String delete(@PathVariable(value = "id")Long id){
		userRepository.deleteById(id);
		return "redirect:../";
	}


	//加入
	@PostMapping(path={"/"})
	public String add(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("_method", "POST");
			model.addAttribute("users", userRepository.findAll());
			return "userform";
		}
		userRepository.save(user);
		return "redirect:./";
	}

	//修改
	@PutMapping(path={"/"})
	public String update(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("_method", "PUT");
			model.addAttribute("users", userRepository.findAll());
			return "userform";
		}
		// 根據 JPA 修改/新增原則，若 user 的 id 存在於資料庫中則進行修改
		userRepository.save(user);
		return "redirect:./";
	}

	//加入Simple Data
	@GetMapping(path= {"/add/simple/data"})
	@ResponseBody
	public String addSimpleDate() {
		Faker faker=new Faker();
		for(int i=0;i<100;i++) {
			User user=new User();
			user.setName(faker.name().lastName());
			user.setPassword(String.format("%04d",new Random().nextInt(1000)));
			user.setBirth(faker.date().birthday());
			user.setHeight(new Random().nextInt(50)+151);
			user.setWeight(new Random().nextInt(20)+(int)((user.getHeight()-80)*0.7));
			userRepository.save(user);
		}
		return "Add Simple Data OK!";
	}

	//列印Simple Data
	@GetMapping(path = {"/list/simple/data"})
	@ResponseBody
	public List<User> listSimpleData(){
		return userRepository.findAll();
	}
}