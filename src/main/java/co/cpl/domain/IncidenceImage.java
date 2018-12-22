package co.cpl.domain;

public class IncidenceImage {

  private final String id;
  private final String id_incidence;
  private final String url;
  private final String urlDisplay;
  private final String createdAt;
  private final String updatedAt;

  public IncidenceImage(String id,
                   String id_incidence,
                   String url,
                   String urlDisplay,
                   String createdAt,
                   String updatedAt) {
    this.id = id;
    this.id_incidence = id_incidence;
    this.url = url;
    this.urlDisplay = urlDisplay;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getId_incidence() {
    return id_incidence;
  }

  public String getUrl() {
    return url;
  }

  public String getUrlDisplay() {
    return urlDisplay;
  }


  public static class Builder {
    private String id;
    private String id_incidence;
    private String url;
    private String urlDisplay;
    private String createdAt;
    private String updatedAt;

    public IncidenceImage.Builder setId(String id) {
      this.id = id;
      return this;
    }

    public IncidenceImage.Builder setIdIncidence(String id_incidence) {
      this.id_incidence = id_incidence;
      return this;
    }

    public IncidenceImage.Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public IncidenceImage.Builder setUrlDisplay(String url) {
      this.urlDisplay = url;
      return this;
    }

    public IncidenceImage.Builder setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public IncidenceImage.Builder setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public IncidenceImage build() {
      return new IncidenceImage(id, id_incidence, url, urlDisplay, createdAt, updatedAt);
    }
  }
}
