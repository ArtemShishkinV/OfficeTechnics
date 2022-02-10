package ru.rsreu.officetechnics.data.devices;

import ru.rsreu.officetechnics.data.Entity;
import ru.rsreu.officetechnics.data.users.Worker;

import java.util.Objects;

public class DeviceProblem extends Entity {

    private Worker worker;
    private Device device;
    private ProblemType problemType;
    private String text;

    public DeviceProblem() {
    }

    public DeviceProblem(int id, Worker worker, Device device, ProblemType problemType, String text) {
        super(id);
        this.worker = worker;
        this.device = device;
        this.problemType = problemType;
        this.text = text;
    }

    public DeviceProblem(Worker worker, Device device, ProblemType problemType, String text) {
        this.worker = worker;
        this.device = device;
        this.problemType = problemType;
        this.text = text;
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

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceProblem)) return false;
        if (!super.equals(o)) return false;
        DeviceProblem that = (DeviceProblem) o;
        return Objects.equals(worker, that.worker) && Objects.equals(device, that.device) && problemType == that.problemType && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), worker, device, problemType, text);
    }

    @Override
    public String toString() {
        return "DeviceProblem{" +
                "worker=" + worker +
                ", device=" + device +
                ", problemType=" + problemType +
                ", text='" + text + '\'' +
                '}';
    }
}
