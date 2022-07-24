package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.User;
import models.exception.DatabaseException;
import models.security.Security;
import models.util.DatabaseHelper;
import models.util.Report;

public class UsersDAO {

    DatabaseHelper db;

    public UsersDAO() {
        db = new DatabaseHelper();
    }

    public UsersDAO(Connection con) {
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }

    public int login(User u) throws DatabaseException {
        int auth = -1;
        String username = u.getUsername().trim();
        String password = u.getPassword().trim();
        String dbUname;
        String dbPword;
        String userType;
        String lastname;
        String firstname;

        try {
            //Retrieves the data from database
            String qString = "SELECT ID, USERNAME, PASSWORD, USERTYPE, FIRSTNAME, LASTNAME FROM USERS WHERE USERNAME = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, username);
            ResultSet records = ps.executeQuery();

            //Parses the ResultSet object and checks for record with that username and password
            while (records.next()) {
                //Get result's username and password
                dbUname = records.getString("USERNAME");

                //If there is no returned result, break from while loop, set int as -1
                if (dbUname == null) {
                    auth = -1;
                    break;
                }

                dbPword = records.getString("PASSWORD");
                userType = records.getString("USERTYPE");
                lastname = records.getString("LASTNAME");
                firstname = records.getString("FIRSTNAME");

                //Decrypt password from database
                dbPword = Security.decrypt(dbPword);

                //If both username and password are correct, return 1
                if (username.equals(dbUname) && password.equals(dbPword)) {
                    u.setUserType(userType.trim());
                    u.setFirstName(firstname.trim());
                    u.setLastName(lastname.trim());
                    u.setId(records.getInt("ID"));
                    auth = 1;

                    //If only username is correct, return 0
                } else if (username.equals(dbUname) && !password.equals(dbPword)) {
                    auth = 0;

                    //Else, return -1 for unsuccessful login
                } else {
                    auth = -1;
                }
            }

            records.close();
            ps.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return auth;
    }

    public int signup(User u) throws DatabaseException {
        int success = -1;
        String username = u.getUsername().trim();
        String password = u.getPassword().trim();
        String lastname = u.getLastName().trim();
        String firstname = u.getFirstName().trim();
        int idNO;

        try {
            //Retrieves the data from database
            String qString = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
            String qString2 = "INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, USERTYPE) VALUES (?, ?, ?, ?, 'guest')";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            System.out.println(username);
            ps.setString(1, username);
            ResultSet records = ps.executeQuery();

            //Check if username currently exists in the database
            while (records.next()) {
                //Get result's username
                String uname = records.getString("USERNAME");

                //Check if username exists
                if (uname != null) {
                    success = -1;
                    throw new DatabaseException("Username already taken.");
                }
            }

            //Add data to database
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setString(1, firstname);
            ps2.setString(2, lastname);
            ps2.setString(3, username);
            ps2.setString(4, Security.encrypt(password));
            ps2.execute();

            success = 1;

            records.close();
            ps.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public int addUser(User u) throws DatabaseException {
        int success = -1;
        try {
            //Retrieves the data from database
            String qString = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
            String qString2 = "INSERT INTO USERS (FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, USERTYPE, USERNAME, PASSWORD, ZIP_CODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, u.getUsername().trim());
            ResultSet records = ps.executeQuery();

            //Check if username currently exists in the database
            while (records.next()) {
                //Get result's username
                String uname = records.getString("USERNAME");

                //Check if username exists
                if (uname != null) {
                    success = -1;
                    throw new DatabaseException("Username already taken.");
                }
            }

            //Add data to database
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setString(1, u.getFirstName().trim());
            ps2.setString(2, u.getLastName().trim());
            ps2.setDate(3, u.getBirthday());
            ps2.setString(4, u.getMobile().trim());
            ps2.setString(5, u.getEmail().trim());
            ps2.setString(6, u.getAddress().trim());
            ps2.setString(7, u.getCity().trim());
            ps2.setString(8, u.getCountry().trim());
            ps2.setString(9, u.getUserType().trim());
            ps2.setString(10, u.getUsername().trim());
            ps2.setString(11, Security.encrypt(u.getPassword().trim()));
            ps2.setString(12, u.getZip_code());
            ps2.execute();
            success = 1;

            records.close();
            ps.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public int editUser(User u) throws DatabaseException {
        int success = -1;
        try {
            String qString = "UPDATE USERS SET FIRSTNAME=?, LASTNAME=?, BIRTHDAY=?, MOBILE=?, EMAIL=?, ADDRESS=?, CITY=?, COUNTRY=?, USERNAME=?, PASSWORD=?, USERTYPE=?, ZIP_CODE=? WHERE ID=?";
            //Add data to database
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setString(1, u.getFirstName().trim());
            ps2.setString(2, u.getLastName().trim());
            ps2.setDate(3, u.getBirthday());
            ps2.setString(4, u.getMobile().trim());
            ps2.setString(5, u.getEmail().trim());
            ps2.setString(6, u.getAddress().trim());
            ps2.setString(7, u.getCity().trim());
            ps2.setString(8, u.getCountry().trim());
            ps2.setString(9, u.getUsername().trim());
            ps2.setString(10, Security.encrypt(u.getPassword().trim()));
            ps2.setString(11, u.getUserType());
            ps2.setString(12, u.getZip_code());
            ps2.setInt(13, u.getId());
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public int changeUserAddress(User u) throws DatabaseException {
        int success = -1;
        System.out.println("CHANGE ADDRESS");
        try {
            String qString = "UPDATE USERS SET MOBILE=?, EMAIL=?, ADDRESS=?, CITY=?, COUNTRY=?, ZIP_CODE=? WHERE ID=?";
            //Add data to database
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setString(1, u.getMobile().trim());
            ps2.setString(2, u.getEmail().trim());
            ps2.setString(3, u.getAddress().trim());
            ps2.setString(4, u.getCity().trim());
            ps2.setString(5, u.getCountry().trim());
            ps2.setString(6, u.getZip_code().trim());
            ps2.setInt(7, u.getId());
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public int deleteUser(User u) throws DatabaseException {
        int success = -1;
        try {
            String qString = "DELETE FROM USERS WHERE ID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, u.getId());
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public List<User> getAdminUserReportData() throws DatabaseException {
        List<User> list = new ArrayList<User>();
        try {
            String qString = "SELECT FIRSTNAME, LASTNAME, BIRTHDAY, EMAIL, USERNAME FROM USERS WHERE USERTYPE='guest'";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getDate("BIRTHDAY"), rs.getString("EMAIL"), rs.getString("USERNAME")));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public List<User> getAdminEmployeeListData() throws DatabaseException {
        List<User> list = new ArrayList<User>();
        try {
            String qString = "SELECT FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, USERNAME, USERTYPE FROM USERS WHERE USERTYPE='employee' OR USERTYPE='admin'";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getDate("BIRTHDAY"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("USERNAME"), rs.getString("USERTYPE")));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public List<User> getUserList() throws DatabaseException {
        List<User> list = new ArrayList<User>();
        try {
            String qString = "SELECT ID, FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, ZIP_CODE, USERNAME, USERTYPE, REGISTEREDAT FROM USERS";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new User(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getDate("BIRTHDAY"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"), rs.getString("USERNAME"), rs.getString("USERTYPE"), rs.getDate("REGISTEREDAT")));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public User getUser(int id) throws DatabaseException, NullPointerException {
        User user = null;
        try {
            String qString = "SELECT ID, FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, ZIP_CODE, USERNAME, USERTYPE, REGISTEREDAT, PASSWORD FROM USERS WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getDate("BIRTHDAY"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"), rs.getString("USERNAME"), rs.getString("USERTYPE"), rs.getDate("REGISTEREDAT"));
                user.setPassword(Security.decrypt(rs.getString("PASSWORD")));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } catch( NullPointerException e){
            throw e;
        }
        return user;
    }

    public int changePassword(User u) throws DatabaseException {
        int success = -1;
        try {
            String qString = "UPDATE USERS SET PASSWORD = ? WHERE ID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setString(1, Security.encrypt(u.getPassword().trim()));
            ps2.setInt(2, u.getId());
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

}
