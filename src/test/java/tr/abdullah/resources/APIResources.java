package tr.abdullah.resources;

public enum APIResources {

    GetPlace("/maps/api/place/get/json"),
    AddPlace("/maps/api/place/add/json"),
    UpdatePlace("/maps/api/place/update/json"),
    DeletePlace("/maps/api/place/delete/json");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}