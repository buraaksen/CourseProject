package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lv.venta.model.Type;
import lv.venta.service.IPropertyFiltering;

@Controller
@RequestMapping("/property/filter")
public class PropertyFilteringController {

    @Autowired
    private IPropertyFiltering propertyFiltering;

    // localhost:8080/property/filter/type/Hotel
    @GetMapping("/type/{type}")
    public String getPropertiesByType(
            @PathVariable("type") Type type,
            Model model) {

        try {
            model.addAttribute("box",
                    propertyFiltering.filterByType(type));

            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/filter/location/Riga
    @GetMapping("/location/{location}")
    public String getPropertiesByLocation(
            @PathVariable("location") String location,
            Model model) {

        try {
            model.addAttribute("box",
                    propertyFiltering.filterByLocation(location));

            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/filter/available/true
    @GetMapping("/available/{status}")
    public String getPropertiesByAvailability(
            @PathVariable("status") boolean status,
            Model model) {

        try {
            model.addAttribute("box",
                    propertyFiltering.filterByAvailability(status));

            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}