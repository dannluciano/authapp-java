package daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import libs.BCrypt;
import models.UserModel;

public class UserDAO {

    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public UserModel getUser(Long userId) {
        UserModel u = em.find(UserModel.class, userId);
        return u;
    }

    public List<UserModel> getUsers() {
        Query query = em.createQuery("SELECT u FROM UserModel as u");
        List<UserModel> users = (List<UserModel>) query.getResultList();
        return users;
    }

    public UserModel getUserWithUsername(String username) {
        Query query = em.createQuery(
                "SELECT u FROM UserModel as u WHERE u.username = :username",
                UserModel.class);
        query.setParameter("username", username);
        List users = query.getResultList();
        if (!users.isEmpty()) {
            return (UserModel) users.get(0);
        }
        return new UserModel();
    }

    public UserModel getUserWithUsernameAndPassword(String username, String password) {
        Query query = em.createQuery(
                "SELECT u FROM UserModel as u "
                + "WHERE u.username = :username AND u.password = :password",
                UserModel.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List users = query.getResultList();
        if (!users.isEmpty()) {
            return (UserModel) users.get(0);
        }
        return new UserModel();
    }
    
    public Long getNumbersOfUsers() {
        Query query = em.createQuery("SELECT COUNT(u.id) FROM UserModel as u");
        return (Long) query.getSingleResult();
    }

    public void createUser(String username, String password) {
        UserModel u = new UserModel();
        u.setUsername(username);
        
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        u.setPassword(hashed);
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public void updateUserModel(Long userId, String username, String password) {
        UserModel u = getUser(userId);
        if (!username.isEmpty()) {
            u.setUsername(username);
        }
        if (!password.isEmpty()) {
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            u.setPassword(hashed);
        }
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
    }

    public void deleteUserModel(Long userId) {
        UserModel u = getUser(userId);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
    }

    public boolean authenticate(String username, String password) {
        UserModel user = getUserWithUsername(username);
        try {
            return BCrypt.checkpw(password, user.getPassword());
            
        } catch (Exception e) {
            return false;
        }
        
    }
}
