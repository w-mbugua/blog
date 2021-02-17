package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;

    public Post(String content){
        this.content = content;
        instances.add(this);
        this.published = false;
        this.createdAt = LocalDateTime.now();
        this.id = instances.size();
    }

    public String getContent() {
        return content;
    }

    public static ArrayList<Post> getAll(){
        return instances;
    }

    public static void clearAll(){
        instances.clear();
    }

    public boolean getPublished(){
        return this.published;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public int getId(){
        return id;
    }

    public static Post findById(int Id){
        return instances.get(Id-1);
    }
    public void update(String content){
        this.content = content;
    }

    public void deletePost(){
        instances.remove(id-1);
    }
}
