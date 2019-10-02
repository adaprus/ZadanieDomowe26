package pl.javastart.tasks.repository;

import org.springframework.stereotype.Repository;
import pl.javastart.tasks.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class TasksRepository {
    private final EntityManager entityManager;
    private final EntityTransaction transaction;
    private EntityManagerFactory entityManagerFactory;

    public TasksRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    public void addTask(Task task) {
        transaction.begin();
        entityManager.persist(task);
        transaction.commit();
    }

    public List<Task> toDoList(){
        transaction.begin();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(currentDate);
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.deadline >" + dateString, Task.class);
        transaction.commit();
        return query.getResultList();
    }

}
