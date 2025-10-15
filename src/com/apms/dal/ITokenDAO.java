package com.apms.dal;

import java.sql.SQLException;
import java.util.List;

import com.apms.dto.TokenDTO;

public interface ITokenDAO {
    boolean addToken(String surfaceForm, int sentenceId, int position) throws SQLException;
    List<TokenDTO> getAllTokens() throws SQLException;
    TokenDTO getTokenById(int tokenId) throws SQLException;
    boolean updateToken(int tokenId, String surfaceForm, int sentenceId, int position) throws SQLException;
    boolean deleteToken(int tokenId) throws SQLException;
}
