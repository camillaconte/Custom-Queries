package customQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;
    private int numberOfFlights;

    /**
     * 1) for the provisioning of 50 flights where:
     * all the string values are randomly generated (using random.ints())
     * the default status is ON_TIME
     *
     * 2) for retrieving all the flights in the db
     */

    public static String getRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }


    @PostMapping
    public void create50RandomFlights() {
        int i;
        for (i = 0; i < 50; i++) {
            Flight flight = new Flight(getRandomString(), getRandomString(), getRandomString());
            flight.setStatus(Status.ON_TIME);
            flightRepository.save(flight);
        }
    }

    @GetMapping
    public List<Flight> getAllFlights () {
           return flightRepository.findAll();
    }

    /**
     * for the provisioning of n flights (where n is an optional query param; if absent, n=100):
     * all the string values are randomly generated (using random.ints())
     * the status is generated randomly
     *
     * for retrieving all the flights in the db using pagination and returning them in ascending order by fromAirport
     *
     * for retrieving all the flights that are ONTIME without using a custom query
     *
     * for retrieving - USING A CUSTOM QUERY - all the flights that are in p1 or in p2 status
     * consider that the user has to pass p1 and p2 as parameters to the GET request
     */

    @PostMapping("/create-n-flights")
    public void createNFlights(@RequestParam (required = false, defaultValue = "100") String numberInString) {
        int numberOfFlights = Integer.parseInt(numberInString);
        int i;
        for (i = 0; i < numberOfFlights; i++) {
            Flight flight = new Flight(getRandomString(), getRandomString(), getRandomString());
            flight.setStatus(Status.randomStatus());
            flightRepository.save(flight);
        }
    }

    @GetMapping("/get-flights-paged")
    public Page<Flight> get(@RequestParam(required = false) Optional<Integer> page,
                            @RequestParam(required = false) Optional<Integer> size){
        if(page.isPresent() && size.isPresent()){
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "fromAirport"));
                    Pageable pageable = PageRequest.of(page.get(), size.get(), sort);
            Page<Flight> flights = flightRepository.findAll(pageable);
            return flights;
        } else {
            Page<Flight> noPageFlights = Page.empty();
            return noPageFlights;
        }
    }

    @GetMapping("/on-time")
    public List<Flight> getOnTimeFlights(){
        List<Flight> flights = flightRepository.findAll().stream().filter(f -> f.getStatus() == Status.ON_TIME)
                .collect(Collectors.toList());;
        return flights;
    }

    /*@GetMapping("/get-by-status")
    public List<Flight> givenStatusFlights(@RequestParam String status1, @RequestParam String status2){
        //DEVO FARE UN "PARSE" da "String" a "Status" ???
        Optional<List<Flight>> statusFlights = flightRepository.findFlightsByStatus(status1, status2);
        if(statusFlights.isPresent()) {
            List<Flight> foundFlights = statusFlights.get();
            return foundFlights;
        } else {
            List<Flight> emptyFlightsList = new ArrayList<>();
            return emptyFlightsList;
        }
    }*/

}
