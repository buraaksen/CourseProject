package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lv.venta.model.Property;
import lv.venta.service.IPropertyCRUDService;

@Controller
@RequestMapping("/property/crud")
public class PropertyCRUDController {

    @Autowired
    private IPropertyCRUDService propertyService;

    // localhost:8080/property/crud/all
    @GetMapping("/all")
    public String getAllProperties(Model model) {

        try {
            ArrayList<Property> properties =
                    propertyService.retrieveAllProperties();

            model.addAttribute("box", properties);
            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/crud/all/1
    @GetMapping("/all/{id}")
    public String getPropertyById(
            @PathVariable(name = "id") int id,
            Model model) {

        try {
            Property property =
                    propertyService.retrievePropertyById(id);

            model.addAttribute("box", property);
            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/crud/delete/1
    @GetMapping("/delete/{id}")
    public String deleteProperty(
            @PathVariable(name = "id") int id,
            Model model) {

        try {

            propertyService.deletePropertyById(id);

            ArrayList<Property> properties =
                    propertyService.retrieveAllProperties();

            model.addAttribute("box", properties);
            return "all-properties-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/crud/add
    @GetMapping("/add")
    public String getPropertyAdd(Model model) {

        model.addAttribute("property", new Property());
        return "add-property-page";
    }

    @PostMapping("/add")
    public String postPropertyAdd(
            @Valid Property property,
            BindingResult problems,
            Model model) {

        if (problems.hasErrors()) {
            return "add-property-page";
        }

        try {

            propertyService.createProperty(
                    property.getName(),
                    property.getLocation(),
                    property.getType(),
                    property.getDescription(),
                    property.getIsAvailable());

            return "redirect:/property/crud/all";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/property/crud/update/1
    @GetMapping("/update/{id}")
    public String getPropertyUpdate(
            @PathVariable(name = "id") int id,
            Model model) {

        try {

            Property property =
                    propertyService.retrievePropertyById(id);

            model.addAttribute("property", property);

            return "update-property-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postPropertyUpdate(
            @PathVariable(name = "id") int id,
            @Valid Property property,
            BindingResult problems,
            Model model) {

        if (problems.hasErrors()) {
            return "update-property-page";
        }

        try {

            propertyService.updatePropertyById(
                    id,
                    property.getName(),
                    property.getLocation(),
                    property.getType(),
                    property.getDescription(),
                    property.getIsAvailable());

            return "redirect:/property/crud/all";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}