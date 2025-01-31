package dev.dexterincs.content_calendar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.dexterincs.content_calendar.model.Content;
import dev.dexterincs.content_calendar.repository.ContentCollectionRepository;

/**
 * ContentController
 */

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentCollectionRepository = contentCollectionRepository;
    }
    
    @GetMapping("")
    public List<Content> findAll()
    {
        return contentCollectionRepository.findAll();
    }
    

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id)
    {
        return contentCollectionRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!!"));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@RequestBody Content content)
    {
        contentCollectionRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id)
    {
        if (!contentCollectionRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        
        contentCollectionRepository.save(content); 
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        contentCollectionRepository.delete(id);
    }
    
}
