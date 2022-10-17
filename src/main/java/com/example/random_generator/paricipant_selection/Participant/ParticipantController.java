package com.example.random_generator.paricipant_selection.Participant;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showParticipantDetail(@NotNull Model model) {
        model.addAttribute("participant_info", participantService.participants());
        return "participant_home";
    }

    @GetMapping("/delete/{id}")
    public String deleteSelectedParticipant(@NotNull Model model, @PathVariable Long id) {
        participantService.deleteParticipantById(id);
        model.addAttribute("participant_info", participantService.participants());
        return "participant_home";
    }

    @GetMapping("/game2/{id}")
    public String gameOne(@PathVariable Long id, @NotNull Model model) {
        model.addAttribute("participant_info", participantService.participants().get(id.intValue()));
        model.addAttribute("random_select", participantService.setParticipants(3, participantService.getParticipantById(
                participantService.participants().get(id.intValue()).getId()).get().getGender()));
        return "game_2.html";
    }

    /**
     * @GetMapping("/select") public String selectRandomParticipant(@NotNull Model model, @ModelAttribute @NotNull Participant participant){
     * model.addAttribute("participant_select",participantService.setParticipants(3,participant.getGender()));
     * return "participant_list";
     * }
     * @GetMapping("/select/{id}")
     * @ResponseBody public List<Participant> selectedRandoms(@PathVariable int id){
     * return participantService.setParticipants(3,participantService.participants().get(id).getGender());
     * }
     */

    public void addNewParticipant(Participant participant) {
        participantService.addNewParticipant(participant);
    }

    @PostMapping("/new_participant")
    public String inputNewParticipant(@NotNull Model model, @ModelAttribute Participant participant) {
        if (participantService.newParticipantResponse(participant) != true) {
            model.addAttribute("participant_info", new Participant());
            model.addAttribute("participant_alert", "Participant already registered");
            return "participant_create";
        }
        addNewParticipant(participant);
        model.addAttribute("participant_info", participantService.participants());
        return "participant_home";
    }
}
