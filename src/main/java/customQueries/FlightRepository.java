package customQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*@Query("SELECT * FROM spring.flights WHERE flights.status = \"ON_TIME\"")
    public List<Flight> getONTimeFlights();*/

    @Query(value = "SELECT * FROM spring.flights WHERE flights.status = ?1 OR flights.status = ?2;", nativeQuery = true)
    Optional<List<Flight>> findFlightsByStatus(Status status1, Status status2);

}
