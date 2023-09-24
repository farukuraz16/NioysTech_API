package datasPojo;

public class UserServicePojo {
    /*
  curl --request POST \
     --url https://app.asana.com/api/1.0/projects \
     --header 'accept: application/json' \
     --header 'authorization: Bearer 1/1205543491884173:59c403d909d21bf0455d3cffe8db0327' \
     --header 'content-type: application/json' \
     --data '
{
  "data": {
    "name": "Marvel",
    "workspace": "1205543498728328"
  }
}
'
     */
    private String workspace;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String country_id;

    @Override
    public String toString() {
        return "{" +
                "id='" + workspace + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", country_id='" + country_id + '\'' +
                '}';
    }

    public UserServicePojo(String id, String email) {
        this.workspace = id;
        this.email = email;
    }

    public UserServicePojo(String id, String name, String lastname, String username, String email, String phone, String address, String country_id) {
        this.workspace = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country_id = country_id;
    }

    public UserServicePojo() {
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
