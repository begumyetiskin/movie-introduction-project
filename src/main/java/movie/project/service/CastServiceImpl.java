package movie.project.service;

import movie.project.model.Cast;
import movie.project.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CastServiceImpl implements CastService {

    @Autowired
    CastRepository castRepository;

    @Autowired
    public CastServiceImpl (CastRepository castRepository){
        this.castRepository = castRepository;
    }

    @Override
    public Cast getCastById(Long id) {
        Optional<Cast> currentCast = castRepository.findById(id);
        if (currentCast.isPresent()) {
            return currentCast.get();
        }
        return null;
    }

    @Override
    public List<Cast> getAllCasts(){
        return castRepository.findAll();
    }

    @Override
    public Cast createCast(Cast cast){
        return castRepository.save(cast);
    }

    @Override
    public Cast updateCast(Cast cast) {
        long id = cast.getId();
        Optional<Cast> currentCast = castRepository.findById(id);
        if (currentCast.isPresent()) {
            Cast updatedCast = currentCast.get();
            updatedCast.setFirstName(cast.getFirstName());
            updatedCast.setLastName(cast.getLastName());
            castRepository.save(updatedCast);
        }
        return null;
    }

    @Override
    public void deleteCastById(Long id) {
        castRepository.deleteById(id);
    }
}
