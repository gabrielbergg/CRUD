package model.dao.implments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conect;
import connection.DataException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "insert into Department values "
                + "(?, ?)");

            st.setNull(1, 0);
            st.setString(2, obj.getName());

            int rows = st.executeUpdate();

            if (rows <= 0) {
                throw new DataException("Erro inersperado.");
            }
        }
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeStatement(st);
        }
    }


    @Override
    public void update(Department obj) {
       PreparedStatement st = null;

       try {
            st = conn.prepareStatement(
                "update Department "
                + "set Name = ? "
                + "where Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getidDep());

            st.executeUpdate();
       } 
       catch (Exception e) {
            throw new DataException(e.getMessage());
       }
       finally {
            Conect.closeStatement(st);
       }
    }


    @Override
    public void delete(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "delete from Department "
                + "where Id = ?");

            st.setInt(1, id);
            st.executeUpdate();
        } 
        catch (Exception e) {
            throw new DataException(e.getMessage());
       }
       finally {
            Conect.closeStatement(st);
       }
    }


    @Override
    public Department find(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;


       try {
            st = conn.prepareStatement(
                "select * from Department "
                + "where Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                Department department = instantDep(rs);
                return department;
            }
            return null;


        }
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeResultSet(rs);
            Conect.closeStatement(st);
        }

    }


    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "select * from Department "
                + "order by name");

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department dep = instantDep(rs);
                list.add(dep);
            }

            return list;
        } 
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeResultSet(rs);
            Conect.closeStatement(st);
        }
    }



    public Department instantDep(ResultSet rs) throws SQLException {
        Department dep = new Department();
            dep.setidDep(rs.getInt("Id"));
            dep.setName(rs.getString("Name"));

            return dep;
    }

    
}
