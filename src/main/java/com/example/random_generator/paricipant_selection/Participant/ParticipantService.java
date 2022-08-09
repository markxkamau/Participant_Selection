package com.example.random_generator.paricipant_selection.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> participants() {
        return participantRepository.findAll();
    }
    public void addNewParticipant(Participant participant){
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

    public List<Participant> getparticipantByGender(Gender gender){
        return participantRepository.findByGender(gender);
    }
    public List<Participant> setParticipants(int count, Gender gender){
        List<Participant> participants = new ArrayList<>();
        Random random = new Random(count);
        int x = 0;
        if (gender.equals(Gender.Male)){
            while (x < count){
                participants.add(getparticipantByGender(Gender.Female).get(random.nextInt()));
                x++;
            }
        }
        else if (gender.equals(Gender.Female)){
            while (x < count){
                participants.add(getparticipantByGender(Gender.Male).get(random.nextInt()));
                x++;
            }
        }
        return participants;
    }

}
