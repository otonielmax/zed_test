package co.cpl.domain;

public class Incidence {

    private final String id;
    private final String title;
    private final String description;
    private final String placa;
    private final String date_device;
    private final String date_user;
    private final String direction_gps;
    private final String direction_user;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    public Incidence(String id,
                 String title,
                 String description,
                 String placa,
                 String date_device,
                 String date_user,
                 String direction_gps,
                 String direction_user,
                 String status,
                 String createdAt,
                 String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.placa = placa;
        this.date_device = date_device;
        this.date_user = date_user;
        this.direction_gps = direction_gps;
        this.direction_user = direction_user;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDate_device() {
        return date_device;
    }

    public String getDate_user() {
        return date_user;
    }

    public String getDirection_gps() {
        return direction_gps;
    }

    public String getDirection_user() {
        return direction_user;
    }


    public static class Builder {
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

        public Incidence.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Incidence.Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public Incidence.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Incidence.Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Incidence.Builder setPlaca(String placa) {
            this.placa = placa;
            return this;
        }

        public Incidence.Builder setDateDevice(String date_device) {
            this.date_device = date_device;
            return this;
        }

        public Incidence.Builder setDateUser(String date_user) {
            this.date_user = date_user;
            return this;
        }

        public Incidence.Builder setDirectionGPS(String direction_gps) {
            this.direction_gps = direction_gps;
            return this;
        }

        public Incidence.Builder setDirectionUser(String direction_user) {
            this.direction_user = direction_user;
            return this;
        }

        public Incidence.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Incidence.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Incidence build() {
            return new Incidence(id, title, description, placa, date_device, date_user, direction_gps, direction_user, status, createdAt, updatedAt);
        }
    }
}
