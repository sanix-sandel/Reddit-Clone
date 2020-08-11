package com.example.demo.service;


import com.example.demo.exceptions.SpringRedditException;
import com.example.demo.model.Subreddit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubredditService {
    private final subredditRepository subredditRepository;
    private final AuthService authService;

    @Transactional(readOnly=true)
    public List<SubredditDto>getAll(){
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save=subredditRepository.save(subredditMapper.mapDtoSubreddit(subredditDto));
        subredditDto.setId(save.getID());
        return subredditDto;
    }

    @Transactional(readOnly=true)
    public SubredditDto getSubreddit(Long id){
        Subreddit subreddit=subredditRepository.findById(id)
                .orElseThrow(()->new SubredditNotFoundException("No subreddit found with ID "+id));
        return mapToDto(subreddit);
    }

    private SubredditDto mapToDto(Subreddit subreddit){
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .postCount(subreddit.getPosts().size())
                .build();
    }

    private Subreddit mapTosubreddit(SubredditDto subredditDto){
        return Subreddit.builder().name("/r/"+subredditDto.getName())
                .description(subredditDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(now()).build();
    }
}
