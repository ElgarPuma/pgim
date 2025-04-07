package pe.gob.osinergmin.pgim.services;

import com.google.auth.oauth2.GoogleCredentials;

import pe.gob.osinergmin.pgim.utils.ImageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class EarthEngineService {
    
    // @Value("${google.earthengine.credentials}")
    // private Resource credentialsResource;

    private GoogleCredentials credentials;

    @Autowired
    private RestTemplate restTemplate;

    public ImageInfo[] getImages(String area, String startDate, String endDate) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:5000/get-images") // .fromUriString("http://ee-juanvalerio785.uc.r.appspot.com/get-images")
                .queryParam("area", area)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, ImageInfo[].class);
    }
}
