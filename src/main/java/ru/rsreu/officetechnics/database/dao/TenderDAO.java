package ru.rsreu.officetechnics.database.dao;

import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.devices.DeviceAccess;
import ru.rsreu.officetechnics.data.devices.DeviceProblem;
import ru.rsreu.officetechnics.data.users.Worker;

import java.util.ArrayList;

public interface TenderDAO {

    ArrayList<DeviceAccess> findReceivingTenders();

    ArrayList<DeviceProblem> findProblemTenders();

    DeviceProblem getDeviceProblem(int id);

    DeviceProblem getDeviceProblem(String id);

    DeviceAccess getDeviceTender(int id);

    DeviceAccess getDeviceTender(String id);

    void createTenderForDevice(Worker worker, Device device);

    void updateDeviceProblem(DeviceProblem deviceProblem);

    void deleteDeviceTender(Device device);

    void deleteDeviceTender(DeviceAccess deviceAccess);

    void createProblemTender(Worker worker, Device device, String text);

    void updateDeviceTender(DeviceAccess deviceAccess);

    void deleteProblemTender(DeviceProblem deviceProblem);
}
