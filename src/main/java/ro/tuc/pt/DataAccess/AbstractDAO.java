package ro.tuc.pt.DataAccess;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.*;
import ro.tuc.pt.Connection.ConnectionFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generic class that manages the data from the Database.
 * It also generates queries and implements the CRUD methods for the tables.
 * @param <T> can be replaced with either Client, Product or Order.
 */

public abstract class AbstractDAO <T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Method that creates the select all query for the tables in the warehouse database.
     * @return the query as a string.
     */
    private String createSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }


    private String createInsertQuery(ArrayList<String> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        sb.append("(");
        String prefix = "";
        for(Field field : type.getDeclaredFields()){
            sb.append(prefix);
            prefix = ",";
            sb.append(field.getName());
        }
        sb.append( ") ");
        sb.append( "VALUES ");
        sb.append( "(");
        prefix = "";
        for(String ignored :values) {
            sb.append(prefix);
            prefix = ",";
            sb.append("?");
        }
        sb.append(")");
        return sb.toString();
    }


    private String createUpdateQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        String prefix = "";
        for(Field fields : type.getDeclaredFields()){
            sb.append(prefix);
            prefix = ",";
            sb.append(fields.getName()).append("=?");
        }
        sb.append(" WHERE ").append(field).append("=?");
        return sb.toString();
    }


    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append("=?");
        return sb.toString();
    }

    public T findById(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(!resultSet.isBeforeFirst())
                return null;
            else
                return createObjects(resultSet).get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:findById "+ e.getMessage());
        }
        finally {
            close(resultSet, statement, connection);
        }
        return null;
    }


    /**
     * Method that uses the select all query to extract all the data from a table.
     * @return the data if it is successfully extracted, null otherwise.
     */
    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAll();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            resultSet = statement.executeQuery();

            if(!resultSet.isBeforeFirst())
                return null;
            else
                return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:findAll "+ e.getMessage());
        }
        finally {
            close(resultSet, statement, connection);
        }
        return null;
    }

    public boolean insert(ArrayList<String> values){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createInsertQuery(values);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(String value : values) {
                statement.setString(i, value);
                i++;
            }
            System.out.println(statement);
            resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }



    public boolean update(ArrayList<String> values){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createUpdateQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(String value : values) {
                statement.setString(i, value);
                i++;
            }
            statement.setString(i, values.get(0));
            System.out.println(statement);
            resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }


    public boolean delete(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createDeleteQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:delete "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Method that creates a query that extracts the maximum id rom a table.
     * @return string of the last id.
     */
    private String createLastID() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT max(id) FROM ");
        sb.append("warehouse.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Method that generates a new ID for the new item inserted in the table
     * by extracting the highest id in the table and adding 1 to it.
     * @return the new Id.
     * @throws SQLException .
     */
    public int getLastID() throws SQLException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        String query = createLastID();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            int lastID = 0;
            while (resultSet.next()) {
                lastID = resultSet.getInt(1);
            }

        return lastID + 1;

    }


    private void close(ResultSet resultSet, Statement statement, Connection connection) {
        ConnectionFactory.close(resultSet);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    private void closeUpdate(Statement statement, Connection connection) {
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

}
