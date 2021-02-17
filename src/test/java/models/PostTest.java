package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PostTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Post.clearAll(); //clears out all posts before each test
    }

    public Post setUpNewPost(){
        return new Post("Day 1: intro");
    }
    @Test
    public void newPostGetsCreatedCorrectly_true() throws Exception {
        Post post = setUpNewPost();
        assertEquals(true, post instanceof Post);
    }

    @Test
    public void postInstiatesWithContent_true() throws Exception{
        Post post = setUpNewPost();
        assertEquals("Day 1: intro", post.getContent());
    }

    @Test
    public void allPostsAreCorrectlyReturned_true() {
        Post newPost = setUpNewPost();
        Post otherPost = new Post("How to learn effectively");
        assertEquals(2, Post.getAll().size());
    }

    @Test
    public void postContainsAllPosts_true() {
        Post newPost = setUpNewPost();
        Post otherPost = new Post("How to learn effectively");
        assertTrue(Post.getAll().contains(newPost));
        assertTrue(Post.getAll().contains(otherPost));
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() {
        Post newPost = setUpNewPost();
        assertEquals(false, newPost.getPublished());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Post newPost = setUpNewPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), newPost.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_postsInstantiateWithAnID_1() throws Exception{


        Post myPost = new Post("Day 1: Intro");
        Post.clearAll();
        assertEquals(1, myPost.getId());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception {
        Post post = setUpNewPost();
        assertEquals(1, Post.findById(post.getId()).getId());
    }

    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() {
        Post post = setUpNewPost();
        Post otherPost = new Post("How to lean effectively");
        assertEquals(2, Post.findById(otherPost.getId()).getId());
    }

    @Test
    public void updateChangePostContent() throws Exception {

        Post post = setUpNewPost();

        String formerContent = post.getContent();
        LocalDateTime formerDate = post.getCreatedAt();
        int formerId = post.getId();

        post.update("Android: Day 40");

        assertEquals(formerId, post.getId());
        assertEquals(formerDate, post.getCreatedAt());
        assertEquals(formerContent, post.getContent());
    }

    @Test
    public void deleteDeletesASpescificPost() throws Exception {
        Post post = setUpNewPost();
        Post otherPost = new Post("How to communicate effectively");
        post.deletePost();
        assertEquals(1, Post.getAll().size()); //that is only one is left
        assertEquals(Post.getAll().get(0).getId(), 2);
    }

    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Post post = setUpNewPost();
        Post otherPost = setUpNewPost();

        Post.clearAll();
        assertEquals(0, Post.getAll().size());
    }
}