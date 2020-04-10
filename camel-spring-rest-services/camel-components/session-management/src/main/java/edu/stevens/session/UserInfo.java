package edu.stevens.session;

public class UserInfo {

    private String firstName;
    private String lastName;
    private String id;
    private String mobileNo;
    private String emailId;

    public UserInfo() {

    }

    public UserInfo(String firstName, String lastName, String id, String mobileNo, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
