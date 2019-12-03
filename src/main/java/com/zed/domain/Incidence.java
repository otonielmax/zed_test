package com.zed.domain;

public class Incidence {

    private final String id;
    private final String id_user;
    private final String title;
    private final String description;
    private final String placa;
    private final String type;
    private final String date_device;
    private final String date_user;
    private final String direction_gps;
    private final Double direction_gps_lat;
    private final Double direction_gps_lng;
    private final String direction_user;
    private final Double direction_user_lat;
    private final Double direction_user_lng;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    public Incidence(String id,
                 String id_user,
                 String title,
                 String description,
                 String placa,
                 String type,
                 String date_device,
                 String date_user,
                 String direction_gps,
                 String direction_user,
                 String status,
                 String createdAt,
                 String updatedAt,
                 Double direction_gps_lat,
                 Double direction_gps_lng,
                 Double direction_user_lat,
                 Double direction_user_lng
        ) {
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.description = description;
        this.placa = placa;
        this.type = type;
        this.date_device = date_device;
        this.date_user = date_user;
        this.direction_gps = direction_gps;
        this.direction_gps_lat = direction_gps_lat;
        this.direction_gps_lng = direction_gps_lng;
        this.direction_user = direction_user;
        this.direction_user_lat = direction_user_lat;
        this.direction_user_lng = direction_user_lng;
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

    public String getType() {
        return type;
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

    public Double getDirectionGpsLat() {
        return direction_gps_lat;
    }

    public Double getDirectionGpsLng() {
        return direction_gps_lng;
    }

    public String getDirection_user() {
        return direction_user;
    }

    public Double getDirectionUserLat() {
        return direction_user_lat;
    }

    public Double getDirectionUserLng() {
        return direction_user_lng;
    }

    public String getId_user() {
        return id_user;
    }


    public static class Builder {
        private String id;
        private String id_user;
        private String title;
        private String description;
        private String placa;
        private String type;
        private String date_device;
        private String date_user;
        private String direction_gps;
        private Double direction_gps_lat;
        private Double direction_gps_lng;
        private String direction_user;
        private Double direction_user_lat;
        private Double direction_user_lng;
        private String status;
        private String createdAt;
        private String updatedAt;

        public Incidence.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Incidence.Builder setIdUser(String id_user) {
            this.id_user = id_user;
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

        public Incidence.Builder setType(String type) {
            this.type = type;
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

        public Incidence.Builder setDirectionGPSLat(Double direction_gps) {
            this.direction_gps_lat = direction_gps;
            return this;
        }

        public Incidence.Builder setDirectionGPSLng(Double direction_gps) {
            this.direction_gps_lng = direction_gps;
            return this;
        }

        public Incidence.Builder setDirectionUser(String direction_user) {
            this.direction_user = direction_user;
            return this;
        }

        public Incidence.Builder setDirectionUserLat(Double direction_gps) {
            this.direction_user_lat = direction_gps;
            return this;
        }

        public Incidence.Builder setDirectionUserLng(Double direction_gps) {
            this.direction_user_lng = direction_gps;
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
            return new Incidence(id, id_user, title, description, placa, type, date_device, date_user, direction_gps, direction_user, status, createdAt, updatedAt, direction_gps_lat, direction_gps_lng, direction_user_lat, direction_user_lng);
        }
    }
}
