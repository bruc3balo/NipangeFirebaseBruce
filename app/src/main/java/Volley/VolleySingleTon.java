package Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleTon {
    private static VolleySingleTon mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleySingleTon(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleTon getInstance(Context context) {
        if(mInstance!=null) {
            mInstance = new VolleySingleTon(context);
        }
        Log.e("VolleySingleton","Succesfully added iinstance");
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
