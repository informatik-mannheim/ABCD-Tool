package bio.gcat.abcdtool.gatherfiles;

/**
 * A class that saves the name and id of GenBank sequences;
 * primarily for the batch downloader.
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class GenbankSequence {
  private String name;
  private String id;
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "Sequence{" +
            "name='" + name + '\'' +
            ", id='" + id + '\'' +
            ", url='" + url + '\'' +
            '}';
  }

  public String getSpecies() {
    return getSpecies(getName());
  }

  public static String getSpecies(String species) {
    if (species.contains("Homo sapiens")) {
      return "Homo sapiens";
    } else if (species.contains("Caenorhabditis elegans")) {
      return "Caenorhabditis elegans";
    } else if (species.contains("Arabidopsis thaliana")) {
      return "Arabidopsis thaliana";
    } else if (species.contains("Chlamydomonas reinhardtii")) {
      return "Chlamydomonas reinhardtii";
    } else if (species.contains("Oryza")) {
      return "Oryza sativa Japonica";
    } else if (species.contains("RandomCo")) {
      return "Random conditional DNA";
    } else if (species.contains("Random")) {
      return "Random DNA";
    }

    // Could also say return split("chromosome")[0] for most of them:
    return "SomeSpecies";
  }
}
