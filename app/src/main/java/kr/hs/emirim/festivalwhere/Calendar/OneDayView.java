package kr.hs.emirim.festivalwhere.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import kr.hs.emirim.festivalwhere.R;


public class OneDayView extends RelativeLayout {

    private static final String TAG = MConfig.TAG;
    private static final String NAME = "OneDayView";
    private final String CLASS = NAME + "@" + Integer.toHexString(hashCode());

    /** number text field */
    private TextView dayTv;
    /** message text field*/
    private TextView msgTv;
    /** Weather icon */
    private ImageView festivalIv;
    /** Value object for a day info */
    private OneDayData one;

    /**
     * OneDayView constructor
     * @param context context
     */
    public OneDayView(Context context) {
        super(context);
        init(context);
    }

    /**
     * OneDayView constructor for xml
     * @param context context
     * @param attrs AttributeSet
     */
    public OneDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {

        View v = View.inflate(context, R.layout.oneday, this);

        dayTv = (TextView) v.findViewById(R.id.onday_dayTv);
        festivalIv = (ImageView) v.findViewById(R.id.onday_festival);
        msgTv = (TextView) v.findViewById(R.id.onday_msgTv);
        one = new OneDayData();

    }

    /**
     * Set the day to display
     * @param year 4 digits of year
     * @param month Calendar.JANUARY ~ Calendar.DECEMBER
     * @param day day of month
     */
    public void setDay(int year, int month, int day) {
        this.one.cal.set(year, month, day);
    }

    /**
     * Set the day to display
     * @param cal Calendar instance
     */
    public void setDay(Calendar cal) {
        this.one.setDay((Calendar) cal.clone());
    }

    /**
     * Set the day to display
     * @param one OneDayData instance
     */
    public void setDay(OneDayData one) {
        this.one = one;
    }

    /**
     * Get the day to display
     * @return OneDayData instance
     */
    public OneDayData getDay() {
        return one;
    }

    /**
     * Set the message to display
     * @param msg message
     */
    public void setMessage(String msg){
        one.setMessage(msg);
    }

    /**
     * Get the message
     * @return message
     */
    public CharSequence getMessage(){
        return  one.getMessage();
    }

    /**
     * Same function with {@link Calendar#get(int)}<br>
     * <br>
     * Returns the value of the given field after computing the field values by
     * calling {@code complete()} first.
     *
     * @param field Calendar.YEAR or Calendar.MONTH or Calendar.DAY_OF_MONTH
     *
     * @throws IllegalArgumentException
     *                if the fields are not set, the time is not set, and the
     *                time cannot be computed from the current field values.
     * @throws ArrayIndexOutOfBoundsException
     *                if the field is not inside the range of possible fields.
     *                The range is starting at 0 up to {@code FIELD_COUNT}.
     */
    public int get(int field) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return one.get(field);
    }

    /**
     * Set weather
     * @param festival Festival instance
     */
    /**
     * Updates UI upon the value object.
     */
    public void refresh() {

        //HLog.d(TAG, CLASS, "refresh");

        dayTv.setText(String.valueOf(one.get(Calendar.DAY_OF_MONTH)));

        if(one.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayTv.setTextColor(Color.RED);
        }
        else if(one.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            dayTv.setTextColor(Color.BLUE);
        }
        else {
            dayTv.setTextColor(Color.BLACK);
        }
        msgTv.setText((one.getMessage()==null)?"":one.getMessage());

        if(one.isFestivalDay) {
            festivalIv.setImageResource(R.drawable.festival_check);
        }else{
            festivalIv.setImageResource(R.drawable.festival_check_none);
        }
    }
}