package com.spoontrack.core.database;

import com.spoontrack.core.entity.Pub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseFunctions {
    private static final Logger log = LoggerFactory.getLogger(DatabaseFunctions.class);

    private static boolean exists(Pub pub) {
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Pubs` WHERE `pubName` = ? AND `pubAddress` = ?")) {

            stmt.setString(1, pub.getName());
            stmt.setString(2, pub.getAddress());
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch(Exception ex) {
            log.error("An error occurred while running the {} class, message: {}", DatabaseFunctions.class.getSimpleName(), ex.getMessage(), ex);
        }

        return true;
    }

    public static void addPub(Pub pub) {
        if(exists(pub)) {
            return;
        }

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `Pubs` (`pubName`, `pubAddress`, `pubRegion`, `pubCountry`, `pubLink`) VALUES (?, ?, ?, ? ,?)")) {

            stmt.setString(1, pub.getName());
            stmt.setString(2, pub.getAddress());
            stmt.setString(3, pub.getRegion());
            stmt.setString(4, pub.getCountry());
            stmt.setString(5, pub.getLink());
            stmt.execute();

            log.info("Pub added: " + pub.getName() + " (" + pub.getCountry() + ", " + pub.getRegion() + ")");

        } catch(Exception ex) {
            log.error("An error occurred while running the {} class, message: {}", DatabaseFunctions.class.getSimpleName(), ex.getMessage(), ex);
        }
    }
}
