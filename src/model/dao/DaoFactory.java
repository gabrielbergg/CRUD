package model.dao;

import connection.Conect;
import model.dao.implments.DepartmentDaoJdbc;
import model.dao.implments.SellerDaoJdbc;

public class DaoFactory {
    
    public static SellerDao createSellerDao() {
        return new SellerDaoJdbc(Conect.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJdbc(Conect.getConnection());
    }

}
