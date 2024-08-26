package com.example.lobbying.tag;

public record TagResponseDTO(Long id, String name) {
    public TagResponseDTO(Tag tag){
        this(tag.getId(), tag.getName());
    }
}
