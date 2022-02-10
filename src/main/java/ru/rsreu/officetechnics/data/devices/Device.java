package ru.rsreu.officetechnics.data.devices;

import ru.rsreu.officetechnics.data.Entity;

import java.util.Objects;

public class Device extends Entity {

    private String title;
    private int ownerId;

    public Device() {
    }

    public Device(int id, String title, int ownerId) {
        super(id);
        this.title = title;
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        if (!super.equals(o)) return false;
        Device device = (Device) o;
        return ownerId == device.ownerId && Objects.equals(title, device.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, ownerId);
    }

    @Override
    public String toString() {
        return "Device{" +
                "title='" + title + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
