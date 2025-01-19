/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dev.dexterincs.content_calendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.dexterincs.content_calendar.model.Content;
import dev.dexterincs.content_calendar.model.Status;
import dev.dexterincs.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;

/**
 *
 * @author Clyde
 */

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    /**
     * 
     */
    public ContentCollectionRepository() {
      // TODO document why this constructor is empty
    }

    @PostConstruct
    private void init()
    {
        Content c = new Content(
            1,
            "My first Blog Post",
            "My first blog post",
            Status.IDEA,
            Type.COURSE,
            LocalDateTime.now(),
            null,
            ""
        );

        contentList.add(c);
    }

    public List<Content> findAll()
    {
        return contentList;
    }

    public Optional<Content> findById(Integer id)
    {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content)
        {
            contentList.removeIf(c -> c.id().equals(content.id()));
            contentList.add(content);
        }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }
    
}
