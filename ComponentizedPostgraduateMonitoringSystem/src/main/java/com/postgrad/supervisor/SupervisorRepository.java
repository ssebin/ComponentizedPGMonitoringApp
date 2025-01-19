package com.postgrad.supervisor;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SupervisorRepository {
    private final Map<String, Supervisor> supervisors = new HashMap<>();

    public void save(Supervisor supervisor) {
        supervisors.put(supervisor.getSupervisorId(), supervisor);
    }

    public Supervisor findById(String supervisorId) {
        return supervisors.get(supervisorId);
    }

    public List<Supervisor> findAll() {
        return new ArrayList<>(supervisors.values());
    }

    public void deleteById(String supervisorId) {
        supervisors.remove(supervisorId);
    }
}