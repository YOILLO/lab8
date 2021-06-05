package database_managers;

import data.*;
import main.Main;
import messages.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

public class DatabaseCollectionManager {
    // STUDY_GROUP_TABLE
    private final String SELECT_ALL_FLATS = "SELECT * FROM " + DatabaseManager.FLAT_TABLE;
    private final String SELECT_FLAT_BY_ID = SELECT_ALL_FLATS + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_FLAT = "INSERT INTO " +
            DatabaseManager.FLAT_TABLE + " (" +
            DatabaseManager.FLAT_TABLE_NAME_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_COORDINATES_ID + ", " +
            DatabaseManager.FLAT_TABLE_CREATION_DATE_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_AREA_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_NUMBERS_OF_ROOMS_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_PRICE_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_FURNISH_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_TRANSPORT_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_HOUSE_ID_COLUMN + ", " +
            DatabaseManager.FLAT_TABLE_USER_ID_COLUMN +
            ") VALUES (?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?)";
    private final String DELETE_FLAT_BY_ID = "DELETE FROM " + DatabaseManager.FLAT_TABLE +
            " WHERE " + DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_NAME_BY_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_AREA_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_AREA_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_NUMBER_OF_ROOMS_BY_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_NUMBERS_OF_ROOMS_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_PRICE_BY_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_PRICE_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_FURNITURE_BY_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_FURNISH_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_FLAT_TRANSPORT_BY_ID = "UPDATE " + DatabaseManager.FLAT_TABLE + " SET " +
            DatabaseManager.FLAT_TABLE_TRANSPORT_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.FLAT_TABLE_ID_COLUMN + " = ?";
    // COORDINATES_TABLE
    private final String SELECT_ALL_COORDINATES = "SELECT * FROM " + DatabaseManager.COORDINATES_TABLE;
    private final String SELECT_COORDINATES_BY_ID = SELECT_ALL_COORDINATES +
            " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_COORDINATES = "INSERT INTO " +
            DatabaseManager.COORDINATES_TABLE + " (" +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + ", " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + ") VALUES (?, ?)";
    private final String UPDATE_COORDINATES_BY_ID = "UPDATE " + DatabaseManager.COORDINATES_TABLE + " SET " +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + " = ?, " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_COORDINATES_BY_ID = "DELETE FROM " + DatabaseManager.COORDINATES_TABLE +
            " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    // PERSON_TABLE
    private final String SELECT_ALL_HOUSE = "SELECT * FROM " + DatabaseManager.HOUSE_TABLE;
    private final String SELECT_HOUSE_BY_ID = SELECT_ALL_HOUSE +
            " WHERE " + DatabaseManager.HOUSE_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_HOUSE = "INSERT INTO " +
            DatabaseManager.HOUSE_TABLE + " (" +
            DatabaseManager.HOUSE_TABLE_NAME_COLUMN + ", " +
            DatabaseManager.HOUSE_TABLE_YEAR_COLUMN + ", " +
            DatabaseManager.HOUSE_TABLE_NUMBER_OF_FLOORS_COLUMN + ") VALUES (?, ?, ?)";
    private final String UPDATE_TABLE_BY_ID = "UPDATE " + DatabaseManager.HOUSE_TABLE + " SET " +
            DatabaseManager.HOUSE_TABLE_NAME_COLUMN + " = ?, " +
            DatabaseManager.HOUSE_TABLE_YEAR_COLUMN + " = ?, " +
            DatabaseManager.HOUSE_TABLE_NUMBER_OF_FLOORS_COLUMN + " WHERE " +
            DatabaseManager.HOUSE_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_TABLE_BY_ID = "DELETE FROM " + DatabaseManager.HOUSE_TABLE +
            " WHERE " + DatabaseManager.HOUSE_TABLE_ID_COLUMN + " = ?";
    private DatabaseManager databaseManager;
    private DatabaseUserManager databaseUserManager;

    public DatabaseCollectionManager(DatabaseManager manager, DatabaseUserManager userManager) {
        databaseManager = manager;
        databaseUserManager = userManager;
    }

