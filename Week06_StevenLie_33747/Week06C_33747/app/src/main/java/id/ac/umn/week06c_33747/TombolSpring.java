package id.ac.umn.week06c_33747;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class TombolSpring extends AppCompatButton {
    public TombolSpring(Context context) {
        super(context);
    }
    public TombolSpring(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TombolSpring(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                final SpringAnimation anim = new
                        SpringAnimation(this, (FloatPropertyCompat) DynamicAnimation.Y, this.getY());
                anim.setStartVelocity(10000);
                anim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                anim.start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }
}

