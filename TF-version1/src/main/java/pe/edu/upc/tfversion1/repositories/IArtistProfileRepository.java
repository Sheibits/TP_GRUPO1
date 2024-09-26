package pe.edu.upc.tfversion1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tfversion1.entities.ArtistProfile;

@Repository
public interface IArtistProfileRepository extends JpaRepository<ArtistProfile, Integer> {
}
