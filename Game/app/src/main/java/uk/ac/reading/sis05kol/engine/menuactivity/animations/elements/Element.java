package uk.ac.reading.sis05kol.engine.menuactivity.animations.elements;

import uk.ac.reading.sis05kol.engine.R;

public enum Element {

    STORMATTACK(new int[]{
            R.drawable.sa1,
            R.drawable.sa2,
            R.drawable.sa3,
            R.drawable.sa4,
            R.drawable.sa5,
            R.drawable.sa6,
            R.drawable.sa7,
            R.drawable.sa8,
            R.drawable.sa9
    },9,0),
    STORMIDLE(new int[]{
            R.drawable.si1,
            R.drawable.si1,
            R.drawable.si2,
            R.drawable.si3,
            R.drawable.si4,
            R.drawable.si5,
            R.drawable.si6,
            R.drawable.si7,
            R.drawable.si8,
            R.drawable.si9,
            R.drawable.si10,
            R.drawable.si11,
            R.drawable.si12,
            R.drawable.si13

    },13,3),

    POISONIDLE(new int[]{
        R.drawable.pa1,R.drawable.pa2,R.drawable.pa3,
                R.drawable.pa4,R.drawable.pa5,R.drawable.pa6,
                R.drawable.pa7,R.drawable.pa8,R.drawable.pa9},9,0),
    POISONATTACK(new int[]{
            R.drawable.pi1,
            R.drawable.pi2,
            R.drawable.pi3,
            R.drawable.pi4,
            R.drawable.pi5,
            R.drawable.pi6,
            R.drawable.pi7,
            R.drawable.pi8,
            R.drawable.pi9,
            R.drawable.pi10,
            R.drawable.pi11,
            R.drawable.pi12,
            R.drawable.pi13,
            R.drawable.pi14,
            R.drawable.pi15,
            R.drawable.pi16,
            R.drawable.pi17,
            R.drawable.pi18,
            R.drawable.pi19,
            R.drawable.pi20,
            R.drawable.pi21,
            R.drawable.pi22,
            R.drawable.pi23,
            R.drawable.pi24,
            R.drawable.pi25,
            R.drawable.pi26,
            R.drawable.pi27,
            R.drawable.pi28,
            R.drawable.pi29,
            R.drawable.pi30,
            R.drawable.pi31,
            R.drawable.pi32,
            R.drawable.pi33,
            R.drawable.pi34,
            R.drawable.pi35,
            R.drawable.pi36,
            R.drawable.pi37,
            R.drawable.pi38,
            R.drawable.pi39,
            R.drawable.pi40,
            R.drawable.pi41,
            R.drawable.pi42,
            R.drawable.pi43},43,0);

    public int ids[];
    public int max;
    public int terminal;

    Element(int[] ids,int max,int terminal) {
        this.ids = ids;
        this.max=max;
        this.terminal=terminal;
    }
}
