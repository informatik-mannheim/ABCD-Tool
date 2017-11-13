package bio.gcat.geneticcode.fasta;

public class Element {
    char base;
    int pos;

    public Element(char base, int pos) {
        this.base = base;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Element{" +
                "base=" + base +
                ", pos=" + pos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (base != element.base) return false;
        return pos == element.pos;
    }

    @Override
    public int hashCode() {
        int result = (int) base;
        result = 31 * result + pos;
        return result;
    }
}
