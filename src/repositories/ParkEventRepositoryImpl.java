package repositories;

import models.DB;
import models.enums.ParkEvent;
import repositories.interfaces.IParkEventRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkEventRepositoryImpl implements IParkEventRepository {

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public ParkEvent getParkEventById(int id) {

        ParkEvent parkEvent = null;
        String getParkEventQuery = "SELECT * FROM ParkEvent WHERE event_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getParkEventByIdSt = conn.prepareStatement(getParkEventQuery)) {

            getParkEventByIdSt.setInt(1, id);
            try (ResultSet rs = getParkEventByIdSt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    parkEvent = ParkEvent.valueOf(name.toUpperCase());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return parkEvent;
    }

    @Override
    public List<ParkEvent> getParkEvents() {

        List<ParkEvent> parkEvents = new ArrayList<>();
        String getParkEventsQuery = "SELECT * FROM ParkEvent";

        try (Connection conn = getConnection();
             PreparedStatement getParkEventsSt = conn.prepareStatement(getParkEventsQuery);
             ResultSet rs = getParkEventsSt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                parkEvents.add(ParkEvent.valueOf(name.toUpperCase()));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return parkEvents;
    }
}
