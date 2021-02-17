import com.sun.org.apache.xpath.internal.operations.Mod;
import models.Post;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show new post form
        get("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());


        //post: process new post form
        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");

        }, new HandlebarsTemplateEngine());

        //get: show all posts
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", posts);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post

        get("/posts/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfPostToFind = Integer.parseInt(request.params(":id"));

            Post foundPost = Post.findById(idOfPostToFind);

            model.put("post", foundPost);

            return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/posts/:id/update", (request, response) ->{
            Map<String, Object> model = new HashMap<>();

            int idOfPostToEdit = Integer.parseInt(request.params("id"));

            Post editPost = Post.findById(idOfPostToEdit);

            model.put("editPost", editPost);

            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        get("/posts/:id/update", (request, response) ->{
            Map<String, Object> model = new HashMap<>();

            String newContent = request.queryParams("content");

            int idOfPostToEdit = Integer.parseInt(request.params("id"));

            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContent);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual post
        get("/posts/:id/delete", (request, reponse) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfPostToDelete = Integer.parseInt(request.params("id"));

            Post deletePost = Post.findById(idOfPostToDelete);

            deletePost.deletePost();

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all posts
        get("/posts/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            Post.clearAll();

            return new ModelAndView(model, "success.hbs");

        }, new HandlebarsTemplateEngine());
    }
}
