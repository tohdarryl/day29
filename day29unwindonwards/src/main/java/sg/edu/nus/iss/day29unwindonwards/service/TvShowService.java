package sg.edu.nus.iss.day29unwindonwards.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;

@Service
public class TvShowService {

    @Autowired
    private MongoTemplate template;

    /*
    db.tv.aggregate([
    { $unwind: "$genres"},
    {
    $group:{
       _id:"$genres",
       total: {$sum:1}
    }
    },
    {
    $sort:{ _id: 1}
    }
    ]);
     */
    public List<Document> countGenres() {
        // $unwind
        UnwindOperation unwindGenres = Aggregation.unwind("genres");

        // $group
        GroupOperation groupShowsByGenres = Aggregation.group("genres")
                .count().as("total");

        // $sort
        SortOperation sortByGenres = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

        Aggregation pipeline = Aggregation.newAggregation(unwindGenres, groupShowsByGenres, sortByGenres);

        // AggregationResults<Document> results =
        // template.aggregate(pipeline, "tv", Document.class);

        // to convert results to a list
        return template.aggregate(pipeline, "tv", Document.class).getMappedResults();
    }

    /*
    db.tv.aggregate([
    { 
    $bucket: {
    groupBy: "$rating.average",
    boundaries: [3, 6, 9],
    default: '>9',
    output: {
        titles: {$push: "$name"}
    }
    }
}
]);
     */
    public List<Document> histogramOfRatings() {

        BucketOperation ratingsBucket = Aggregation.bucket("rating.average")
                .withBoundaries(3, 6, 9)
                .withDefaultBucket(">9")
                .andOutput("name").push().as("titles");

        Aggregation pipeline = Aggregation.newAggregation(ratingsBucket);

        return template.aggregate(pipeline, "tv", Document.class)
                .getMappedResults();

    }
}
