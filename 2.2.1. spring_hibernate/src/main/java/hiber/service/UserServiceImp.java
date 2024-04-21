package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDao carDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCar() {
        return carDao.listCar();
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void getUserAndCarByCarId() {

        String HQL = "FROM Car ms LEFT OUTER JOIN FETCH ms.carUser WHERE ms.id=:msid";
        Car car = sessionFactory.getCurrentSession().createQuery(HQL, Car.class).setParameter("msid", 3).uniqueResult();
        System.out.println("");
        System.out.println(car);
        System.out.println("id Car : " + car.getId());
        User user = car.getCarUser();
        System.out.println(user);
        System.out.println("");
    }

}
