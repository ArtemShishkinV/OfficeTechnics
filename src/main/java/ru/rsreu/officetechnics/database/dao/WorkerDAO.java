package ru.rsreu.officetechnics.database.dao;

import ru.rsreu.officetechnics.data.users.Worker;

import java.util.ArrayList;

public interface WorkerDAO {

    ArrayList<Worker> findAll();

    Worker getWorkerById(int id);

    Worker getWorkerById(String id);

    Worker getWorkerByLogin(String login);

    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);

    void addWorker(Worker worker);

}
