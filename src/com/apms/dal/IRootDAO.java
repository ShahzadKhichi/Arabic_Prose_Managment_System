package com.apms.dal;

import java.sql.SQLException;
import java.util.List;

import com.apms.dto.RootDTO;



public interface IRootDAO {
    boolean addRoot(String rootForm) throws SQLException;
    List<RootDTO> getAllRoots() throws SQLException;
    RootDTO getRootById(int rootId) throws SQLException;
    boolean updateRoot(int rootId, String rootForm) throws SQLException;
    boolean deleteRoot(int rootId) throws SQLException;
}
