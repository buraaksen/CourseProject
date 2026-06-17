package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lv.venta.model.Room;
import lv.venta.service.IRoomFiltering;

@Controller
@RequestMapping("/room/filter")
public class RoomFilteringController {

    @Autowired
    private IRoomFiltering roomFiltering;

    // localhost:8080/room/filter/capacity/10
    @GetMapping("/capacity/{capacity}")
    public String findByCapacityGreaterThanEqual(
            @PathVariable("capacity") int capacity,
            Model model) {

        try {
            ArrayList<Room> rooms =
                    roomFiltering.filterByCapacity(capacity);

            model.addAttribute("box", rooms);
            return "all-rooms-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/filter/property/1
    @GetMapping("/property/{id}")
    public String filterByProperty(
            @PathVariable("id") int propertyId,
            Model model) {

        try {
            ArrayList<Room> rooms =
                    roomFiltering.filterByPropertyId(propertyId);

            model.addAttribute("box", rooms);
            return "all-rooms-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}