package daos;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import models.User;

public class UserDAO {

    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public User getUser(Long userId) {
        User u = em.find(User.class, userId);
        return u;
    }

    public List<User> getUsers() {
        Query query = em.createQuery("SELECT u FROM User as u");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    public User getUserWithUsernameAndPassword(String username, String password) {
        Query query = em.createQuery(
                "SELECT u FROM User as u "
                + "WHERE u.username = :username AND u.password = :password", 
                User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List users = query.getResultList();
        if (!users.isEmpty()) {
            return (User) users.get(0);
        }
       return null; 
    }
}
