package sg.edu.nus.iss.day29workshop.model;

import org.springframework.util.MultiValueMap;

import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    private String item;
    private Integer quantity;

    public static LineItem createJson(JsonObject o){
        LineItem item = new LineItem();
        item.setItem(o.getString("item"));
        item.setQuantity(o.getInt("quantity"));
        return item;
    }
}
