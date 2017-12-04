package mobi.ingogo.interview.dao;

import mobi.ingogo.interview.dto.RouteRequestDto;
import mobi.ingogo.interview.dto.RouteResponseDto;
import mobi.ingogo.interview.service.geocoder.GeocodeResult;

public interface IGeopositionDAO {
    void addGeocodeResult(GeocodeResult geocodeResult);
    GeocodeResult findGeocodeResultById(Integer id);
    void addRouteRequest (RouteRequestDto routeRequestDto);
    RouteRequestDto findRouteRequestById(Integer id);
    void addRouteResponse (RouteResponseDto routeResponseDto);
    RouteResponseDto findRouteResponseById(Integer id);
}
