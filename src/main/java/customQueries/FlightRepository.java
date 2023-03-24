package customQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*@Query("SELECT * FROM spring.flights WHERE flights.status = \"ON_TIME\"")
    List<Flight> flightsList();*/

    /*@Query("SELECT * FROM spring.flights WHERE flights.status = ?1 OR flights.status = ?2")
    List<Flight> listFlight findFlightsByStatus(int p1, int p2);*/

}
