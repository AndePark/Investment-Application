package persistence;

import org.json.JSONArray;


public interface Writable {
    //EFFECTS: returns this as JSON array
    JSONArray toJson();
}
