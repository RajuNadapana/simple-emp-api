package com.wipro.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.wipro.entity.Leave;

@Repository
public class LeaveRepository {
    private final List<Leave> leaves = new ArrayList<>();

    public List<Leave> findAll() {
        return leaves;
    }

    public void save(Leave leave) {
        // if leave with same id exists, replace it
        deleteById(leave.getLeaveId());
        leaves.add(leave);
    }

    public Optional<Leave> findById(int id) {
        return leaves.stream()
                .filter(l -> l.getLeaveId() == id)
                .findFirst();
    }

    public void deleteById(int id) {
        leaves.removeIf(l -> l.getLeaveId() == id);
    }
}
