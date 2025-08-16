package com.rt.prototype.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor // no-args constructor
@AllArgsConstructor // generates getters, setters, toString, equals, hashCode
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String author; // could be a separate table
    @Column(columnDefinition = "TEXT")
    private String content;

    private String category; // could be a separate table
    private String tags; // comma separated for now
}
