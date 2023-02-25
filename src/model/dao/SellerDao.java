package model.dao;
import java.util.List;
import model.entities.Department;
import model.entities.Seller;


public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void delete(Integer id);
    Seller find(Integer id);
    List<Seller> findAll();
    List<Seller> findDepartment(Department dep);
}
