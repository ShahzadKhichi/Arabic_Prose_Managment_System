package com.apms.dal;

import java.sql.SQLException;
import java.util.List;

import com.apms.dto.LemmaDTO;

public interface ILemmaDAO {
    boolean addLemma(String lemmaForm) throws SQLException;
    List<LemmaDTO> getAllLemmas() throws SQLException;
    LemmaDTO getLemmaById(int lemmaId) throws SQLException;
    boolean updateLemma(int lemmaId, String lemmaForm) throws SQLException;
    boolean deleteLemma(int lemmaId) throws SQLException;
}