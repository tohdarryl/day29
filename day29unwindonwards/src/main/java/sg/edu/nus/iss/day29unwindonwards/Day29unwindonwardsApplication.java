package sg.edu.nus.iss.day29unwindonwards;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day29unwindonwards.service.TvShowService;

@SpringBootApplication
public class Day29unwindonwardsApplication implements CommandLineRunner {

	@Autowired
	TvShowService tvShowSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day29unwindonwardsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// List<Document> results = tvShowSvc.countGenres();
		List<Document> results = tvShowSvc.histogramOfRatings();

		for (Document d : results)
			System.out.printf(">>> %s\n", d.toJson());
	}

}
