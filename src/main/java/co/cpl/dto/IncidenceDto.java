package co.cpl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidenceDto {

    private String id;
    private String title;
    private String description;
    private String placa;
    private String date_device;
    private String date_user;
    private String direction_gps;
    private String direction_user;
    private String status;
    private String createdAt;
    private String updatedAt;

    @JsonProperty("id")
    public String getId() { return id;  }
    public void setId(String id) { this.id = id; }

    @JsonProperty("title")
    public String getTitle() { return title;  }
    public void setTitle(String title) { this.title = title;  }

    @JsonProperty("description")
    public String getDescription() { return description;  }
    public void setDescription(String description) { this.description = description; }

    @JsonProperty("placa")
    public String getPlaca() { return placa;  }
    public void setPlaca(String placa) { this.placa = placa; }

    @JsonProperty("date_device")
    public String getDateDevice() { return date_device; }
    public void setDateDevice(String date_device) { this.date_device = date_device; }

    @JsonProperty("date_user")
    public String getDateUser() { return date_user; }
    public void setDateUser(String date_user) { this.date_user = date_user; }

    @JsonProperty("direction_gps")
    public String getDirectionGps() { return direction_gps; }
    public void setDirectionGps(String direction_gps) { this.direction_gps = direction_gps; }

    @JsonProperty("city")
    public String getDirectionUser() { return direction_user; }
    public void setDirectionUser(String direction_user) { this.direction_user = direction_user; }

    @JsonProperty("status")
    public String getStatus() { return status;  }
    public void setStatus(String status) { this.status = status;  }


    @JsonProperty("createDate")
    public String getCreateDate() { return createdAt; }
    public void setCreateDate(String createDate) { this.createdAt = createDate; }


    @JsonProperty("updatedDate")
    public String getUpdateDate() { return updatedAt;  }
    public void setUpdateDate(String updateDate) { this.updatedAt = updateDate;  }

    @Override
    public String toString() {
        return "UsersDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", placa='" + placa + '\'' +
                ", date_device='" + date_device + '\'' +
                ", date_user='" + date_user + '\'' +
                ", direction_gps='" + direction_gps + '\'' +
                ", direction_user='" + direction_user + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createdAt + '\'' +
                ", updateDate='" + updatedAt + '\'' +
                '}';
    }
}
