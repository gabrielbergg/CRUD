package model.dao.implments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import connection.Conect;
import connection.DataException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;



public class SellerDaoJdbc implements SellerDao {

    private Connection conn;

    public SellerDaoJdbc(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller obj) {
        
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "insert into seller values "
                +"(?, ?, ?, ?, ?, ?)", 
                Statement.RETURN_GENERATED_KEYS);


            st.setNull(1, 0);
            st.setString(2, obj.getName());
            st.setString(3, obj.getEmail());
            st.setDate(4,  new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(5, obj.getBaseSalary());
            st.setInt(6, obj.getDepartment().getidDep());

            int r = st.executeUpdate();

            if (r > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }
            else {
                throw new DataException("Erro inesperado!");
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
    public void update(Seller obj) {
        
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "update seller "
                + "set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                + "where Id = ?");


            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3,  new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getidDep());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        }
        catch (SQLException e) {
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
                "delete from seller "
                + "where Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } 
        catch (SQLException e) {
            throw new DataException("Erro. " +e.getMessage());
        }
        finally {
            Conect.closeStatement(st);
        }
        
    }


    @Override
    public Seller find(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "select seller.*, department.name as dp "
                + "from seller inner join department "
                + "on seller.DepartmentId = Department.Id "
                + "where seller.Id = ?");


            st.setInt(1, id);
            rs = st.executeQuery();


            if(rs.next()) {
                Department department = instantDep(rs);
                Seller seller = instantSeller(rs, department);
                return seller;

            }
            
            return null;
        } 
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeStatement(st);
            Conect.closeResultSet(rs);
        }
    


    }



    private Seller instantSeller(ResultSet rs, Department department) throws SQLException {
        Seller sell = new Seller();
        sell.setId(rs.getInt("Id"));
        sell.setName(rs.getString("Name"));
        sell.setEmail(rs.getString("Email"));
        sell.setBirthDate(rs.getDate("BirthDate"));
        sell.setBaseSalary(rs.getDouble("BaseSalary"));
        sell.setDepartment(department);

        return sell;
    }


    private Department instantDep(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setidDep(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("dp"));
        return dep;
    }


    @Override
    public List<Seller> findAll() {
        
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "select seller.*, department.Name as dp "
                + "from seller inner join department "
                + "on seller.DepartmentId = department.Id "
                + "order by Name");
                
            
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department department = map.get(rs.getInt("DepartmentId"));

                if(department == null) {
                    department = instantDep(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }

                Seller seller = instantSeller(rs, department);
                list.add(seller);
                

            }
            
            return list;




        } 
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeStatement(st);
            Conect.closeResultSet(rs);
        }
    }


    @Override
    public List<Seller> findDepartment(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "select seller.*, department.Name as dp "
                + "from seller inner join department "
                + "on seller.DepartmentId = department.Id "
                + "where DepartmentId = ? "
                + "order by Name");
                
            
            st.setInt(1, dep.getidDep());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department department = map.get(rs.getInt("DepartmentId"));

                if(department == null) {
                    department = instantDep(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }

                Seller seller = instantSeller(rs, department);
                list.add(seller);
                

            }
            
            return list;




        } 
        catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        finally {
            Conect.closeStatement(st);
            Conect.closeResultSet(rs);
        }


    }
    
}
