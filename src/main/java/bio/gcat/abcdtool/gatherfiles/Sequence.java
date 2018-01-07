package bio.gcat.abcdtool.gatherfiles;

public class Sequence {
    String name;
    String id;

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

    @Override
    public String toString() {
        return "Sequence{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    public String getSpecies() {
        if (getName().contains("Homo sapiens")) {
            return "Homo sapiens";
        } else if (getName().contains("Caenorhabditis elegans")) {
            return "Caenorhabditis elegans";
        } else if (getName().contains("Arabidopsis thaliana")) {
            return "Arabidopsis thaliana";
        } else if (getName().contains("Chlamydomonas reinhardtii")) {
            return "Chlamydomonas reinhardtii";
        } else if (getName().contains("Oryza sativa Japonica")) {
            return "Oryza sativa Japonica";
        }

        //could also say reutnr spliit("chromosome")[0] for most of them
        return "SomeSpecies";
    }
}
