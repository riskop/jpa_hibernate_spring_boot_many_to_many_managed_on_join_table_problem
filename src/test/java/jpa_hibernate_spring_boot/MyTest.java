package jpa_hibernate_spring_boot;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import jpa_hibernate_spring_boot.entity.Post;
import jpa_hibernate_spring_boot.entity.PostTag;
import jpa_hibernate_spring_boot.entity.Tag;
import jpa_hibernate_spring_boot.repository.PostRepository;
import jpa_hibernate_spring_boot.repository.PostTagRepository;
import jpa_hibernate_spring_boot.repository.TagRepository;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "" })
@TestPropertySource("/application.properties")
public class MyTest {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private PostTagRepository postTagRepository;

    @Test
    public void test() {
        Tag t = new Tag();
        t.setName("fun");
        t = tagRepository.saveAndFlush(t);
        
        Post p = new Post();
        p.setName("post1");
        p = postRepository.saveAndFlush(p);
        
        PostTag pt = new PostTag(p, t);
        postTagRepository.saveAndFlush(pt);

        Set<PostTag> postTags = postTagRepository.findByTagIdAndFetchPosts(1l);
        System.out.println("postTags: " + postTags);
        Post postFound = postTags.iterator().next().getPost();
        System.out.println("postFound: " + postFound);
    }
}

