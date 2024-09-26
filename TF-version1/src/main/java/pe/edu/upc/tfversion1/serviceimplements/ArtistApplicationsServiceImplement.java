package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.ArtistApplications;
import pe.edu.upc.tfversion1.repositories.IArtistApplicationsRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistApplicationsService;

import java.util.List;

@Service
public class ArtistApplicationsServiceImplement implements IArtistApplicationsService {

    @Autowired
    private IArtistApplicationsRepository artistApplicationsRepository;

    @Override
    public List<ArtistApplications> list() {
        return artistApplicationsRepository.findAll();
    }

    @Override
    public void insert(ArtistApplications artistApplications) {
        artistApplicationsRepository.save(artistApplications);
    }

    @Override
    public void update(ArtistApplications artistApplications) {
        artistApplicationsRepository.save(artistApplications);
    }

    @Override
    public void delete(int id) {
        artistApplicationsRepository.deleteById(id);
    }
}
