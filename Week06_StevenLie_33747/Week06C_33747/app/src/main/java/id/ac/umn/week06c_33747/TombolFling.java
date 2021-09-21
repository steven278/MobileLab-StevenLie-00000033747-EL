package id.ac.umn.week06c_33747;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

public class TombolFling extends AppCompatButton {
    public TombolFling(Context context) {
        super(context);
    }
    public TombolFling(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TombolFling(Context context, AttributeSet attrs,
                       int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                FlingAnimation fling = new FlingAnimation(
                        this, DynamicAnimation.ROTATION_X);
                fling.setStartVelocity(150)
                        .setFriction(0.11f)
                        .start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }

}
