package lv.venta.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    // localhost:8080/room/filter/price
    @GetMapping("/price")
    public String filterByPriceRange(
            @RequestParam("min") float min,
            @RequestParam("max") float max,
            Model model) {

        try {
            ArrayList<Room> rooms =
                    roomFiltering.filterByPriceRange(min, max);

            model.addAttribute("box", rooms);
            return "all-rooms-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/filter/capacity/4
    @GetMapping("/capacity/{capacity}")
    public String filterByCapacity(
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

    // localhost:8080/room/filter/available?start=2026-06-01&end=2026-06-10
    @GetMapping("/available")
    public String filterByAvailability(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Model model) {

        try {
            ArrayList<Room> rooms =
                    roomFiltering.filterByDateAvilability(start, end);

            model.addAttribute("box", rooms);
            return "all-rooms-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}