package kg.geektech.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    private Integer id;
    private String title;
    private String content;
    @SerializedName("user")
    private Integer userId;
    @SerializedName("group")
    private Integer groupId;

    public Post(String title, String content, Integer userId, Integer groupId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
