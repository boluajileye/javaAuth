package bolu.ajileye.authfinal.dto.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BibleResponse {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("requestTime")
    private Date requestTime;

    @JsonProperty("requestType")
    private String requestType;

    @JsonProperty("referenceId")
    private String referenceId;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private UserData data;

    // Getters and Setters

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {

        @JsonProperty("id")
        private String id;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("phone_country")
        private String phoneCountry;

        @JsonProperty("manager")
        private ManagerData manager;

        @JsonProperty("residence")
        private Object residence; // Change Object to the actual type if known

        @JsonProperty("sub_manager")
        private Object subManager; // Change Object to the actual type if known

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("estate_id")
        private String estateId;

        @JsonProperty("role")
        private String role;

        // Getters and Setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneCountry() {
            return phoneCountry;
        }

        public void setPhoneCountry(String phoneCountry) {
            this.phoneCountry = phoneCountry;
        }

        public ManagerData getManager() {
            return manager;
        }

        public void setManager(ManagerData manager) {
            this.manager = manager;
        }

        public Object getResidence() {
            return residence;
        }

        public void setResidence(Object residence) {
            this.residence = residence;
        }

        public Object getSubManager() {
            return subManager;
        }

        public void setSubManager(Object subManager) {
            this.subManager = subManager;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getEstateId() {
            return estateId;
        }

        public void setEstateId(String estateId) {
            this.estateId = estateId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class ManagerData {

        @JsonProperty("id")
        private String id;

        // Getters and Setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
