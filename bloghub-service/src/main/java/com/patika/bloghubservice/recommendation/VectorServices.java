package com.patika.bloghubservice.recommendation;

import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.service.BlogService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VectorServices {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    BlogService blogService;


    public List<Document> simpleVector(String query){

        List<BlogResponse> blogList = blogService.getAll();

        String availableBlogs = blogList.stream()
                .map(blog -> "Title: " + blog.getTitle() + ", Text: " + blog.getText())
                .collect(Collectors.joining(", "));

        List<Document> documents = List.of(
                new Document(availableBlogs));

        vectorStore.add(documents);


        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.defaults()
                        .withQuery(query)
                        .withTopK(2)
        );
        return results;
    }

}