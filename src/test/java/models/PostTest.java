package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void newPostGetsCreatedCorrectly_true() throws Exception {
        Post post = new Post("Day 1: intro");
        assertEquals(true, post instanceof Post);
    }

    @Test
    public void postInstiatesWithContent_true() throws Exception{
        Post post = new Post("Day 1: intro");
        assertEquals("Day 1: intro", post.getContent());
    }
}