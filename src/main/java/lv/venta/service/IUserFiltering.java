package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Status;
import lv.venta.model.User;

public interface IUserFiltering {

	ArrayList<User> filterByName(String name) throws Exception;

	ArrayList<User> filterBySurname(String surname) throws Exception;

	ArrayList<User> filterByStatus(Status status) throws Exception;

}
