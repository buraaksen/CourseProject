package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lv.venta.model.Room;
import lv.venta.service.IRoomCRUDService;

@Controller
@RequestMapping("/room/crud")
public class RoomCRUDController {

    @Autowired
    
    private IRoomCRUDService roomService;

    // localhost:8080/room/crud/all
    @GetMapping("/all")
    public String getAllRooms(Model model) {

        try {
            ArrayList<Room> rooms = roomService.retrieveAllRooms();

            model.addAttribute("box", rooms);

            return "all-rooms-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/crud/all/1
    @GetMapping("/all/{id}")
    public String getRoomById(
            @PathVariable(name = "id") int id,
            Model model) {

        try {
            Room room = roomService.retrieveRoomById(id);

            model.addAttribute("box", room);

            return "room-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/crud/delete/1
    @GetMapping("/delete/{id}")
    public String deleteRoom(
            @PathVariable(name = "id") int id,
            Model model) {

        try {

            roomService.deleteRoomById(id);

            return "redirect:/room/crud/all";

        } catch (Exception e) {

            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/crud/add
    @GetMapping("/add")
    public String getRoomAdd(Model model) {

        model.addAttribute("room", new Room());

        return "add-room-page";
    }

    @PostMapping("/add")
    public String postRoomAdd(
            @Valid Room room,
            BindingResult problems,
            Model model) {

        if (problems.hasErrors()) {
            return "add-room-page";
        }

        try {

            roomService.create(
                    room.getPrId(),
                    room.getRoomNumber(),
                    room.getCapacity(),
                    room.getPricePerNight());

            return "redirect:/room/crud/all";

        } catch (Exception e) {

            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/room/crud/update/1
    @GetMapping("/update/{id}")
    public String getRoomUpdate(
            @PathVariable(name = "id") int id,
            Model model) {

        try {

            Room room = roomService.retrieveRoomById(id);

            model.addAttribute("room", room);

            return "update-room-page";

        } catch (Exception e) {

            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postRoomUpdate(
            @PathVariable(name = "id") int id,
            @Valid Room room,
            BindingResult problems,
            Model model) {

        if (problems.hasErrors()) {
            return "update-room-page";
        }

        try {

            roomService.updateRoomById(
                    id,
                    room.getPrId(),
                    room.getRoomNumber(),
                    room.getCapacity(),
                    room.getPricePerNight());

            return "redirect:/room/crud/all";

        } catch (Exception e) {

            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}