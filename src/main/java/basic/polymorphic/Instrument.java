package basic.polymorphic;

public class Instrument {
    public void play(Note note) {
        System.out.println("Instrument.play() " + note);
    }
}

enum Note {
    MIDDLE_C, C_SHARP, B_FLAT;
}

class Brass extends Instrument {
    public void play(Note note) {
        System.out.println("Brass.play() " + note);
    }
}

class Wind extends Instrument {
    public void play(Note note) {
        System.out.println("Wind.play() " + note);
    }
}

class Music {
    public static void tune(Instrument instrument, Note note) {
        instrument.play(note);
    }

    public static void main(String[] args) {
        Wind wind = new Wind();
        Brass brass = new Brass();
        tune(wind, Note.MIDDLE_C);
        tune(brass, Note.MIDDLE_C);
    }
}