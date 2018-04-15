package vyvital.fitz.data;

import org.json.JSONArray;

public abstract class GetRequestListener {

    abstract public void onResult(JSONArray res);
    abstract public void onFailed();
}
