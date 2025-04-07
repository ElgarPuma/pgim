package pe.gob.osinergmin.pgim.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ImageInfo {
    
    private String id;
    private String type;
    private List<Map<String, Object>> bands;
    private Map<String, Object> properties;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("bands")
    public List<Map<String, Object>> getBands() {
        return bands;
    }

    public void setBands(List<Map<String, Object>> bands) {
        this.bands = bands;
    }

    @JsonProperty("properties")
    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", bands=" + bands +
                ", properties=" + properties +
                '}';
    }
}
