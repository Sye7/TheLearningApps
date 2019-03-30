package developers.hub.com.thelearningapp;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

class MyEditText extends AppCompatEditText {

    private Rect rect;
    private Paint paint;

    public MyEditText(Context context, AttributeSet attr){
        super(context,attr);
        init();
    }

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public void init(){
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int baseline = getBaseline();
        for(int i=0;i<getLineCount();i++){
            canvas.drawText(" "+(i+1),rect.left,baseline,paint);
            baseline+=getLineHeight();
        }
        super.onDraw(canvas);
    }
}