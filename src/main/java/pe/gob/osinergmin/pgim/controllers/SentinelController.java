package pe.gob.osinergmin.pgim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.pgim.services.EarthEngineService;
import pe.gob.osinergmin.pgim.utils.ImageInfo;

@RestController
@RequestMapping("/gee")
public class SentinelController {
    
    @Autowired
    private EarthEngineService earthEngineService;

     @GetMapping("/images")
    public ImageInfo[] getImages(@RequestParam String area,
                              @RequestParam String startDate, 
                              @RequestParam String endDate) {
        return earthEngineService.getImages(area, startDate, endDate);
    }
}
