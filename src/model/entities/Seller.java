package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Seller implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;

    private Department department;

    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");



    public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public Seller() {
    }



    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public Date getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }



    public Double getBaseSalary() {
        return baseSalary;
    }


    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }



    public Department getDepartment() {
        return department;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Seller other = (Seller) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Id: " +id + " | Nome: " + name + " |  Email: " + email + " |  Data de nascimento: " + sd.format(birthDate) + " |  Salário: "
                + baseSalary + " |  Departamento: " + department;
    }
}
