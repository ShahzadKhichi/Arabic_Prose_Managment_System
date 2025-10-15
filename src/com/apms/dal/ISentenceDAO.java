package com.apms.dal;

import java.util.List;

import com.apms.dto.SentenceDTO;

public interface ISentenceDAO {
    boolean insertSentence(SentenceDTO sentence);
    SentenceDTO getSentenceById(int id);
    List<SentenceDTO> getAllSentences();
    boolean updateSentence(SentenceDTO sentence);
    boolean deleteSentence(int id);
}