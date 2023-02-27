package sg.edu.nus.iss.day29workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.util.MultiValueMap;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    // Inspect Google chrome page -> Network -> Headers/Payload to check for attributes and type of controller needed
    private String orderId;
    private String name;
    private String email;
    private String deliveryDate;
    //private LocalDate deliveryDate;
    private List<LineItem> lineItems = new LinkedList<>();

    // to convert from String to JsonObject to set our Order Object attributes
    public static Order createOrder(String json) throws IOException{
        Order order = new Order();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            order.setName(o.getString("name"));
            order.setEmail(o.getString("email"));
            order.setDeliveryDate(o.getString("deliveryDate"));
            //order.setDeliveryDate(new Date());
            //order.setDeliveryDate(LocalDate.parse(o.getString("deliveryDate"));
            order.lineItems = o.getJsonArray("lineItems")
                                .stream()
                                .map(v-> (JsonObject)v)
                                .map(v-> LineItem.createJson(v))
                                .toList();
            
        }
        return order;
    }
    // Convert to document to insert into MongoDB
    public Document toDocument() {
        Document doc = new Document();
        doc.put("orderId",getOrderId());
        doc.put("name", getName());
        doc.put("email", getEmail());
        doc.put("deliveryDate", getDeliveryDate());
        doc.put("lineItems", getLineItems());
        return doc;
    }
}
