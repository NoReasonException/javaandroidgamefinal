package uk.ac.reading.sis05kol.engine.game.core.object.animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class DrawableAnimator {

    private Element element;
    protected List<Bitmap> bitmapArray=new ArrayList<>();
    private int state ;
    private LevelInfo levelInfo;

    private int delayState;
    private int maxDelayState;

    public DrawableAnimator(Element element, Context context, LevelInfo levelInfo) {
        this.element = element;
        this.state = 0;
        this.delayState=0;
        this.maxDelayState=5;
        this.levelInfo=levelInfo;
        this.bitmapArray= resourcesToMemory(element,context);
        bitmapArray=reshapeResources(bitmapArray,levelInfo.getScale());
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
        delayState=(delayState+1)%maxDelayState;
        if(delayState==3){
            state=(state+1)%element.max;
        }
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
