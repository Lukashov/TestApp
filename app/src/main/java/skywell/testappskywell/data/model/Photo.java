package skywell.testappskywell.data.model;

/**
 * Created by Den on 01.11.16.
 */

public class Photo {

    private String pid;
    private String owner_id;
    private String src_big;
    private String created;
    private String post_id;
    private String access_key;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getSrc_big() {
        return src_big;
    }

    public void setSrc_big(String src_big) {
        this.src_big = src_big;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }
}
