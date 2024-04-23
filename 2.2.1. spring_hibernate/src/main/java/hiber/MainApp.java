package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        try {
            System.out.println("\n" + userService.getUserAndCarByCarId("Toyota Carina ED", 201) + "\n");
        } catch (NoResultException e) {
            System.err.println("\n" + "Пользователей с таким авто в БД нет!!!" + "\n");
        }

//     User user1 = new User("User1", "Lastname1", "user1@mail.ru");
//     Car car1 = new Car("Toyota Carina ED", 160);
//     user1.setCar(car1);
//     userService.add(user1);
//
//     User user2 = new User("User2", "Lastname2", "user2@mail.ru");
//     Car car2 = new Car("Toyota Carina ED", 180);
//     user2.setCar(car2);
//     userService.add(user2);
//
//     User user3 = new User("User3", "Lastname3", "user3@mail.ru");
//     Car car3 = new Car("Toyota Carina ED", 200);
//     user3.setCar(car3);
//     userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        List<Car> cars = userService.listCar();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }
        context.close();
    }
}
