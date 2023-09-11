package io.proj3ct.testjob.web;

import io.proj3ct.testjob.model.CharacterFrequency;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/frequency")
public class FrequencyController {
    @GetMapping
    public String showFrequencyForm(@NotNull Model model) {
        model.addAttribute("inputString", "");
        return "frequency-form";
    }

    @PostMapping
    public String calculateFrequency(@RequestParam("inputString") String inputString, Model model) {
        Map<Character, Integer> frequencyMap = calculateCharacterFrequency(inputString);
        Map<Character, Integer> sortedFrequencyMap = sortFrequencyMap(frequencyMap);
        model.addAttribute("frequencyMap", sortedFrequencyMap);

        return "frequency-result";
    }

    private Map<Character, Integer> calculateCharacterFrequency(String inputString) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : inputString.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }

    private Map<Character, Integer> sortFrequencyMap(Map<Character, Integer> frequencyMap) {
        Map<Character, Integer> sortedFrequencyMap = new LinkedHashMap<>();

        frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedFrequencyMap.put(entry.getKey(), entry.getValue()));

        return sortedFrequencyMap;
    }
}
