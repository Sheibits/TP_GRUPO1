package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tfversion1.entities.Messages;

@Repository
public interface IMessagesRepository extends JpaRepository<Messages, Integer> {
}
