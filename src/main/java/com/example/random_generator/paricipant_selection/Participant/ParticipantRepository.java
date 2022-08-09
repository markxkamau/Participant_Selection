package com.example.random_generator.paricipant_selection.Participant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    List<Participant> findByGender(Gender gender);
}
