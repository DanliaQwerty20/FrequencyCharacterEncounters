package io.proj3ct.testjob.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterFrequency {
    @NotNull
    @Pattern(regexp = "[^0-9]")
    private char character;
    @NotNull
    private int frequency;
}
