package uk.ac.reading.sis05kol.engine.game.core.object.animator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class DrawableAnimator {

    private Element element;
    private List<Bitmap> bitmapArray=new ArrayList<>();
    private int state ;

    public DrawableAnimator(Element element,Context context) {
        this.element = element;
        this.state = 0;
        this.bitmapArray= resourcesToMemory(element,context);
    }
    public DrawableAnimator(Element element, Context context,float scale) {
        this(element,context);

        bitmapArray=reshapeResources(bitmapArray,scale);
    }
    private List<Bitmap> reshapeResources(List<Bitmap>resources, float scale){
        return resources.stream().
                map((b)->Bitmap.createScaledBitmap(b,
                        Double.valueOf(b.getWidth()*scale).intValue(),
                        Double.valueOf(b.getHeight()*scale).intValue(), true)).
                collect(Collectors.toList());
    }
    private ArrayList<Bitmap> resourcesToMemory(Element element, Context context){
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for (int elementId :
                element.ids) {
            bitmaps.add(BitmapFactory.decodeResource
                    (context.getResources(),
                            elementId));
        }
        return bitmaps;
    }

    public Bitmap getBitmap() {
        Bitmap b= bitmapArray.get(state);
        state=(state+1)%element.max;
        return b;
    }
    public void reset(){
        state=0;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
