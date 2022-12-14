package com.example.random_generator.paricipant_selection.Participant;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> participants() {
        return participantRepository.findAll();
    }

    public void addNewParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public void addOtherParticipant(Participant participant) {
        int x = 0;
        while (x < participants().size()) {
            if (!participants().get(x).getName().equals(participant.getName())) {
                participantRepository.save(participant);
            } else {
                throw new IllegalStateException("Participant Already exists");
            }
            x++;
        }
    }

    public List<Participant> getParticipantByGender(Gender gender) {
        return participantRepository.findByGender(gender);
    }

    public List<Participant> setParticipants(int count, @NotNull Gender gender) {
        List<Participant> participants = new ArrayList<>();
        Random random = new Random();
        int range = count+1;
        int x = 0;
        if (gender.equals(Gender.Male)) {
            while (x < count) {
                participants.add(getParticipantByGender(Gender.Female).get(random.nextInt(range)));
                x++;
            }
        } else if (gender.equals(Gender.Female)) {
            while (x < count) {
                participants.add(getParticipantByGender(Gender.Male).get(random.nextInt(range)));
                x++;
            }
        }
        return participants;
    }

    public boolean newParticipantResponse(Participant participant) {
        int x = 0;
        List<Participant> participantList = participantRepository.findAll();
        while (x < participantList.size()) {
            if (participant.getName().equalsIgnoreCase(participantList.get(x).getName()) && participant.getGender().equals(participantList.get(x).getGender())) {
                return false;
            }
            x++;
        }
        return true;
    }

    public void deleteParticipantById(Long id){
        participantRepository.deleteById(id);
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }
}
