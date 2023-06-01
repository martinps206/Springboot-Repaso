package com.martinps;

import com.martinps.bean.MyBean;
import com.martinps.bean.MyBeanWhithDependencySum;
import com.martinps.bean.MyBeanWithDependency;
import com.martinps.bean.MyBeanWithProperties;
import com.martinps.component.ComponentDependency;
import com.martinps.entity.User;
import com.martinps.pojo.UserPojo;
import com.martinps.repository.UserRepository;
import com.martinps.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication{
//public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWhithDependencySum myBeanWhithDependencySum;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;



	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWhithDependencySum myBeanWhithDependencySum, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWhithDependencySum = myBeanWhithDependencySum;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);

	}
/*
	@Override
	public void run(String... args){
		//ejemplosAnteriores();
		//saveUsersInDataBase();
		//getInformationJpqlFromUser();
		//sabeWithErrorTransactional();
	}
*/
	private void sabeWithErrorTransactional(){
		User test1 = new User("Cesar", "Barrios", LocalDate.now());
		User test2 = new User("Adriana", "Barrios", LocalDate.now());
		User test3 = new User("Norma", "Paliza", LocalDate.now());
		User test4 = new User("Carla", "Quispe", LocalDate.now());
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		userService.saveTransactional(users);
		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Este es el usuario por el metodo transaccional " + user));
	}

	private void getInformationJpqlFromUser(){
		/*
		LOGGER.info("USUARIO 1 CON QUERY JPQL" + userRepository.findByUserEmail("martin@email.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findByAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("User with method findByAndSort:" + user));

		LOGGER.info("User with method findUserByNameAndEmail: " + userRepository.findUsersByNameAndAndEmail("Diego", "iego@email.com")
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario por el email dado")));

		userRepository.findByName("Gustavo").stream().forEach(user -> LOGGER.info("User with query method : " + user));
		*/
		LOGGER.info("El usuario a partir del named parameter es: " + userRepository.findByNameOrEmail("Diego", "diego@email.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario por el named parametro")));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("Martin", "martin@email.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Luis", "luis@email.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Gustavo", "gustavo@email.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Diego", "diego@email.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Valeria", "valeria@email.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Johnny", "johnny@email.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Paula", "paula@email.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Florentino", "florentino@email.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Victoria", "victoria@email.com", LocalDate.of(2021, 4, 10));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);

		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		//myBeanWithDependency.printWithDepndecy();
		//myBeanWhithDependencySum.Result();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword());
		LOGGER.error("Esto es un error...");
	}
}
