package library.artaris.cn.androidlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class Info  implements Serializable{
    public ArrayList<Data> getDate() {
        return date;
    }

    public void setDate(ArrayList<Data> date) {
        this.date = date;
    }

    @JsonProperty("data")
    private ArrayList<Data> date;
}
