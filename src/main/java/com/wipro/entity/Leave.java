package com.wipro.entity;

public class Leave {
    
    private int leaveId;
    private String employeeName;
    private String leaveType;
    private String startDate;
    private String endDate;
    private int leaveDays;
    private String leaveStatus;

    // ✅ Correct getter & setter names
    public int getLeaveId() {
        return leaveId;
    }
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getLeaveDays() {
        return leaveDays;
    }
    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }
    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
