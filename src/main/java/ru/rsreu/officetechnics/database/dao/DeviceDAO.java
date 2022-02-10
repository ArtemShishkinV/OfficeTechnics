package ru.rsreu.officetechnics.database.dao;

import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.users.Worker;

import java.util.ArrayList;

public interface DeviceDAO {

    ArrayList<Device> findAll();

    ArrayList<Device> findAllAvailable(Worker worker);

    ArrayList<Device> findAllByWorker(Worker worker);

    Device getDeviceById(int id);

    Device getDeviceById(String id);

    void updateDevice(Device device);

    void deleteDevice(Device device);

    void createDevice(Device device);

}
