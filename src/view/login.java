package view;

import java.util.Scanner;

import dto.User_dto;
import service.LoginService;
import util.annotion.DAO;

@DAO("dao.userDAO")
public class login {

	public static void main(String[] args) throws Exception{
		LoginService loginService = new LoginService();
		Scanner sc = new Scanner(System.in);
		System.out.println("«Î ‰»Î’ ∫≈");
		String name = sc.nextLine();
		System.out.println("«Î ‰»Î√‹¬Î");
		String pass = sc.nextLine();
		User_dto user_dto = new User_dto(name, pass);
		if (loginService.login(user_dto)) {
			System.out.println(user_dto.getMsg());
		}else {
			System.out.println(user_dto.getMsg());
		}
		

	}

}
