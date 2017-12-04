package mobi.ingogo.interview.dao;

import mobi.ingogo.interview.dto.RouteRequestDto;
import mobi.ingogo.interview.dto.RouteResponseDto;
import mobi.ingogo.interview.service.geocoder.GeocodeResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
public class GeopositionDAOImpl implements IGeopositionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addGeocodeResult(GeocodeResult geocodeResult) {
        entityManager.persist(geocodeResult);
    }

    @Override
    public GeocodeResult findGeocodeResultById(Integer id) {
        return entityManager.find(GeocodeResult.class, id);
    }

    @Override
    public void addRouteRequest(RouteRequestDto routeRequestDto) {
        entityManager.persist(routeRequestDto);
    }

    @Override
    public RouteRequestDto findRouteRequestById(Integer id) {
        return entityManager.find(RouteRequestDto.class, id);
    }

    @Override
    public void addRouteResponse(RouteResponseDto routeResponseDto) {
        entityManager.persist(routeResponseDto);
    }

    @Override
    public RouteResponseDto findRouteResponseById(Integer id) {
        return entityManager.find(RouteResponseDto.class, id);
    }

}