    public Flat insertFlat(RowFlat flatRaw, User user){
        Flat stGroup;
        PreparedStatement preparedInsertFlatStatement = null;
        PreparedStatement preparedInsertCoordinatesStatement = null;
        PreparedStatement preparedInsertHouseStatement = null;
        try {
            databaseManager.setCommitMode();
            databaseManager.setSavepoint();

            LocalDateTime creationTime = LocalDateTime.now();

            preparedInsertFlatStatement = databaseManager.getPreparedStatement(INSERT_FLAT, true);
            preparedInsertCoordinatesStatement = databaseManager.getPreparedStatement(INSERT_COORDINATES, true);
            preparedInsertHouseStatement = databaseManager.getPreparedStatement(INSERT_HOUSE, true);

            preparedInsertHouseStatement.setString(1, flatRaw.getHouse().getName());
            preparedInsertHouseStatement.setLong(2, flatRaw.getHouse().getYear());
            preparedInsertHouseStatement.setLong(3, flatRaw.getHouse().getNumberOfFloors());
            if (preparedInsertHouseStatement.executeUpdate() == 0)
                throw new SQLException();

            ResultSet generatedPersonKeys = preparedInsertHouseStatement.getGeneratedKeys();
            long userId;
            if (generatedPersonKeys.next()) {
                userId = generatedPersonKeys.getLong(1);
            } else throw new SQLException();
            Main.logger.info("Выполнен запрос INSERT_HOUSE.");


            preparedInsertCoordinatesStatement.setFloat(1, flatRaw.getCoordinates().getX());
            preparedInsertCoordinatesStatement.setFloat(2, flatRaw.getCoordinates().getY());

            if (preparedInsertCoordinatesStatement.executeUpdate() == 0)
                throw new SQLException();

            ResultSet generatedCoordinatesKeys = preparedInsertCoordinatesStatement.getGeneratedKeys();
            long coordinatesId = 0;
            if (generatedCoordinatesKeys.next()){
                coordinatesId = generatedCoordinatesKeys.getLong(1);
                //Main.logger.info("Get coordinates key: " + coordinatesId);
            }
            Main.logger.info("Выполнен запрос INSERT_COORDINATES.");


            preparedInsertFlatStatement.setString(1, flatRaw.getName());
            preparedInsertFlatStatement.setLong(2, coordinatesId);
            preparedInsertFlatStatement.setTimestamp(3, Timestamp.valueOf(creationTime));
            preparedInsertFlatStatement.setInt(4, flatRaw.getArea());
            preparedInsertFlatStatement.setInt(5, flatRaw.getNumberOfRooms());
            preparedInsertFlatStatement.setFloat(6, flatRaw.getPrice());
            preparedInsertFlatStatement.setString(7, flatRaw.getFurnish().toString());
            preparedInsertFlatStatement.setString(8, flatRaw.getTransport().toString());
            preparedInsertFlatStatement.setLong(9, userId);
            preparedInsertFlatStatement.setLong(10, databaseUserManager.getUserIdByUsername(user));
            if (preparedInsertFlatStatement.executeUpdate() == 0)
                throw new SQLException();
            ResultSet generatedSGKeys = preparedInsertFlatStatement.getGeneratedKeys();
            int flatId;
            if (generatedSGKeys.next()) {
                flatId = generatedSGKeys.getInt(1);
            } else throw new SQLException();
            Main.logger.info("Выполнен запрос INSERT_FLAT.");

            stGroup = new Flat(
                    flatRaw,
                    flatId,
                    user);

            databaseManager.commit();
            return stGroup;
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении группы запросов на добавление нового объекта!");
            databaseManager.rollback();
            return null;
        } finally {
            databaseManager.closePreparedStatement(preparedInsertHouseStatement);
            databaseManager.closePreparedStatement(preparedInsertCoordinatesStatement);
            databaseManager.closePreparedStatement(preparedInsertFlatStatement);
            databaseManager.setNormalMode();
        }
    }

