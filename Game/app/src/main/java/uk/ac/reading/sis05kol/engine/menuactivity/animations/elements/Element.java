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

    FIREATTACK(new int[]{
            R.drawable.fa1,
            R.drawable.fa2,
            R.drawable.fa3,
            R.drawable.fa4,
            R.drawable.fa5,
            R.drawable.fa6,
            R.drawable.fa7,
            R.drawable.fa8,
            R.drawable.fa9},9,0),

    FIREIDLE(new int[]{
            R.drawable.fi1,
            R.drawable.fi2,
            R.drawable.fi3,
            R.drawable.fi4,
            R.drawable.fi5,
            R.drawable.fi6,
            R.drawable.fi7,
            R.drawable.fi8,
            R.drawable.fi9,
            R.drawable.fi10,
            R.drawable.fi11,
            R.drawable.fi12,
            R.drawable.fi13,
            R.drawable.fi14,
            R.drawable.fi15,
            R.drawable.fi16,
            R.drawable.fi17,
            R.drawable.fi18,
            R.drawable.fi19,
            R.drawable.fi20,
            R.drawable.fi21,
            R.drawable.fi22,
            R.drawable.fi23,
            R.drawable.fi24,
            R.drawable.fi25,
            R.drawable.fi26,
            R.drawable.fi27
    },27,0),

    ICEATTACK(new int[]{
            R.drawable.ia1,
            R.drawable.ia2,
            R.drawable.ia3,
            R.drawable.ia4,
            R.drawable.ia5,
            R.drawable.ia6,
            R.drawable.ia7,
            R.drawable.ia8,
            R.drawable.ia9},9,0),


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
