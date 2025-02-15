import java.sql.SQLOutput;

public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit,String face) {
        this.suit = suit;
        this.face = face;
        this.internalHash=1;
    }

    @Override
    public String toString() {
        return face + "of" + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCard that = (PlayingCard) o;

        if (internalHash != that.internalHash) return false;
        if (!suit.equals(that.suit)) return false;
        return face.equals(that.face);
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + face.hashCode();
        result = 31 * result + internalHash;
        return result;
    }
}