    public void updateFloatById(long flatId, RowFlat flatRaw) {
        PreparedStatement updateNameByIdStatement = null;
        PreparedStatement updateFlatByIdStatement = null;
        PreparedStatement updateNumberOfRoomsByIdStatement = null;
        PreparedStatement updatePriceByIdStatement = null;
        PreparedStatement updateFurnishByIdStatement = null;
        PreparedStatement updateTransportByIdStatement = null;
        PreparedStatement updateCoordinatesByIdStatement = null;
        PreparedStatement updateHouseByIdStatement = null;
        try {
            databaseManager.setCommitMode();
            databaseManager.setSavepoint();

            updateNameByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_NAME_BY_ID, false);
            updateFlatByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_AREA_ID, false);
            updateNumberOfRoomsByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_NUMBER_OF_ROOMS_BY_ID, false);
            updatePriceByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_PRICE_BY_ID, false);
            updateFurnishByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_FURNITURE_BY_ID, false);
            updateTransportByIdStatement = databaseManager.getPreparedStatement(UPDATE_FLAT_TRANSPORT_BY_ID, false);
            updateCoordinatesByIdStatement = databaseManager.getPreparedStatement(UPDATE_COORDINATES_BY_ID, false);
            updateHouseByIdStatement = databaseManager.getPreparedStatement(UPDATE_TABLE_BY_ID, false);

            if (flatRaw.getName() != null) {
                updateNameByIdStatement.setString(1, flatRaw.getName());
                updateNameByIdStatement.setLong(2, flatId);
                if (updateNameByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_FLAT_NAME_BY_ID.");
            }
            if (flatRaw.getCoordinates() != null) {
                updateCoordinatesByIdStatement.setFloat(1, flatRaw.getCoordinates().getX());
                updateCoordinatesByIdStatement.setFloat(2, flatRaw.getCoordinates().getY());
                updateCoordinatesByIdStatement.setLong(3, getCoordinatesIdByFloatId(flatId));
                if (updateCoordinatesByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_COORDINATES_NAME_BY_ID.");
            }
            if (flatRaw.getArea() != -1) {
                updateFlatByIdStatement.setInt(1, flatRaw.getArea());
                updateFlatByIdStatement.setLong(2, flatId);
                if (updateFlatByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_AREA_BY_ID.");
            }
            if (flatRaw.getNumberOfRooms() != -1) {
                updateNumberOfRoomsByIdStatement.setInt(1, flatRaw.getNumberOfRooms());
                updateNumberOfRoomsByIdStatement.setLong(2, flatId);
                if (updateNumberOfRoomsByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_NUMBER_OF_ROOMS.");
            }
            if (flatRaw.getPrice() != -1) {
                updatePriceByIdStatement.setFloat(1, flatRaw.getPrice());
                updatePriceByIdStatement.setLong(2, flatId);
                if (updatePriceByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_PRICE.");
            }
            if (flatRaw.getFurnish() != null) {
                updateFurnishByIdStatement.setString(1, flatRaw.getFurnish().toString());
                updateFurnishByIdStatement.setLong(2, flatId);
                if (updateFurnishByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_FURNISH.");
            }
            if (flatRaw.getTransport() != null) {
                updateTransportByIdStatement.setString(1, flatRaw.getTransport().toString());
                updateTransportByIdStatement.setLong(2, flatId);
                if (updateFurnishByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_TRANSPORT.");
            }
            if (flatRaw.getHouse() != null) {
                updateHouseByIdStatement.setString(1, flatRaw.getHouse().getName());
                updateHouseByIdStatement.setLong(2, flatRaw.getHouse().getYear());
                updateHouseByIdStatement.setLong(3, flatRaw.getHouse().getNumberOfFloors());
                updateHouseByIdStatement.setLong(4, getHouseIdByFlatId(flatId));
                if (updateHouseByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                Main.logger.info("Выполнен запрос UPDATE_HOUSE.");
            }
            databaseManager.commit();
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении группы запросов на обновление объекта!");
            databaseManager.rollback();
        } finally {
            databaseManager.closePreparedStatement(updateNameByIdStatement);
            databaseManager.closePreparedStatement(updateFlatByIdStatement);
            databaseManager.closePreparedStatement(updateNumberOfRoomsByIdStatement);
            databaseManager.closePreparedStatement(updatePriceByIdStatement);
            databaseManager.closePreparedStatement(updateFurnishByIdStatement);
            databaseManager.closePreparedStatement(updateCoordinatesByIdStatement);
            databaseManager.closePreparedStatement(updateHouseByIdStatement);
            databaseManager.setNormalMode();
        }
    }

    private long getCoordinatesIdByFloatId(long SGId){
        long CoordinatesId = 0;
        PreparedStatement preparedSelectSGByIdStatement = null;
        try {
            preparedSelectSGByIdStatement = databaseManager.getPreparedStatement(SELECT_FLAT_BY_ID, false);
            preparedSelectSGByIdStatement.setLong(1, SGId);
            ResultSet resultSet = preparedSelectSGByIdStatement.executeQuery();
            Main.logger.info("Выполнен запрос SELECT_STUDY_GROUP_BY_ID.");
            if (resultSet.next()) {
                CoordinatesId = resultSet.getLong(DatabaseManager.FLAT_TABLE_COORDINATES_ID);
            } else throw new SQLException();
            //Main.logger.info("Get ID " + CoordinatesId);
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении запроса!");
        } finally {
            databaseManager.closePreparedStatement(preparedSelectSGByIdStatement);
        }
        return CoordinatesId;
    }

    private long getHouseIdByFlatId(long sgId){
        long houseId = 0;
        PreparedStatement preparedSelectFloatByIdStatement = null;
        try {
            preparedSelectFloatByIdStatement = databaseManager.getPreparedStatement(SELECT_FLAT_BY_ID, false);
            preparedSelectFloatByIdStatement.setLong(1, sgId);
            ResultSet resultSet = preparedSelectFloatByIdStatement.executeQuery();
            Main.logger.info("Выполнен запрос SELECT_STUDY_GROUP_BY_ID.");
            if (resultSet.next()) {
                houseId = resultSet.getLong(DatabaseManager.FLAT_TABLE_HOUSE_ID_COLUMN);
            } else throw new SQLException();
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении запроса!");
        } finally {
            databaseManager.closePreparedStatement(preparedSelectFloatByIdStatement);
        }
        return houseId;
    }


    public void deleteFlatById(long sgId){
        PreparedStatement preparedDeleteFlatByIdStatement = null;
        PreparedStatement preparedDeleteHouseById = null;
        PreparedStatement preparedDeleteCoordinatesById = null;
        try {
            preparedDeleteHouseById = databaseManager.getPreparedStatement(DELETE_TABLE_BY_ID, false);
            preparedDeleteHouseById.setLong(1, getHouseIdByFlatId(sgId));
            if (preparedDeleteHouseById.executeUpdate() == 0)
                throw new SQLException();
            Main.logger.info("Выполнен запрос DELETE_PERSON_BY_ID.");

            preparedDeleteCoordinatesById = databaseManager.getPreparedStatement(DELETE_COORDINATES_BY_ID, false);
            preparedDeleteCoordinatesById.setLong(1, getCoordinatesIdByFloatId(sgId));
            if (preparedDeleteCoordinatesById.executeUpdate() == 0)
                throw new SQLException();
            Main.logger.info("Выполнен запрос DELETE_COORDINATES_BY_ID.");

            preparedDeleteFlatByIdStatement = databaseManager.getPreparedStatement(DELETE_FLAT_BY_ID, false);
            preparedDeleteFlatByIdStatement.setLong(1, sgId);
            if (preparedDeleteFlatByIdStatement.executeUpdate() == 0)
                throw new SQLException();
            Main.logger.info("Выполнен запрос DELETE_FLAT_BY_ID.");

        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении запросов!");
        } finally {
            databaseManager.closePreparedStatement(preparedDeleteFlatByIdStatement);
        }
    }

    public Vector<Flat> getCollection(){
        Vector<Flat> buffCollection = new Vector<Flat>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseManager.getPreparedStatement(SELECT_ALL_FLATS, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                buffCollection.add(createFlat(resultSet));
            }
        } catch (SQLException exception) {
            Main.logger.error("Ошибка создания коллекции");
        } finally {
            databaseManager.closePreparedStatement(preparedSelectAllStatement);
        }
        return buffCollection;
    }

    private Flat createFlat(ResultSet resultSet){
        try {
            long id = resultSet.getLong(DatabaseManager.FLAT_TABLE_ID_COLUMN);
            String name = resultSet.getString(DatabaseManager.FLAT_TABLE_NAME_COLUMN);
            LocalDate creationDate = LocalDate.from(resultSet.getTimestamp(DatabaseManager.FLAT_TABLE_CREATION_DATE_COLUMN).toLocalDateTime().toLocalDate());
            int area = resultSet.getInt(DatabaseManager.FLAT_TABLE_AREA_COLUMN);
            int numberOfRooms = resultSet.getInt(DatabaseManager.FLAT_TABLE_NUMBERS_OF_ROOMS_COLUMN);
            float price = resultSet.getFloat(DatabaseManager.FLAT_TABLE_PRICE_COLUMN);
            Furnish furnish = Furnish.valueOf(resultSet.getString(DatabaseManager.FLAT_TABLE_FURNISH_COLUMN).trim());
            Transport transport = Transport.valueOf(resultSet.getString(DatabaseManager.FLAT_TABLE_TRANSPORT_COLUMN).trim());
            Coordinates cor = getCoordinatesById(getCoordinatesIdByFloatId(id));
            House house = getHouseById(getHouseIdByFlatId(id));
            User us = databaseUserManager.getUserById(resultSet.getLong(DatabaseManager.FLAT_TABLE_USER_ID_COLUMN));
            return new Flat((int)id, name.trim(),
                    cor, creationDate, area,
                    numberOfRooms, price, furnish, transport, house, us);
        } catch (SQLException throwables) {
            Main.logger.error("Ошибка парсинга таблицы");
        }
        return null;
    }

    private Coordinates getCoordinatesById(long Id) {
        Coordinates coordinates = null;
        PreparedStatement preparedSelectCoordinatesByIdStatement = null;
        try {
            preparedSelectCoordinatesByIdStatement =
                    databaseManager.getPreparedStatement(SELECT_COORDINATES_BY_ID, false);
            preparedSelectCoordinatesByIdStatement.setLong(1, Id);
            ResultSet resultSet = preparedSelectCoordinatesByIdStatement.executeQuery();
            Main.logger.info("Выполнен запрос SELECT_COORDINATES_BY_ID.");
            if (resultSet.next()) {
                coordinates = new Coordinates(
                        resultSet.getFloat(DatabaseManager.COORDINATES_TABLE_X_COLUMN),
                        resultSet.getFloat(DatabaseManager.COORDINATES_TABLE_Y_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении запроса SELECT_COORDINATES_BY_ID!");
        } finally {
            databaseManager.closePreparedStatement(preparedSelectCoordinatesByIdStatement);
        }
        return coordinates;
    }


    private House getHouseById(long Id) {
        House house = null;
        PreparedStatement preparedSelectPersonByIdStatement = null;
        try {
            preparedSelectPersonByIdStatement =
                    databaseManager.getPreparedStatement(SELECT_HOUSE_BY_ID, false);
            preparedSelectPersonByIdStatement.setLong(1, Id);
            ResultSet resultSet = preparedSelectPersonByIdStatement.executeQuery();
            Main.logger.info("Выполнен запрос SELECT_HOUSE_BY_ID.");
            if (resultSet.next()) {
                house = new House(
                        resultSet.getString(DatabaseManager.HOUSE_TABLE_NAME_COLUMN).trim(),
                        resultSet.getLong(DatabaseManager.HOUSE_TABLE_YEAR_COLUMN),
                        resultSet.getLong(DatabaseManager.HOUSE_TABLE_NUMBER_OF_FLOORS_COLUMN));
            } else throw new SQLException();
        } catch (SQLException exception) {
            Main.logger.error("Произошла ошибка при выполнении запроса SELECT_COORDINATES_BY_ID!");
        } finally {
            databaseManager.closePreparedStatement(preparedSelectPersonByIdStatement);
        }
        return house;
    }

    public void clearCollection(User chager) {
        Vector<Flat> list = getCollection();
        for (Flat flat : list) {
            if (chager.getUsername().equals(flat.getUser().getUsername()))
                deleteFlatById(flat.getId());
        }
    }
}
