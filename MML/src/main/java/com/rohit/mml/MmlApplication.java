package com.rohit.mml;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.javafaker.Faker;
import com.rohit.mml.model.Movie;
import com.rohit.mml.model.MovieSmall;
import com.rohit.mml.model.User;
import com.rohit.mml.repository.MovieRepository;
import com.rohit.mml.repository.RoleRepository;
import com.rohit.mml.repository.UserRepository;
import com.rohit.mml.util.FileUtils;

@SpringBootApplication
public class MmlApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(MmlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // this.saveUser(10);
        // if (userRepository.count() <= 0) {
        // this.saveUser(3);
        // }
        // if (roleRepository.count() <= 0) {
        // Role rAdmin = new Role(RoleName.ROLE_ADMIN);
        // Role rUser = new Role(RoleName.ROLE_USER);
        // Role rPm = new Role(RoleName.ROLE_MOD);
        // roleRepository.save(rAdmin);
        // roleRepository.save(rPm);
        // roleRepository.save(rUser);
        //
        // }

        // initMovieDB();
        // copy();

    }

    // @Autowired
    // MovieRepository2 movieRepository2;
    //
    // private void copy() {
    // movieRepository2.deleteAll();
    // List<Movie> movies = movieRepository.findAll();
    // movies.forEach(m -> {
    // Movie2 m2 = new Movie2();
    // m2.setTitle(m.getTitle());
    // m2.setYear(m.getYear());
    // m2.setRated(m.getRated());
    // m2.setReleased(m.getReleased());
    // try {
    // String runTime = m.getRunTime().split(" ")[0];
    // m2.setRunTime(Integer.parseInt(runTime));
    // } catch (NumberFormatException e) {
    // m2.setRunTime(0);
    // }
    //
    // m2.setGenre(StringUtils.csvToList(m.getGenre()));
    // m2.setDirector(m.getDirector());
    // m2.setWriter(m.getWriter());
    // m2.setActors(StringUtils.csvToList(m.getActors()));
    // m2.setPlot(m.getPlot());
    // m2.setLanguage(StringUtils.csvToList(m.getLanguage()));
    // m2.setCountry(StringUtils.csvToList(m.getCountry()));
    // m2.setAwards(m.getAwards());
    // m2.setPoster(m.getPoster());
    // m2.setRatings(m.getRatings());
    // m2.setMetascore(m.getMetascore());
    // m2.setImdbRating(m.getImdbRating());
    // m2.setImdbVotes(m.getImdbVotes());
    // m2.setImdbID(m.getImdbID());
    // m2.setType(m.getType());
    // m2.setDvd(m.getDvd());
    // m2.setBoxOffice(m.getBoxOffice());
    // m2.setProduction(m.getProduction());
    // m2.setWebsite(m.getWebsite());
    // m2.setResponse(m.getResponse());
    //
    // movieRepository2.save(m2);
    // });
    //
    // }

    private void initMovieDB() throws IOException {
        String json = new FileUtils().getFileContentFromResources("movieUrls.json");
        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, MovieSmall.class);
        List<MovieSmall> movieSmall = mapper.readValue(json, typeReference);

        RestTemplate rt = new RestTemplate();
        movieSmall.forEach(m -> {
            ResponseEntity<String> resString = rt.getForEntity(m.getUrl(), String.class);
            if (resString.getStatusCode() == HttpStatus.OK) {
                Movie movie;
                try {
                    movie = mapper.readValue(resString.getBody(), Movie.class);
                    movieRepository.save(movie);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println(e);
                }
            }
        });
        System.out.println("...done");
    }

    public void saveUser(int n) {
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            String name = faker.name().firstName().toLowerCase();
            User u = new User(name, "password", new HashSet<>());
            userRepository.save(u);
        }
    }

}
