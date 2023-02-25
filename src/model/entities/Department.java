package model.entities;
import java.io.Serializable;


public class Department implements Serializable{
    private Integer idDep;
    private String name;


    public Department(Integer idDep, String name) {
        this.idDep = idDep;
        this.name = name;
    }

    public Department() {
    }


    public Integer getidDep() {
        return idDep;
    }


    public void setidDep(Integer idDep) {
        this.idDep = idDep;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDep == null) ? 0 : idDep.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        if (idDep == null) {
            if (other.idDep != null)
                return false;
        } else if (!idDep.equals(other.idDep))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return this.idDep + " - " + this.name;
    }
    
    
}
