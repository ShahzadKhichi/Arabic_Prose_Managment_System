package com.apms.dal;

import java.util.List;

import com.apms.dto.ProseDTO;

public interface IProseDAO {
    boolean insertProse(ProseDTO prose);
    ProseDTO getProseById(int id);
    List<ProseDTO> getAllProse();
    boolean updateProse(ProseDTO prose);
    boolean deleteProse(int id);
}