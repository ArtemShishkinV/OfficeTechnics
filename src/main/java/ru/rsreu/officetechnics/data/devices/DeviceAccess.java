package ru.rsreu.officetechnics.data.devices;

import ru.rsreu.officetechnics.data.Entity;
import ru.rsreu.officetechnics.data.users.Worker;

import java.util.Objects;

public class DeviceAccess extends Entity {

    private Worker worker;
    private Device device;
    private TenderType tenderType;

    public DeviceAccess() {
    }

    public DeviceAccess(int id, Worker worker, Device device, TenderType tenderType) {
        super(id);
        this.worker = worker;
        this.device = device;
        this.tenderType = tenderType;
    }

    public DeviceAccess(Worker worker, Device device, TenderType tenderType) {
        this.worker = worker;
        this.device = device;
        this.tenderType = tenderType;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public TenderType getTenderType() {
        return tenderType;
    }

    public void setTenderType(TenderType tenderType) {
        this.tenderType = tenderType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceAccess)) return false;
        if (!super.equals(o)) return false;
        DeviceAccess that = (DeviceAccess) o;
        return Objects.equals(worker, that.worker) && Objects.equals(device, that.device) && tenderType == that.tenderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), worker, device, tenderType);
    }

    @Override
    public String toString() {
        return "DeviceAccess{" + "worker=" + worker + ", device=" + device + ", tenderType=" + tenderType + '}';
    }
}
