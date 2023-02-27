package sg.edu.nus.iss.day29workshop.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day29workshop.model.Order;

@Repository
public class OrderRepo {
    public static String COLLECTION_ORDERS = "orders";

    @Autowired
    MongoTemplate template;

    // insert function
    public void insertOrder(Order o) {
		Document doc = o.toDocument();
		template.insert(doc, COLLECTION_ORDERS);
	}
}
