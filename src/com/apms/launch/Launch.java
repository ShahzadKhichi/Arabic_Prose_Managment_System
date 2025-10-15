package com.apms.launch;

import java.sql.Connection;
import com.apms.dal.*;
import com.apms.dto.*;

public class Launch {

    public static void main(String[] args) {
        try {
            // Initialize database connection
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            // Create DAO instances
            AuthorDAO authorDAO = new AuthorDAO(connection);
            BookDAO bookDAO = new BookDAO(connection);
            ProseDAO proseDAO = new ProseDAO(connection);
            SentenceDAO sentenceDAO = new SentenceDAO(connection);
            TokenDAO tokenDAO = new TokenDAO(connection);
            LemmaDAO lemmaDAO = new LemmaDAO(connection);
            RootDAO rootDAO = new RootDAO(connection);

            // Test Author DAO
            AuthorDTO author = new AuthorDTO(0, "محمد بن إسحاق", "مؤرخ وكاتب عربي شهير");
            boolean authorCreated = authorDAO.create(author);
            System.out.println("Author created: " + authorCreated + ", ID: " + author.getAuthorId());
            System.out.println("All authors:");
            for (AuthorDTO a : authorDAO.readAll()) {
                System.out.println("ID: " + a.getAuthorId() + ", Name: " + a.getName() + ", Bio: " + a.getBiography());
            }

            // Test Book DAO
            BookDTO book = new BookDTO(0, "",author.getAuthorId(), "السيرة النبوية", "العصر الأموي");
            boolean bookCreated = bookDAO.create(book);
            System.out.println("Book created: " + bookCreated + ", ID: " + book.getBookId());
            System.out.println("All books:");
            for (BookDTO b : bookDAO.readAll()) {
                System.out.println("ID: " + b.getBookId() + ", Author ID: " + b.getAuthorId() +
                        ", Title: " + b.getTitle() + ", Era: " + b.getEra());
            }

            // Test Prose DAO
            ProseDTO prose = new ProseDTO(0, book.getBookId(), "مقدمة السيرة", "مقدمة تحكي عن بداية السيرة النبوية");
            boolean proseCreated = proseDAO.insertProse(prose);
            System.out.println("Prose created: " + proseCreated + ", ID: " + prose.getProseId());
            System.out.println("All prose:");
            for (ProseDTO p : proseDAO.getAllProse()) {
                System.out.println("ID: " + p.getProseId() + ", Book ID: " + p.getBookId() +
                        ", Title: " + p.getTitle() + ", Description: " + p.getDescription());
            }

            // Test Sentence DAO
            SentenceDTO sentence = new SentenceDTO(0, prose.getProseId(), 1, "كان النبي محمد صلى الله عليه وسلم يعيش في مكة",
                    "كَانَ النَّبِيُّ مُحَمَّدٌ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ يَعِيشُ فِي مَكَّةَ",
                    "The Prophet Muhammad lived in Mecca", "تاريخي");
            boolean sentenceCreated = sentenceDAO.insertSentence(sentence);
            System.out.println("Sentence created: " + sentenceCreated + ", ID: " + sentence.getSentenceId());
            System.out.println("All sentences:");
            for (SentenceDTO s : sentenceDAO.getAllSentences()) {
                System.out.println("ID: " + s.getSentenceId() + ", Prose ID: " + s.getProseId() +
                        ", Text: " + s.getText() + ", Translation: " + s.getTranslation());
            }

            // Test Token DAO
            boolean tokenCreated = tokenDAO.addToken("كان", sentence.getSentenceId(), 1);
            System.out.println("Token created: " + tokenCreated);
            System.out.println("All tokens:");
            for (TokenDTO t : tokenDAO.getAllTokens()) {
                System.out.println("ID: " + t.getTokenId() + ", Surface: " + t.getSurfaceForm() +
                        ", Sentence ID: " + t.getSentenceId() + ", Position: " + t.getPosition());
            }

            // Test Lemma DAO
            boolean lemmaCreated = lemmaDAO.addLemma("كَانَ");
            System.out.println("Lemma created: " + lemmaCreated);
            System.out.println("All lemmas:");
            for (LemmaDTO l : lemmaDAO.getAllLemmas()) {
                System.out.println("ID: " + l.getLemmaId() + ", Form: " + l.getLemmaForm());
            }

            // Test Root DAO
            boolean rootCreated = rootDAO.addRoot("كون");
            System.out.println("Root created: " + rootCreated);
            System.out.println("All roots:");
            for (RootDTO r : rootDAO.getAllRoots()) {
                System.out.println("ID: " + r.getRootId() + ", Form: " + r.getRootForm());
            }

            // Clean up: Delete in reverse dependency order
            boolean tokenDeleted = tokenDAO.deleteToken(1);
            System.out.println("Token deleted: " + tokenDeleted);
            boolean sentenceDeleted = sentenceDAO.deleteSentence(sentence.getSentenceId());
            System.out.println("Sentence deleted: " + sentenceDeleted);
            boolean proseDeleted = proseDAO.deleteProse(prose.getProseId());
            System.out.println("Prose deleted: " + proseDeleted);
            boolean bookDeleted = bookDAO.delete(book.getBookId());
            System.out.println("Book deleted: " + bookDeleted);
            boolean authorDeleted = authorDAO.delete(author.getAuthorId());
            System.out.println("Author deleted: " + authorDeleted);

            // Close connection
            dbConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}