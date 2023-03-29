package customQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*@Query("SELECT * FROM spring.flights WHERE flights.status = \"ON_TIME\"")
    public List<Flight> getONTimeFlights();*/

    /*@Query("SELECT f from airport.flights f WHERE f.status = ?#{[0]} OR f.status = ?#{[1]}")
    Optional<List<Flight>> findFlightsByStatus(Status status1, Status status2);

    @Query("SELECT f from airport.flights f WHERE f.status = ?#{#status1} OR f.status = ?#{#status2}")
    Optional<List<Flight>> findFlightsByStatus2(@Param("status1") Status status1, @Param ("status2") Status status2);

    @Query("SELECT f from flights f WHERE f.from_airport = ?#{[0]} OR f.from_airport = ?#{[1]")
    Optional<List<Flight>> findFlightsByAirport(String airport, String airport2);*/

    @Query(value = "SELECT * FROM airport.flights as f where f.status = ? OR f.status= ?", nativeQuery = true)// {1},1,?1
    Optional<List<Flight>> findFlightByStatus(String status1, String status2);

}
