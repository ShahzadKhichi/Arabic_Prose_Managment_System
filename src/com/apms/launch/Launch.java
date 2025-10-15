package com.apms.launch;

import java.sql.Connection;
import com.apms.dal.DBConnection;
import com.apms.dal.TokenDAO;
import com.apms.dto.TokenDTO;
import com.apms.dal.LemmaDAO;
import com.apms.dto.LemmaDTO;
import com.apms.dal.RootDAO;
import com.apms.dto.RootDTO;

public class Launch {

    public static void main(String[] args) {
        try {
            // Initialize database connection
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            // Create DAO instances
            TokenDAO tokenDAO = new TokenDAO(connection);
            LemmaDAO lemmaDAO = new LemmaDAO(connection);
            RootDAO rootDAO = new RootDAO(connection);

            // Test Token DAO
            boolean tokenAdded = tokenDAO.addToken("كتاب", 1, 1);
            System.out.println("Token added: " + tokenAdded);
            System.out.println("All tokens:");
            for (TokenDTO t : tokenDAO.getAllTokens()) {
                System.out.println("ID: " + t.getTokenId() + ", Surface: " + t.getSurfaceForm() +
                        ", Sentence ID: " + t.getSentenceId() + ", Position: " + t.getPosition());
            }
            TokenDTO fetchedToken = tokenDAO.getTokenById(1); // Assuming ID 1 is generated
            if (fetchedToken != null) {
                fetchedToken.setSurfaceForm("كتب");
                boolean tokenUpdated = tokenDAO.updateToken(fetchedToken.getTokenId(), fetchedToken.getSurfaceForm(),
                        fetchedToken.getSentenceId(), fetchedToken.getPosition());
                System.out.println("Token updated: " + tokenUpdated);
            }
            boolean tokenDeleted = tokenDAO.deleteToken(1);
            System.out.println("Token deleted: " + tokenDeleted);

            // Test Lemma DAO
            boolean lemmaAdded = lemmaDAO.addLemma("كتب");
            System.out.println("Lemma added: " + lemmaAdded);
            System.out.println("All lemmas:");
            for (LemmaDTO l : lemmaDAO.getAllLemmas()) {
                System.out.println("ID: " + l.getLemmaId() + ", Form: " + l.getLemmaForm());
            }
            LemmaDTO fetchedLemma = lemmaDAO.getLemmaById(1); // Assuming ID 1 is generated
            if (fetchedLemma != null) {
                fetchedLemma.setLemmaForm("كاتب");
                boolean lemmaUpdated = lemmaDAO.updateLemma(fetchedLemma.getLemmaId(), fetchedLemma.getLemmaForm());
                System.out.println("Lemma updated: " + lemmaUpdated);
            }
            boolean lemmaDeleted = lemmaDAO.deleteLemma(1);
            System.out.println("Lemma deleted: " + lemmaDeleted);

            // Test Root DAO
            boolean rootAdded = rootDAO.addRoot("كتب");
            System.out.println("Root added: " + rootAdded);
            System.out.println("All roots:");
            for (RootDTO r : rootDAO.getAllRoots()) {
                System.out.println("ID: " + r.getRootId() + ", Form: " + r.getRootForm());
            }
            RootDTO fetchedRoot = rootDAO.getRootById(1); // Assuming ID 1 is generated
            if (fetchedRoot != null) {
                fetchedRoot.setRootForm("كتب2");
                boolean rootUpdated = rootDAO.updateRoot(fetchedRoot.getRootId(), fetchedRoot.getRootForm());
                System.out.println("Root updated: " + rootUpdated);
            }
            boolean rootDeleted = rootDAO.deleteRoot(1);
            System.out.println("Root deleted: " + rootDeleted);

            // Close connection
            dbConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}