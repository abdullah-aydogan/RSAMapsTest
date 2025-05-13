package tr.abdullah.resources;

import tr.abdullah.pojoClasses.Location;
import tr.abdullah.pojoClasses.Place;
import tr.abdullah.pojoClasses.UpdateData;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public Place addPlacePayload(String name, String language, String address) {

        Place p = new Place();

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+90) 555 555 5555");
        p.setWebsite("https://example.com");
        p.setName(name);

        List<String> myList = new ArrayList<>();

        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        Location l = new Location();

        l.setLat(40.8662129);
        l.setLng(29.2739068);

        p.setLocation(l);

        return p;
    }

    public UpdateData updatePlacePayload(String place_id, String address) {

        UpdateData ud = new UpdateData();

        ud.setPlace_id(place_id);
        ud.setKey("qaclick123");
        ud.setAddress(address);

        return ud;
    }

    public String deletePlacePayload(String placeId) {
        return "{\n" + "  \"place_id\": \"" + placeId + "\"\n" + "}";
    }
}