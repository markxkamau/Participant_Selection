package com.example.random_generator.paricipant_selection.Participant;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @GetMapping("/new_participant")
    public String addParticipantDetail(@NotNull Model model, @ModelAttribute Participant participant) {
        model.addAttribute("participant_info", new Participant());
        return "participant_create";
    }

    @GetMapping("/all_participant")
    public String showParticipantDetail(@NotNull Model model, @ModelAttribute Participant participant) {
        model.addAttribute("participant_info", participantService.participants());
        return "participant_home";
    }

    public void addNewparticipant(Participant participant) {
        participantService.addNewParticipant(participant);
    }

    @PostMapping("/new_participant")
    public String inputNewParticipant(@NotNull Model model, @ModelAttribute Participant participant) {
        addNewparticipant(participant);
        model.addAttribute("participant_info", participantService.participants());
        return "participant_home";
    }
}
