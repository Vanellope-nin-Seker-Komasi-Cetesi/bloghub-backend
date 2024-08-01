package com.patika.bloghubservice.recommendation;

import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.model.UserReadHistory;
import com.patika.bloghubservice.service.BlogService;
import com.patika.bloghubservice.service.UserReadHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {
    private final UserReadHistoryService readHistoryService;

    private final BlogService blogService;
    private final ChatClient chatClient;
    private final VectorServices vectorServices;
    //private final OpenAiChatModel openAiChatModel;



/*



    public String recommend() {
        List<UserReadHistory> readHistory = readHistoryService.getReadHistory();
        List<BlogResponse> blogList = blogService.getAll();

        String readBlogs = readHistory.stream()
                .map(history -> "Title: " + history.getTitle() + ", Text: " + history.getText())
                .collect(Collectors.joining(", "));

        String availableBlogs = blogList.stream()
                .map(blog -> "Title: " + blog.getTitle() + ", Text: " + blog.getText())
                .collect(Collectors.joining(", "));

        String prompt = String.format("""
            Based on the user's reading history and the available blogs, provide a list of recommended blogs.
            The response should be in JSON format containing a list of objects with the following fields: "title", "text".
            Analyze the user's reading history and recommend blogs from the available list that are similar to the blogs the user has read.
            Don't add any other text to the response. Don't add the new line or any other symbols to the response. Send back the raw JSON.
            User's reading history: [%s]
            Available blogs: [%s]
        """, readBlogs, availableBlogs);

        final PromptTemplate promptTemplate = new PromptTemplate(prompt);

        ChatResponse response = openAiChatModel.call(promptTemplate.create());

        return response.getResult().getOutput().getContent();
    }
*/

    public BlogRecommendation getBlogRecommendations(String email) {

        List<UserReadHistory> readHistory = readHistoryService.getReadHistory();
        List<BlogResponse> blogList = blogService.getUserNotBlogs(email);
        BeanOutputParser<BlogRecommendation> parser = new BeanOutputParser<>(BlogRecommendation.class);

        String promptString = """
        Based on the user's reading history and the available blogs, provide a list of recommended blogs.
        The response should be in JSON format containing a list of objects with the following fields: "title", "text","image".
        Analyze the user's reading history and recommend blogs from the available list that are similar to the blogs the user has read.
        Only and only list the available blogs that are similar to the reading history.
        Don't add any other text to the response. Don't add the new line or any other symbols to the response. Send back the raw JSON.
        User's reading history: {readBlogs}
        Available blogs: {availableBlogs}
        Format: {format}
        Never recommend blogs that are already in the reading history.
    """;


        PromptTemplate promptTemplate = new PromptTemplate(promptString);

        String readBlogs = readHistory.stream()
                .map(history -> "Title: " + history.getTitle() + ", Text: "
                        + history.getText()+ ", Author: " + history.getAuthor()+ ", Image: " + history.getImage())
                .collect(Collectors.joining(", "));

        String availableBlogs = blogList.stream()
                .filter(blog -> readHistory.stream()
                        .noneMatch(history -> history.getTitle().equals(blog.getTitle()) &&
                                history.getText().equals(blog.getText())))
                .map(blog -> "Title: " + blog.getTitle() + ", Text: " + blog.getText()
                        + ", Image: " + blog.getImage()+ ", Tags: " + blog.getBlogTags())
                .collect(Collectors.joining(", "));

        promptTemplate.add("readBlogs", readBlogs);
        promptTemplate.add("availableBlogs", availableBlogs);
        promptTemplate.add("format", parser.getFormat());
        promptTemplate.setOutputParser(parser);

        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatClient.call(prompt);


        /*// Vector işlemi
        String vectorContent = vectorServices.simpleVector(prompt.toString()).get(0).getContent().toString();
        System.out.println("Vector Content: " + vectorContent); // Veya başka bir işleme tabi tutabilirsiniz*/


        String strResponse = response.getResult().getOutput().getContent();
        return parser.parse(strResponse);
    }



}