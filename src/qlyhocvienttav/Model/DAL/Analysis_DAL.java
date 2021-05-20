package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;



import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import qlyhocvienttav.Model.DTO.Analysis;

public class Analysis_DAL {
    public Analysis_DAL(){};
    ObservableList<Analysis> data = FXCollections.observableArrayList();

  
    public ObservableList<Analysis> GetRevenueByMonth(int month ){
       try {
            month=month==1?12:month;
            this.data.clear();
            String sql = String.format("select course.course_id, course.coursename,COALESCE(SUM(a.amountoffeeiscomplete), 0)\n" +
                "from course \n" +
                "left join (select * \n" +
                "            from student st join studentfee stf on st.student_id = stf.student_id\n" +
                "            where extract(month from stf.dateofcompletefee) = %s) a \n" +
                "            on  course.course_id = a.course_id\n" +
                "where SYSDATE BETWEEN course.daystart and course.dayend+1\n" +
                "group by course.course_id,course.coursename",month);
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Analysis anal = new Analysis(rs.getString(1),rs.getString(2),rs.getDouble(3));
                data.add(anal);
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
    public ObservableList<Analysis> GetRevenueByLast3Month( ){
       try {
            this.data.clear();
            String sql = "select course.course_id, course.coursename,COALESCE(SUM(a.amountoffeeiscomplete), 0)\n" +
                "from course \n" +
                "left join (select * \n" +
                "            from student st join studentfee stf on st.student_id = stf.student_id\n" +
                "            where stf.dateofcompletefee >= add_months(sysdate, -3) ) a \n" +
                "            on  course.course_id = a.course_id\n" +
                "where SYSDATE BETWEEN course.daystart and course.dayend+1\n" +
                "group by course.course_id,course.coursename";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Analysis anal = new Analysis(rs.getString(1),rs.getString(2),rs.getDouble(3));
                data.add(anal);
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
    public ObservableList<Analysis> GetAmountStudentPerMonth(){
       try {
            this.data.clear();
            String sql = "SELECT \n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 1 THEN st.student_id END) AS January,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 2 THEN st.student_id END) AS February,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 3 THEN st.student_id END) AS March,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 4 THEN st.student_id END) AS April,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 5 THEN st.student_id END) AS May,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 6 THEN st.student_id END) AS June,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 7 THEN st.student_id END) AS July,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 8 THEN st.student_id END) AS August,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 9 THEN st.student_id END) AS September,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 10 THEN st.student_id END) AS October,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 11 THEN st.student_id END) AS November,\n" +
            "    count(CASE WHEN extract(month from st.dayenroll) = 12 THEN st.student_id END) AS December\n" +
            "FROM\n" +
            "    student st";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Analysis anal = new Analysis(rs.getDouble(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11),rs.getDouble(12));
                data.add(anal);
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
}


