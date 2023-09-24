package datasPojo;

public class ProjectPojo {
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
    private String task;
    private String project;
    private String email;
    private String phone;
    private String address;
    private String country_id;

    @Override
    public String toString() {
        return "ProjectPojo{" +
                "workspace='" + workspace + '\'' +
                ", name='" + name + '\'' +
                ", task='" + task + '\'' +
                ", project='" + project + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", country_id='" + country_id + '\'' +
                '}';
    }

    public ProjectPojo(String workspace, String name) {
        this.workspace = workspace;
        this.name = name;
    }


}
