package apps.prince.wanderindia;

public class FetchedPlaces {
    private String imageUrl;
    private String location_by;
    private String name;

    public FetchedPlaces(String imageUrl, String location_by, String name) {
        this.imageUrl = imageUrl;
        this.location_by = location_by;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation_by() {
        return location_by;
    }

    public void setLocation_by(String location_by) {
        this.location_by = location_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
