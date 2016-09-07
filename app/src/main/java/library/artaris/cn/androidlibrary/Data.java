package library.artaris.cn.androidlibrary;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class Data  {

    /**
     * description : 一个介绍职人的，所
     * id : 33
     * name : 《职人介绍所》
     * thumbnail : http://pic2.zhimg.com/fa5612cedf533de03bf6f1fe89b5b539.jpg
     */

    private String description;
    private int id;
    private String name;
    private String thumbnail;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
