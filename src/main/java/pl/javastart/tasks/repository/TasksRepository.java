package pl.javastart.tasks.repository;

import org.springframework.stereotype.Repository;
import pl.javastart.tasks.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.ifDone = 0 ORDER BY t.deadline", Task.class);
        transaction.commit();
        return query.getResultList();
    }

    public List<Task> doneTasksList() {
        transaction.begin();
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.ifDone = 1 ORDER BY t.deadline", Task.class);
        transaction.commit();
        return query.getResultList();
    }

    public void checkOffTask(Long id){
        Task task = entityManager.find(Task.class, id);
        entityManager.getTransaction().begin();
        task.setIfDone(true);
        entityManager.getTransaction().commit();
    }
}
